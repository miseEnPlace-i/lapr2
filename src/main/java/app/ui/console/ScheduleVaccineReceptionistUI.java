package app.ui.console;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.controller.App;
import app.controller.ScheduleVaccineController;
import app.domain.shared.FieldToValidate;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.exception.AbortedOperationException;
import app.ui.console.utils.Utils;

/**
 * ScheduleVaccineReceptionistUI class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ScheduleVaccineReceptionistUI extends RegisterUI<ScheduleVaccineController> {
  public ScheduleVaccineReceptionistUI() {
    super(new ScheduleVaccineController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() {
    String snsNumber = Utils.readLineFromConsoleWithValidation("\nSNS Number (xxxxxxxxx):", FieldToValidate.SNS_NUMBER);

    VaccineTypeDTO vaccineTypeDto = ctrl.getSuggestedVaccineType();

    boolean accepted;
    try {
      accepted = showSuggestedVaccineType(vaccineTypeDto);
      if (!accepted) {
        vaccineTypeDto = selectVaccineType();
      }

      VaccinationCenterListDTO vacCenterDto = selectVaccinationCenterWithVaccineType(vaccineTypeDto);

      Date date = Utils.readDateInFutureFromConsole("Date (dd/MM/yyyy): ");
      String time = Utils.readLineFromConsoleWithValidation("Hour (HH:MM):", FieldToValidate.HOURS);

      boolean sms = selectSMS();

      ctrl.createAppointment(snsNumber, date, time, vacCenterDto, vaccineTypeDto, sms);
    } catch (AbortedOperationException e) {
      // System.out.println(e.getMessage());
    }
  }

  private boolean showSuggestedVaccineType(VaccineTypeDTO vt) throws AbortedOperationException {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    if (index == -1) throw new AbortedOperationException("Canceled operation by Receptionist.");

    return index == 0;
  }

  private VaccineTypeDTO selectVaccineType() throws AbortedOperationException {
    List<VaccineTypeDTO> list = ctrl.getListOfVaccineTypes();

    Object selectedVt = Utils.showAndSelectOne(list, "\n\nSelect a Vaccine Type:\n");

    if (selectedVt == null) throw new AbortedOperationException("Canceled operation by Receptionist.");

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
   * @throws AbortedOperationException
   */
  private VaccinationCenterListDTO selectVaccinationCenterWithVaccineType(VaccineTypeDTO vtDto) throws AbortedOperationException {
    List<VaccinationCenterListDTO> list = ctrl.getListOfVaccinationCentersWithVaccineType(vtDto);

    Object selectedCenter = Utils.showAndSelectOne(list, "\nSelect a Vaccination Center:\n");

    if (selectedCenter == null) throw new AbortedOperationException("Canceled operation by Receptionist.");

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

