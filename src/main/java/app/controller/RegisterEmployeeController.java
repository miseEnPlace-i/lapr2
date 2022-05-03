package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * Register SNS User Controller
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class RegisterEmployeeController {
  private App app;
  private Company company;
  private Employee employee;
  private EmployeeStore store;
  private EmployeeRoleStore roleStore;

  /**
   * Constructor for RegisterEmployeeController.
   */
  public RegisterEmployeeController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.store = this.company.getEmployeeStore();
    this.roleStore = this.company.getEmployeeRoleStore();
    this.employee = null;
  }

  /**
   * Creates an Employee instance and validates it.
   * 
   * @param name the employee name
   * @param address the employee address
   * @param phoneNumber the employee phoneNumber
   * @param email the employee email
   * @param citizenCardNumber the employee citizenCardNumber
   * @param roleId the employee roleId
   */
  public void createEmployee(String name, String address, String phoneNumber, String email, String citizenCardNumber, String roleId) {
    // create an instance of an Employee
    this.employee = store.addEmployee(name, address, phoneNumber, email, citizenCardNumber, roleId);

    // validate the Employee
    store.validateEmployee(employee);
  }

  /**
   * Adds an Employee to the Employee store.
   */
  public void saveEmployee() {
    store.saveEmployee(this.employee);
  }

  /**
   * Returns all Employee roles.
   * 
   * @return String
   */
  public List<UserRole> getEmployeeRoles() {
    return roleStore.getRoles();
  }

  /**
   * Gets all the information about the employee.
   * 
   * @return the employee information
   */
  public String stringifyEmployee() {
    return this.employee.toString();
  }
}
