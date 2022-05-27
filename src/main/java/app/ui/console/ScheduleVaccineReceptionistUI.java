package app.ui.console;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * ScheduleVaccineReceptionistUI class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ScheduleVaccineReceptionistUI extends RegisterUI<ScheduleVaccineController> {
  String snsNumber;

  public ScheduleVaccineReceptionistUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() {
    System.out.println("\nEnter the appointment data:");
    boolean existsUser;

    do {
      snsNumber = Utils.readLineFromConsoleWithValidation("\nSNS Number (xxxxxxxxx):",
          FieldToValidate.SNS_NUMBER);
      existsUser = ctrl.existsUser(this.snsNumber);
    } while (!existsUser);

    VaccineType vaccineType = ctrl.getSuggestedVaccineType();

    boolean accepted = showSuggestedVaccineType(vaccineType);
    boolean isEligible = isUserEligibleForVaccine(vaccineType, this.snsNumber);

    if (!accepted || !isEligible) {
      // Declines the suggested vaccine type or is not eligible for vaccine type selected

      if (!isEligible) {
        System.out.println(
            "\nYou are not eligible for any vaccine of this type. Please select other type.");
      }

      vaccineType = selectVaccineType();

      if (vaccineType == null) {
        return;
      }
    }

    if (userHasAppointmentForVaccineType(vaccineType, this.snsNumber)) {
      throw new IllegalArgumentException(
          "\nYou can not have two appointments for the same vaccine type.\n");
    }

    VaccinationCenter vacCenter = null;

    if (userHasTakenVaccineType(vaccineType, this.snsNumber)) {
      // ctrl.getVaccinesByType(vaccineType);
      // ctrl.checkAdministrationProcessForNextDose();
      // vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
    } else {
      if (ctrl.checkAdministrationProcessForVaccineType(vaccineType, this.snsNumber)) {
        vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
      } else {
        throw new IllegalArgumentException(
            "\nYou are not eligible for any vaccine of this type.\n");
      }
    }

    Calendar appointmentDate = selectDateAndTimeInCenterAvailability(vacCenter);

    boolean sms = selectSMS();

    ctrl.createAppointment(snsNumber, appointmentDate, vacCenter, vaccineType, sms);
  }


  /**
   * Shows suggested vaccine type and asks to select one option
   * 
   * @param vt the vaccine type suggested by the system
   * @return "true" if accepted, "false" otherwise
   */
  public boolean showSuggestedVaccineType(VaccineType vt) {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return index == 0;
  }

  /**
   * Checks if user is eligible for a certain vaccine type. It checks if it has already taken any vaccine from certain
   * vaccine type: If it has taken, BY NOW THIS DOES NOTHING If it has not taken, checks if there is any vaccine with an
   * administration process that includes his age.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return "true" if user is eligible, "false" otherwise
   */
  private boolean isUserEligibleForVaccine(VaccineType vaccineType, String number) {
    if (userHasTakenVaccineType(vaccineType, number)) {
      // ctrl.getVaccinesByType(vaccineType);
      // ctrl.checkAdministrationProcessForNextDose();
      // vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
      return false;
    } else {
      if (ctrl.checkAdministrationProcessForVaccineType(vaccineType, number)) {
        return true;
      } else {
        return false;
      }

    }
  }

  /**
   * Checks if the user has already taken a specific vaccine type
   * 
   * @param vt the vaccineType to be checked
   * @return "true" if already taken, "false" otherwise
   */
  private boolean userHasTakenVaccineType(VaccineType vt, String number) {
    return ctrl.userHasTakenAnyVaccineFromVaccineType(vt, number);
  }

  /**
   * Receptionist selects the vaccine type pretended of user
   * 
   * @return vaccine type selected
   */
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

        if (isUserEligibleForVaccine(vaccineType, this.snsNumber)) {
          accepted = true;
          return vaccineType;
        } else {
          System.out.println(
              "\nYou are not eligible for any vaccine of this type. Please select other type.");
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

      SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      String dateStr =
          Utils.readLineFromConsoleWithValidation("Date (dd/MM/yyyy): ", FieldToValidate.DATE);
      try {
        date = df.parse(dateStr);
      } catch (ParseException ex) {
        System.out.println("Invalid date format.\n");
      }
      hours = Utils.readLineFromConsoleWithValidation("Hour (HH:MM):", FieldToValidate.HOURS);

      if (!ctrl.isCenterOpenAt(center, hours)) {
        accepted = false;
        System.out.println(
            "\nVaccination Center is closed or does not accept appointments at selected time. Please enter other date.\n");
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
        System.out.println(
            "\nVaccination Center does not support more appointments at selected time. Please enter other date.\n");
        continue;
      }

      return appointmentDate;
    } while (!accepted);

    return null;
  }

  /**
   * Checks if user has an appointment for a certain vaccine type.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return true if has an appointment, false otherwise
   */
  private boolean userHasAppointmentForVaccineType(VaccineType vaccineType, String number) {
    return ctrl.userHasAppointmentForVaccineType(vaccineType, number);
  }
}

