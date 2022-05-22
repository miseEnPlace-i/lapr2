package app.ui.console;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.Employee;
import app.domain.shared.FieldToValidate;
import app.domain.shared.VaccinationCenterType;
import app.ui.console.utils.Utils;

/**
 * Register a new Vaccination Center
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class RegisterVaccinationCenterUI extends RegisterUI<RegisterVaccinationCenterController> {
  /**
   * VaccinationCenterUI Constructor
   */
  public RegisterVaccinationCenterUI() {
    super(new RegisterVaccinationCenterController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() {
    // Select center type
    System.out.println("\nSelect the Vaccination Center type: ");
    VaccinationCenterType type = (VaccinationCenterType) Utils
        .showAndSelectOne(Arrays.asList(VaccinationCenterType.values()), "");
    String name = Utils.readLineFromConsole("Name: ");
    String address = Utils.readLineFromConsole("Address: ");
    String email = Utils.readLineFromConsoleWithValidation("Email (example@example.com): ",
        FieldToValidate.EMAIL);
    String phone = Utils.readLineFromConsoleWithValidation("Phone Number (+351xxxxxxxxx): ",
        FieldToValidate.PHONE_NUMBER);
    String fax = Utils.readLineFromConsoleWithValidation("Fax Number (+351xxxxxxxxx): ",
        FieldToValidate.FAX);
    String website = Utils.readLineFromConsoleWithValidation(
        "Website Address (https://domain.ext): ", FieldToValidate.WEBSITE);
    String openHours =
        Utils.readLineFromConsoleWithValidation("Opening hours (HH:MM): ", FieldToValidate.HOURS);
    String closHours =
        Utils.readLineFromConsoleWithValidation("Closing hours (HH:MM): ", FieldToValidate.HOURS);
    int slotDur = Utils.readIntegerFromConsole("Slot duration: ");
    int maxVac = Utils.readIntegerFromConsole("Maximum vaccines per slot: ");
    Employee coordinator;

    // select coordinator
    boolean flag = false;
    List<Employee> coordinators = new ArrayList<Employee>();
    coordinators = ctrl.getCoordinators();

    do {
      System.out.println("\nSelect a employee from the list:\n");
      coordinator = (Employee) Utils.showAndSelectOne(coordinators, "Coordinators");
      if (coordinators.contains(coordinator)) {
        flag = true;
      } else {
        System.out.println("\nInvalid coordinator.");
      }
    } while (!flag);

    // if type == 0 -> Community Mass Vaccination Center; if type == 1 -> Health Care Center
    if (type == VaccinationCenterType.COMMUNITY_MASS_VACCINATION_CENTER) {
      super.ctrl.createCommunityMass(name, address, email, phone, fax, website, openHours,
          closHours, slotDur, maxVac, coordinator);
    } else {
      String ages = Utils.readLineFromConsole("AGES: ");
      String ars = Utils.readLineFromConsole("ARS: ");
      super.ctrl.createHealthCare(name, address, email, phone, fax, website, openHours, closHours,
          slotDur, maxVac, coordinator, ages, ars);
    }
  }
}
