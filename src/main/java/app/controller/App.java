package app.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import app.domain.model.Company;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.shared.Constants;
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

  private App() {
    Properties props = getProperties();
    this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
    this.authFacade = this.company.getAuthFacade();
    this.employeeStore = this.company.getEmployeeStore();
    this.employeeRoleStore = this.company.getEmployeeRoleStore();

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

  private Properties getProperties() {
    Properties props = new Properties();

    // Add default properties and values
    props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");

    // Read configured values
    try {
      InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
      props.load(in);
      in.close();
    } catch (IOException ex) {

    }
    return props;
  }

  private void bootstrap() {
    // Added Receptionist user role & a test user with Receptionist role for testing
    // purposes
    this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
    this.employeeRoleStore.addEmployeeRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);

    this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
    this.authFacade.addUserWithRole("Test Administrator", "admin@admin.pt", "123456", Constants.ROLE_ADMIN);

    this.employeeStore.addEmployee("Name", "phoneNumber", "email", "address", "citizenCard", Constants.ROLE_RECEPTIONIST);
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
