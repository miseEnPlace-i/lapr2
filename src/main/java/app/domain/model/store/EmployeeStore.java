package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class EmployeeStore {
  private List<Employee> employees;

  private AuthFacade authFacade;

  /**
   * Constructor for EmployeeStore.
   */
  public EmployeeStore(AuthFacade authFacade) {
    this.employees = new ArrayList<Employee>();
    this.authFacade = authFacade;
  }

  /**
   * Creates an SNS User instance.
   * 
   * @param name the employee name
   * @param phoneNumber the employee phoneNumber
   * @param email the employee email
   * @param address the employee address
   * @param citizenCard the employee citizenCard
   * @param roleId the employee roleId
   */
  public Employee createEmployee(String name, String phoneNumber, String email, String address,
      String citizenCard, String roleId) {
    // TODO verifications

    Employee employee = new Employee(name, phoneNumber, email, address, citizenCard, roleId);
    return employee;
  }

  /**
   * @param roleId the role id to be searched
   * @return a List of employees with that given role
   */
  public List<Employee> getEmployeesWithRole(String roleId) {
    if (!authFacade.existsRole(roleId)) {
      throw new IllegalArgumentException("Role does not exist");
    }

    List<Employee> lstEmp = new ArrayList<>();

    for (Employee employee : employees)
      if (employee.getRoleId().equals(roleId)) lstEmp.add(employee);

    return lstEmp;
  }
}
