package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import app.controller.App;
import app.controller.ScheduleVaccineController;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.shared.FieldToValidate;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.service.CalendarUtils;
import app.ui.console.utils.Utils;

/**
 * ScheduleVaccineUI class.
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class ScheduleVaccineUI extends RegisterUI<ScheduleVaccineController> {
  private String snsUserNumber = "";

  public ScheduleVaccineUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
    snsUserNumber = ctrl.getSNSUserNumberWithEmail(email);
  }

  public void insertData() {
    VaccineType vaccineType = ctrl.getSuggestedVaccineType();

    boolean accepted = showSuggestedVaccineType(vaccineType);
    boolean isEligible = isUserEligibleForVaccine(vaccineType);

    if (!accepted || !isEligible) {
      // Declines the suggested vaccine type or is not eligible for vaccine type selected

      if (!isEligible) {
        System.out.println("\nYou are not eligible for any vaccine of this type. Please select other type.");
      }

      vaccineType = selectVaccineType();

      if (vaccineType == null) {
        return;
      }
    }

    if (userHasAppointmentForVaccineType(vaccineType)) {
      throw new IllegalArgumentException("You can not have two appointments for the same vaccine type.");
    }

    VaccinationCenter vacCenter = checkUserTakenVaccinesAndSelectsCenter(vaccineType);

    Calendar appointmentDate = selectDateAndTimeInCenterAvailability(vacCenter);

    boolean sms = selectSMS();

    ctrl.createAppointment(snsUserNumber, appointmentDate, vacCenter, vaccineType, sms);
  }

  private boolean showSuggestedVaccineType(VaccineType vt) {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return index == 0;
  }

  private VaccineType selectVaccineType() {
    List<VaccineTypeDTO> list = ctrl.getListOfVaccineTypes();

    boolean accepted;

    do {
      accepted = false;

      Object selectedVt = Utils.showAndSelectOne(list, "\n\nSelect a Vaccine Type:\n");
      VaccineType vaccineType;

      if (selectedVt == null) {
        return null;
      }

      try {
        VaccineTypeDTO vtDto = (VaccineTypeDTO) selectedVt;
        vaccineType = ctrl.getVaccineTypeByCode(vtDto.getCode());

        if (isUserEligibleForVaccine(vaccineType)) {
          accepted = true;
          return vaccineType;
        } else {
          System.out.println("\nYou are not eligible for any vaccine of this type. Please select other type.");
          accepted = false;
        }
      } catch (ClassCastException e) {
        System.out.println("\n\nInvalid selection.");
        return null;
      }
    } while (!accepted);

    return null;
  }

  /**
   * Check that if user has already taken a specific vaccine type, if it is eligible for that administration process and
   * selects center
   * 
   * @param vaccineType the vaccineType requested
   * 
   * @return the vaccination center selected
   */
  public VaccinationCenter checkUserTakenVaccinesAndSelectsCenter(VaccineType vaccineType) {
    VaccinationCenter center = null;

    if (isUserEligibleForVaccine(vaccineType)) {
      center = selectVaccinationCenterWithVaccineType(vaccineType);
    } else {
      throw new IllegalArgumentException("You are not eligible for any vaccine of this type.");
    }

    return center;
  }

  /**
   * Asks the user to select a vaccination center that administers certain vaccine type.
   * 
   * @param vt the vaccine type that the centers administer
   * @return the selected vaccination center
   */
  private VaccinationCenter selectVaccinationCenterWithVaccineType(VaccineType vt) {
    List<VaccinationCenterListDTO> list = ctrl.getListOfVaccinationCentersWithVaccineType(vt);

    Object selectedCenter = Utils.showAndSelectOne(list, "\nSelect a Vaccination Center:\n");

    if (selectedCenter == null) {
      return null;
    }

    try {
      VaccinationCenterListDTO centerDto = (VaccinationCenterListDTO) selectedCenter;
      return ctrl.getVaccinationCenterByEmail(centerDto.getEmail());
    } catch (ClassCastException e) {
      System.out.println("\n\nInvalid selection.");
      return null;
    }
  }

  /**
   * Asks the user if he wants to receive an SMS.
   * 
   * @return true it does, false otherwise
   */
  private boolean selectSMS() {
    System.out.println("\nDo you want to receive an SMS with the appointment's info?");
    List<String> options = new ArrayList<String>();
    options.add("Yes, send me an SMS.");
    options.add("No, don't send me an SMS.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return (index == 0);
  }

  private boolean userHasTakenVaccineType(VaccineType vt) {
    return ctrl.userHasTakenAnyVaccineFromVaccineType(vt, snsUserNumber);
  }

  /**
   * Asks user to enter a date for his appointment. The function checks if the center has availability for the selected
   * time: It checks for center schedule; It checks for slots availability.
   * 
   * @param center the center to be checked
   * @return the date selected by the user; or null if there is an error
   */
  private Calendar selectDateAndTimeInCenterAvailability(VaccinationCenter center) {
    boolean accepted = true;
    Date date = new Date();
    String hours;

    do {
      accepted = true;

      date = Utils.readDateInFutureFromConsole("Date (dd/MM/yyyy): ");
      hours = Utils.readLineFromConsoleWithValidation("Hour (HH:MM):", FieldToValidate.HOURS);

      if (!ctrl.isCenterOpenAt(center, hours)) {
        accepted = false;
        System.out.println("\nVaccination Center is closed or does not accept appointments at selected time. Please enter other date.\n");
        continue;
      }

      Calendar appointmentDate = Calendar.getInstance();
      try {
        appointmentDate = CalendarUtils.parseDateTime(date, hours);
      } catch (ParseException e) {
        System.out.println("\n\nDate or Hour invalid.");
      }

      if (!ctrl.hasSlotAvailability(center, appointmentDate)) {
        accepted = false;
        System.out.println("\nVaccination Center does not support more appointments at selected time. Please enter other date.\n");
        continue;
      }

      return appointmentDate;
    } while (!accepted);

    return null;
  }

  /**
   * Checks if user is eligible for a certain vaccine type. It checks if it has already taken any vaccine from certain
   * vaccine type: If it has taken, BY NOW THIS DOES NOTHING If it has not taken, checks if there is any vaccine with an
   * administration process that includes his age.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return true if user is eligible, false otherwise
   */
  private boolean isUserEligibleForVaccine(VaccineType vaccineType) {
    if (userHasTakenVaccineType(vaccineType)) {
      // ctrl.getVaccinesByType(vaccineType);
      // ctrl.checkAdministrationProcessForNextDose();
      // vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
      return false;
    } else {
      if (ctrl.checkAdministrationProcessForVaccineType(vaccineType, snsUserNumber)) {
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * Checks if user has an appointment for a certain vaccine type.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return true if has an appointment, false otherwise
   */
  private boolean userHasAppointmentForVaccineType(VaccineType vaccineType) {
    return ctrl.userHasAppointmentForVaccineType(vaccineType, snsUserNumber);
  }
}
