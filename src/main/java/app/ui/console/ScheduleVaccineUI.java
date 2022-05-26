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
import app.dto.AppointmentWithoutNumberDTO;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.service.CalendarUtils;
import app.ui.console.utils.Utils;

public class ScheduleVaccineUI extends RegisterUI<ScheduleVaccineController> {

  public ScheduleVaccineUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
  }

  public void insertData() {
    Date date = Utils.readDateFromConsole("Date (dd/MM/yyyy): ");
    String hours = Utils.readLineFromConsoleWithValidation("Hour (HH:MM)", FieldToValidate.HOURS);

    VaccineType vaccineType = ctrl.getSuggestedVaccineType();

    boolean accepted = showSuggestedVaccineType(vaccineType);

    if (!accepted) {
      // Declines the suggested vaccine type
      vaccineType = selectVaccineType();
    }

    VaccinationCenter vacCenter = null;

    if (checkIfUserHasTakenVaccineType(vaccineType)) {
      // ctrl.getVaccinesByType(vaccineType);
      // ctrl.checkAdministrationProcessForNextDose();
      // vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
    } else {
      if (ctrl.checkAdministrationProcessForVaccineType(vaccineType)) {
        vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
      } else {
        // TODO RAIA MAXIMA SAI DAQUI PARA FORA; NAO HA VACINA PARA A TUA IDADE
      }
    }

    if (!ctrl.isCenterOpenAt(vacCenter, hours)) {
      // TODO RAIA MAXIMA DESAPARECE; CENTRO ENCERRADO
    }

    // VERIFICAR A DISPONIBILIDADE DO SLOT.


    boolean sms = selectSMS();

    Calendar appointmentDate = Calendar.getInstance();
    try {
      appointmentDate = CalendarUtils.parseDateTime(date, hours);
    } catch (ParseException e) {
      System.out.println("\n\nDate or Hour invalid.");
    }

    AppointmentWithoutNumberDTO appointmentDto =
        new AppointmentWithoutNumberDTO(appointmentDate, vacCenter, vaccineType, sms);

    ctrl.createAppointment(appointmentDto);
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

    Object selectedVt = Utils.showAndSelectOne(list, "\n\nSelect a Vaccine Type:\n");

    try {
      VaccineTypeDTO vtDto = (VaccineTypeDTO) selectedVt;
      return ctrl.getVaccineTypeByCode(vtDto.getCode());
    } catch (ClassCastException e) {
      System.out.println("\n\nInvalid selection.");
      return null;
    }
  }

  private VaccinationCenter selectVaccinationCenterWithVaccineType(VaccineType vt) {
    List<VaccinationCenterListDTO> list = ctrl.getListOfVaccinationCentersWithVaccineType(vt);

    Object selectedCenter = Utils.showAndSelectOne(list, "\nSelect a Vaccination Center:\n");

    try {
      VaccinationCenterListDTO centerDto = (VaccinationCenterListDTO) selectedCenter;
      return ctrl.getVaccinationCenterByEmail(centerDto.getEmail());
    } catch (ClassCastException e) {
      System.out.println("\n\nInvalid selection.");
      return null;
    }
  }

  private boolean selectSMS() {
    System.out.println("\nDo you want to receive an SMS with the appointment's info?");
    List<String> options = new ArrayList<String>();
    options.add("Yes, send me an SMS.");
    options.add("No, don't send me an SMS.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return (index == 0);
  }

  private boolean checkIfUserHasTakenVaccineType(VaccineType vt) {
    return ctrl.checkIfUserHasTakenVaccineType(vt);
  }
}
