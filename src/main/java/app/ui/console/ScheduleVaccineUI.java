package app.ui.console;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.controller.App;
import app.controller.ScheduleVaccineController;
import app.domain.shared.FieldToValidate;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.ui.console.utils.Utils;

/**
 * ScheduleVaccineUI class.
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class ScheduleVaccineUI extends RegisterUI<ScheduleVaccineController> {
  public ScheduleVaccineUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() {
    VaccineTypeDTO vaccineTypeDto = ctrl.getSuggestedVaccineType();

    boolean accepted = showSuggestedVaccineType(vaccineTypeDto);

    if (!accepted) {
      vaccineTypeDto = selectVaccineType();
    }

    VaccinationCenterListDTO vacCenterDto = selectVaccinationCenterWithVaccineType(vaccineTypeDto);

    Date date = Utils.readDateInFutureFromConsole("Date (dd/MM/yyyy): ");
    String time = Utils.readLineFromConsoleWithValidation("Hour (HH:MM):", FieldToValidate.HOURS);

    boolean sms = selectSMS();

    ctrl.createAppointment(date, time, vacCenterDto, vaccineTypeDto, sms);
  }

  private boolean showSuggestedVaccineType(VaccineTypeDTO vt) {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return index == 0;
  }

  private VaccineTypeDTO selectVaccineType() {
    List<VaccineTypeDTO> list = ctrl.getListOfVaccineTypes();

    Object selectedVt = Utils.showAndSelectOne(list, "\n\nSelect a Vaccine Type:\n");

    try {
      return (VaccineTypeDTO) selectedVt;
    } catch (ClassCastException ex) {
      throw new IllegalArgumentException("Invalid selection.");
    }
  }

  /**
   * Asks the user to select a vaccination center that administers certain vaccine type.
   * 
   * @param vt the vaccine type that the centers administer
   * @return the selected vaccination center
   */
  private VaccinationCenterListDTO selectVaccinationCenterWithVaccineType(VaccineTypeDTO vtDto) {
    List<VaccinationCenterListDTO> list = ctrl.getListOfVaccinationCentersWithVaccineType(vtDto);

    Object selectedCenter = Utils.showAndSelectOne(list, "\nSelect a Vaccination Center:\n");

    try {
      return (VaccinationCenterListDTO) selectedCenter;
    } catch (ClassCastException ex) {
      throw new IllegalArgumentException("Invalid selection.");
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
}
