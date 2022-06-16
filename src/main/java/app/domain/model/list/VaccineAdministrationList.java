package app.domain.model.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import app.dto.AdverseReactionDTO;
import app.dto.UserNotificationDTO;
import app.dto.VaccineDTO;
import app.domain.model.AdverseReaction;
import app.domain.model.CenterEvent;
import app.domain.model.RecoveryRoom;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.WaitingRoom;
import app.domain.shared.CenterEventType;
import app.domain.shared.Constants;
import app.mapper.AdverseReactionMapper;
import app.mapper.UserNotificationMapper;
import app.mapper.VaccineMapper;
import app.service.CalendarUtils;
import app.service.PropertiesUtils;
import app.service.sender.ISender;
import app.service.sender.SenderFactory;
import app.domain.model.VaccineAdministration;

public class VaccineAdministrationList implements Serializable {
  private List<VaccineAdministration> vaccineAdministrations;

  /**
   * Constructor for VaccineAdministrationList.
   */
  public VaccineAdministrationList() {
    this.vaccineAdministrations = new ArrayList<VaccineAdministration>();
  }

  /**
   * Creates a new vaccine administration object.
   * 
   * @param snsUser the snsUser who got the vaccine
   * @param vaccine the vaccine that was administered
   * @param lotNumber the lot number of the vaccine
   * @param doseNumber the dose number of the vaccine
   * @param vaccinationCenter the vaccination center where the vaccine was administered
   * @param date the date the vaccine was administered
   */
  public VaccineAdministration createVaccineAdministration(SNSUser snsUser,
      Vaccine vaccine, String lotNumber, int doseNumber,
      VaccinationCenter vaccinationCenter, Calendar date) {
    VaccineAdministration vaccineAdministration = new VaccineAdministration(
        snsUser, vaccine, lotNumber, doseNumber, vaccinationCenter, date);

    return vaccineAdministration;
  }

  /**
   * Validates a vaccine administration object.
   * 
   * @param vaccineAdministration the vaccine administration object to be validated
   */
  public void validateVaccineAdministration(
      VaccineAdministration vaccineAdministration) {

  }

  /**
   * Saves a vaccine administration object.
   * 
   * @param vaccineAdministration the vaccine administration object to be saved
   */
  public void saveVaccineAdministration(
      VaccineAdministration vaccineAdministration) {
    vaccineAdministrations.add(vaccineAdministration);

    VaccinationCenter vaccinationCenter =
        vaccineAdministration.getVaccinationCenter();
    vaccinationCenter.addVaccineAdministrationToList(vaccineAdministration);

    CenterEventList centerEventList = vaccinationCenter.getEvents();

    SNSUser snsUser = vaccineAdministration.getSnsUser();
    Calendar date = vaccineAdministration.getDate();

    CenterEvent vaccinated =
        centerEventList.create(date, CenterEventType.VACCINATED, snsUser);

    centerEventList.save(vaccinated);

    WaitingRoom waitingRoom = vaccinationCenter.getWaitingRoom();
    waitingRoom.removeUser(snsUser);

    RecoveryRoom recoveryRoom = vaccinationCenter.getRecoveryRoom();
    recoveryRoom.addVaccineAdministration(vaccineAdministration);

    Properties props = PropertiesUtils.getProperties();
    int recoveryPeriod =
        Integer.parseInt(props.getProperty(Constants.PARAMS_RECOVERY_PERIOD));

    Calendar departureTime = Calendar.getInstance();
    departureTime.add(Calendar.MINUTE, recoveryPeriod);

    CenterEvent departure = centerEventList.create(departureTime,
        CenterEventType.DEPARTURE, snsUser);

    centerEventList.save(departure);

    setSmsSending(vaccineAdministration, recoveryPeriod);
  }

  private void setSmsSending(VaccineAdministration vaccineAdministration,
      int recoveryPeriod) {
    String message = String.format(
        "Your recovery period has ended.\nYou can now leave the center.");
    UserNotificationDTO notificationDto = UserNotificationMapper.toDto(
        vaccineAdministration.getSnsUser().getEmail(),
        vaccineAdministration.getSnsUser().getPhoneNumber(), message);

    new java.util.Timer().schedule(new java.util.TimerTask() {
      @Override
      public void run() {
        sendNotification(notificationDto);
      }
    }, minutesToMilliseconds(recoveryPeriod));
  }

  private long minutesToMilliseconds(int minutes) {
    return minutes * 60 * 1000;
  }

  private void sendNotification(UserNotificationDTO notificationDto) {
    ISender sender = SenderFactory.getSender();

    try {
      sender.send(notificationDto);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Get the last taken vaccine of a given vaccine type by a user.
   * 
   * @param VaccineType the vaccine type
   * 
   * @return the last taken vaccine of a given vaccine type by a user
   */
  public VaccineDTO getLastTakenVaccineByVaccineType(VaccineType vaccineType) {
    Collections.sort(vaccineAdministrations);

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.vaccineHasVaccineType(vaccineType)) {
        return VaccineMapper.toDto(vaccineAdministration.getVaccine());
      }
    }

    return null;
  }

  /**
   * Get the last taken vaccine of a given vaccine type by a user.
   * 
   * @param VaccineType the vaccine type
   * 
   * @return the last taken vaccine of a given vaccine type by a user
   */
  public VaccineAdministration getLastVaccineAdministrationByVaccineType(
      VaccineType vaccineType) {
    Collections.sort(vaccineAdministrations);

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.vaccineHasVaccineType(vaccineType)) {
        return vaccineAdministration;
      }
    }

    return null;
  }

  /**
   * Get the next dose number to administer to a user.
   * 
   * @param Vaccine the vaccine to check the next dose number
   * 
   * @return the next dose number to administer to a user
   */
  public int getNextDoseNumberOfVaccine(Vaccine vaccine) {
    Collections.sort(vaccineAdministrations);

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.getVaccine().equals(vaccine)) {
        return vaccineAdministration.getDoseNumber() + 1;
      }
    }

    return 1;
  }

  /**
   * Gets the size of the list.
   * 
   * @return int of number of VaccineAdministrations in the list.
   */
  public int size() {
    return vaccineAdministrations.size();
  }
}
