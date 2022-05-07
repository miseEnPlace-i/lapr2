package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.shared.Constants;

/**
 * Register Vaccination Center Controller
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class RegisterVaccinationCenterController implements IController<VaccinationCenter> {
  private App app;
  private Company company;
  private EmployeeStore employeeStore;
  private VaccinationCenter center;
  private VaccinationCenterStore vacStore;

  /**
   * Constructor for VaccinationCenterController
   */
  public RegisterVaccinationCenterController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.employeeStore = this.company.getEmployeeStore();
    this.vacStore = this.company.getVaccinationCenterStore();
  }

  /**
   * Creates an Vaccination Center instance and validates it.
   * 
   * @param name the vaccination center name
   * @param address the vaccination center address
   * @param emailAddress the vaccination center email address
   * @param phoneNum the vaccination center phone number
   * @param faxNum the vaccination center fax number
   * @param webAddress the vaccination center website address
   * @param openingHours the vaccination center opening hours
   * @param closingHours the vaccination center closing hours
   * @param slotDuration the vaccination center slot duration
   * @param maxVacSlot the vaccination center maximum vaccines per slot
   * @param coordinator the vaccination center coordinator
   */
  public void create(String name, String address, String emailAddress, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator) {

    // creates an vaccination center instance
    this.center = vacStore.createVaccinationCenter(name, address, emailAddress, phoneNum, faxNum,
        webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);

    // validates the center
    vacStore.validateVaccinationCenter(center);
  }

  /**
   * Saves Vaccination Center
   */
  @Override
  public void save() {
    vacStore.saveVaccinationCenter(this.center);
  }

  /**
   * @return Gets list of all coordinators registered in the system
   */
  public List<Employee> getCoordinators() {
    return employeeStore.getEmployeesWithRole(Constants.ROLE_COORDINATOR);
  }

  /**
   * Returns the Vaccination Center data as a String.
   */
  @Override
  public String stringifyData() {
    return this.center.toString();
  }
}
