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
import app.domain.model.dto.AppointmentWithNumberDTO;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.dto.VaccineTypeDTO;
import app.domain.shared.FieldToValidate;
import app.service.CalendarUtils;
import app.ui.console.utils.Utils;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ScheduleVaccineReceptionistUI extends RegisterUI<ScheduleVaccineController> {
  ScheduleVaccineController controller;

  public ScheduleVaccineReceptionistUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() {
    System.out.println("\nEnter the appointment data:");
    String SNSNumber;
    boolean existsUser;

    do {
      SNSNumber = Utils.readLineFromConsoleWithValidation("\nSNS Number (xxxxxxxxx):",
          FieldToValidate.SNS_NUMBER);
      existsUser = ctrl.existsUser(SNSNumber);
    } while (!existsUser);

    Date date = Utils.readDateFromConsole("Date (dd/MM/yyyy): ");
    String hours = Utils.readLineFromConsoleWithValidation("Hour (HH:MM)", FieldToValidate.HOURS);
    VaccineType vaccineType = ctrl.getSuggestedVaccineType();

    boolean accepted = showSuggestedVaccineType(vaccineType);

    if (!accepted) {
      // Declines the suggested vaccine type
      List<VaccineTypeDTO> list = ctrl.getListOfVaccineTypes();

      Object selectedVt = Utils.showAndSelectOne(list, "\n\nSelect a Vaccine Type:\n");

      try {
        VaccineTypeDTO vtDto = (VaccineTypeDTO) selectedVt;
        vaccineType = ctrl.getVaccineTypeByCode(vtDto.getCode());
      } catch (ClassCastException e) {
        System.out.println("\n\nInvalid selection.");
      }
    }

    List<VaccinationCenterListDTO> list =
        ctrl.getListOfVaccinationCentersWithVaccineType(vaccineType);

    VaccinationCenter vacCenter = null;

    Object selectedCenter = Utils.showAndSelectOne(list, "\n\nSelect a Vaccination Center:\n");

    try {
      VaccinationCenterListDTO centerDto = (VaccinationCenterListDTO) selectedCenter;
      vacCenter = ctrl.getVaccinationCenterByEmail(centerDto.getEmail());
    } catch (ClassCastException e) {
      System.out.println("\n\nInvalid selection.");
    }

    System.out.println("\nDo you want to receive an SMS with the appointment's info?\n");
    List<String> options = new ArrayList<String>();
    options.add("Yes, send me an SMS.");
    options.add("No, don't send me an SMS.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    boolean sms = (index == 0);

    Calendar appointmentDate = Calendar.getInstance();

    try {
      appointmentDate = CalendarUtils.parseDateTime(date, hours);
    } catch (ParseException e) {
      System.out.println("\n\nDate or Hour invalid.");
    }

    AppointmentWithNumberDTO appointmentDto =
        new AppointmentWithNumberDTO(SNSNumber, appointmentDate, vacCenter, vaccineType, sms);

    ctrl.createAppointment(appointmentDto);
  }

  public boolean showSuggestedVaccineType(VaccineType vt) {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return index == 0;
  }
}
