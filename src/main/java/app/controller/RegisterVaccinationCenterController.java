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
public class RegisterVaccinationCenterController implements IRegisterController {
  private Company company;
  private EmployeeStore employeeStore;
  private VaccinationCenter center;
  private VaccinationCenterStore vacStore;

  /**
   * Constructor for VaccinationCenterController
   */
  public RegisterVaccinationCenterController(Company company) {
    this.company = company;
    this.employeeStore = this.company.getEmployeeStore();
    this.vacStore = this.company.getVaccinationCenterStore();
  }

  /**
   * Creates a Community Mass Vaccination Center instance and validates it.
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
  public void createCommunityMass(String name, String address, String emailAddress, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator) {

    // creates a vaccination center instance
    this.center = vacStore.createVaccinationCenter(name, address, emailAddress, phoneNum, faxNum,
        webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);

    // validates the center
    vacStore.validateVaccinationCenter(center);
  }

  /**
   * Creates a Health Care Center instance and validates it.
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
   * @param ages the vaccination center AGES
   * @param ars the vaccination center ARS
   */
  public void createHealthCare(String name, String address, String emailAddress, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator, String ages, String ars) {

    // creates a vaccination center instance
    this.center = vacStore.createVaccinationCenter(name, address, emailAddress, phoneNum, faxNum,
        webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);

    // validates the center
    vacStore.validateVaccinationCenter(center);
  }

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

  @Override
  public String stringifyData() {
    return this.center.toString();
  }

  @Override
  public String getResourceName() {
    return "Vaccination Center";
  }
}
