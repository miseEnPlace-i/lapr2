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
public class RegisterEmployeeController implements IRegisterController {
  private Company company;
  private Employee employee;
  private EmployeeStore store;
  private EmployeeRoleStore roleStore;

  /**
   * Constructor for RegisterEmployeeController.
   */
  public RegisterEmployeeController(Company company) {
    this.company = company;
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
  public void create(String name, String address, String phoneNumber, String email,
      String citizenCardNumber, String roleId) {
    // create an instance of an Employee
    this.employee =
        store.createEmployee(name, phoneNumber, email, address, citizenCardNumber, roleId);

    // validate the Employee
    store.validateEmployee(employee);

  }

  @Override
  public void save() {
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

  @Override
  public String stringifyData() {
    return this.employee.toString();
  }

  @Override
  public String getResourceName() {
    return "Employee";
  }
}
