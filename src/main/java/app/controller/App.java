package app.controller;

import java.util.Date;
import java.util.Properties;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

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
  private VaccineTypeStore vacTypeStore;
  private SNSUserStore snsUserStore;

  private App() {
    Properties props = PropertiesUtils.getProperties();
    this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION),
        props.getProperty(Constants.PARAMS_ONGOING_OUTBREAK_VACCINE_TYPE_CODE), props.getProperty(Constants.PARAMS_SENDER));

    this.authFacade = this.company.getAuthFacade();
    this.employeeStore = this.company.getEmployeeStore();
    this.employeeRoleStore = this.company.getEmployeeRoleStore();
    this.vaccineTechnologyStore = this.company.getVaccineTechnologyStore();
    this.vaccinationCenterStore = this.company.getVaccinationCenterStore();
    this.vacTypeStore = this.company.getVaccineTypeStore();
    this.snsUserStore = this.company.getSNSUserStore();

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
    this.authFacade.addUserRole(Constants.ROLE_SNS_USER, Constants.ROLE_SNS_USER);
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

    SNSUser user = this.snsUserStore.createSNSUser("000000000ZZ4", "123456789", "name", new Date(),
        'M', "+351212345678", "s@user.com", "address");
    this.snsUserStore.saveSNSUser(user);

    Employee e = this.employeeStore.createEmployee("Name", "+351916919169", "r@user.com",
        "address", "123456789ZZ1", Constants.ROLE_RECEPTIONIST);
    this.employeeStore.saveEmployee(e);
    Employee e2 = this.employeeStore.createEmployee("Name2", "+351916919269", "c@user.com",
        "address", "155424041ZY0", Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e2);

    Employee e3 = this.employeeStore.createEmployee("Name2", "+351916919269", "n@user.com",
        "address", "000000000ZZ4", Constants.ROLE_NURSE);
    this.employeeStore.saveEmployee(e3);

    Employee e4 = this.employeeStore.createEmployee("Name2", "+351916919269", "teste@coor.pt",
        "address", "000000000ZZ4", Constants.ROLE_COORDINATOR);
    this.employeeStore.saveEmployee(e4);

    VaccineType vacType = this.vacTypeStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
    this.vacTypeStore.saveVaccineType(vacType);

    VaccinationCenter vc = this.vaccinationCenterStore.createCommunityMassCenter("name", "address",
        "test@gmail.com", "+351212345678", "+351212345679", "http://www.test.com", "20:00", "21:00",
        5, 5, e2, vacType);
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
