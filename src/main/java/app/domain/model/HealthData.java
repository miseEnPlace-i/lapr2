package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.dto.AdverseReactionDTO;
import app.mapper.AdverseReactionMapper;
import app.service.CalendarUtils;


/**
 * HealthData
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class HealthData implements Serializable {
  SNSUser snsUser;
  VaccineAdministrationList vaccineAdministrationList;
  List<Appointment> appointments;
  SNSUserStore store;
  List<AdverseReaction> adverseReactions;

  /**
   * HealthData Constructor
   * 
   * @param snsUser
   */
  public HealthData(SNSUser snsUser) {
    this.vaccineAdministrationList = new VaccineAdministrationList();
    this.appointments = new ArrayList<>();
    this.snsUser = snsUser;
    this.adverseReactions = new ArrayList<>();
  }

  /**
   * Adds the appointment to the user scheduled vaccines
   * 
   * @param appointment The appointment to be added.
   */
  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
  }

  /**
   * 
   * @param vaccineType The type of the vaccine.
   * @return True if the user has an appointment for the given vaccine type in the future
   */
  public boolean hasAppointmentForVaccineTypeInFuture(VaccineType vaccineType) {
    for (Appointment appointment : appointments)
      if (appointment.hasSnsNumber(snsUser.getSnsNumber()) && appointment.hasVaccineType(vaccineType) && isAppointmentInFuture(appointment.getDate()))
        return true;

    return false;
  }

  /**
   * 
   * @param vaccineType The vaccine type to check.
   * @return True if the user has an appointment for the given vaccine type for today
   */
  public boolean hasAppointmentForVaccineTypeToday(VaccineType vaccineType) {
    for (Appointment appointment : appointments)
      if (appointment.hasSnsNumber(snsUser.getSnsNumber()) && appointment.hasVaccineType(vaccineType) && isAppointmentToday(appointment.getDate())) return true;

    return false;
  }

  /**
   * 
   * @param appointmentDate The date of the appointment.
   * @return True if the appointment is in the future, false otherwise.
   */
  private boolean isAppointmentInFuture(Calendar appointmentDate) {
    return CalendarUtils.compareDates(appointmentDate, Calendar.getInstance()) > 0;
  }

  /**
   * 
   * @param appointmentDate The date of the appointment.
   * @return True if the appointment is today, false otherwise.
   */
  private boolean isAppointmentToday(Calendar appointmentDate) {
    return CalendarUtils.compareDates(appointmentDate, Calendar.getInstance()) == 0;
  }

  /**
   * Get the VaccineAdministrationList.
   * 
   * @return the vaccineAdministrationList
   */
  public VaccineAdministrationList getVaccineAdministrationList() {
    return vaccineAdministrationList;
  }

  /**
   * Adds an adverse reaction to the vaccine administration.
   * 
   * @param adverseReaction the adverse reaction to add
   */
  public void addAdverseReaction(AdverseReaction adverseReaction) {
    this.adverseReactions.add(adverseReaction);
  }

  /**
   * Gets the list of adverse reactions.
   * 
   * @return a list of AdverseReactionDTO
   */
  public List<AdverseReactionDTO> getAdverseReactions() {
    return AdverseReactionMapper.toDto(adverseReactions);
  }

  /**
   * 
   * @param vaccineType the vaccine type to check
   * @return true if the user has taken all the doses of the vaccine type
   */
  public boolean hasTakenAllDoses(VaccineType vaccineType) {
    VaccineAdministration lastVaccineAdministration = vaccineAdministrationList.getLastVaccineAdministrationByVaccineType(vaccineType);
    if (lastVaccineAdministration == null) return false;

    return lastVaccineAdministration.getDoseNumber() == lastVaccineAdministration.getVaccine().getAdministrationProcessForGivenAge(snsUser.getAge())
        .getNumberOfDoses();
  }

  /**
   * 
   * @param vaccine the vaccine to check
   * @param doseNumber the dose number to check
   * @return true if the user is eligible for taking the specified dose
   */
  public boolean isTakingCorrectDoseOfVaccine(Vaccine vaccine, int doseNumber) {
    VaccineAdministration lastVaccineAdministration = vaccineAdministrationList.getLastVaccineAdministrationByVaccineType(vaccine.getVacType());
    if (lastVaccineAdministration == null && doseNumber > 1) return false;
    if (lastVaccineAdministration == null && doseNumber == 1) return true;
    if (lastVaccineAdministration.getDoseNumber() == (doseNumber - 1)) return true;

    return false;
  }
}
