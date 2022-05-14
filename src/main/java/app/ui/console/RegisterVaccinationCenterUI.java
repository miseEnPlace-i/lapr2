package app.ui.console;


import java.util.ArrayList;
import java.util.List;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.Employee;
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
    super(new RegisterVaccinationCenterController());
  }

  @Override
  public void insertData() {
    System.out.println("\nRegister Vaccination Center UI: ");

    String name = Utils.readLineFromConsole("Name: ");
    String address = Utils.readLineFromConsole("Address: ");
    String email = Utils.readLineFromConsole("Email (example@example.com): ");
    String phone = Utils.readLineFromConsole("Phone Number (+351xxxxxxxxx): ");
    String fax = Utils.readLineFromConsole("Fax Number (+351xxxxxxxxx): ");
    String website = Utils.readLineFromConsole("Website Address (https://domain.ext): ");
    String openHours = Utils.readLineFromConsole("Opening hours (HH:MM): ");
    String closHours = Utils.readLineFromConsole("Closing hours (HH:MM): ");
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

    super.ctrl.create(name, address, email, phone, fax, website, openHours, closHours, slotDur,
        maxVac, coordinator);
  }
}
