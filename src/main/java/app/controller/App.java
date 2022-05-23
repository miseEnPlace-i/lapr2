package app.controller;

import java.util.Properties;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import app.service.PropertiesUtils;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {
  private Company company;
  private AuthFacade authFacade;
  private EmployeeStore employeeStore;
  private EmployeeRoleStore employeeRoleStore;
  private VaccineTechnologyStore vaccineTechnologyStore;
  private VaccinationCenterStore vaccinationCenterStore;

  private App() {
    Properties props = PropertiesUtils.getProperties();
    this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
    this.authFacade = this.company.getAuthFacade();
    this.employeeStore = this.company.getEmployeeStore();
    this.employeeRoleStore = this.company.getEmployeeRoleStore();
    this.vaccineTechnologyStore = this.company.getVaccineTechnologyStore();
    this.vaccinationCenterStore = this.company.getVaccinationCenterStore();

    bootstrap();
  }

  public Company getCompany() {
    return this.company;
  }

  public UserSession getCurrentUserSession() {
    return this.authFacade.getCurrentUserSession();
  }

  public boolean doLogin(String email, String pwd) {
    return this.authFacade.doLogin(email, pwd).isLoggedIn();
  }

  public void doLogout() {
    this.authFacade.doLogout();
  }

  private void bootstrap() {
    // Added Receptionist user role & a test user with Receptionist role for testing
    // purposes
    this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_RECEPTIONIST,
        Constants.ROLE_RECEPTIONIST);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_COORDINATOR, Constants.ROLE_COORDINATOR);
    this.vaccineTechnologyStore.addVaccineTechnology("LIVE_ATTENUATED_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("INACTIVATED_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("SUBUNIT_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("TOXOID_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("VIRAL_VECTOR_TECHNOLOGY");
    this.vaccineTechnologyStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

    this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",
        Constants.ROLE_ADMIN);
    this.authFacade.addUserWithRole("Test Administrator", "admin@admin.pt", "123456",
        Constants.ROLE_ADMIN);

    Employee e = this.employeeStore.createEmployee("Name", "+351916919169", "teste@teste.com",
        "address", "123456789ZZ1", Constants.ROLE_RECEPTIONIST);
    this.employeeStore.saveEmployee(e);
    Employee e2 = this.employeeStore.createEmployee("Name2", "+351916919269", "teste1@teste.com",
        "adress", "155424041ZY0", Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e2);

    Employee e3 = this.employeeStore.createEmployee("Name2", "+351916919269", "nurse@nurse.pt",
        "adress", "000000000ZZ4", Constants.ROLE_NURSE);
    this.employeeStore.saveEmployee(e3);

    VaccineType type = new VaccineType("12345", "description", "test");

    VaccinationCenter vc = this.vaccinationCenterStore.createCommunityMassCenter("name", "address",
        "sda@das.com", "+351212345678", "+351212345678", "http://www.ggg.com", "20:00", "21:00", 5,
        5, e2, type);
    this.vaccinationCenterStore.saveVaccinationCenter(vc);
  }

  // Extracted from
  // https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
  private static App singleton = null;

  public static App getInstance() {
    if (singleton == null) {
      synchronized (App.class) {
        singleton = new App();
      }
    }
    return singleton;
  }
}
