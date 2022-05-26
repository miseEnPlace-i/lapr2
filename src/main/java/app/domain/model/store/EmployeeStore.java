package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Employee;
import app.dto.UserNotificationDTO;
import app.mapper.UserNotificationMapper;
import app.service.PasswordGenerator;
import app.service.sender.ISender;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class EmployeeStore {
  private AuthFacade authFacade;
  private List<Employee> employees;
  private EmployeeRoleStore roleStore;
  private ISender sender;

  /**
   * Constructor for EmployeeStore.
   */
  public EmployeeStore(AuthFacade authFacade, EmployeeRoleStore roleStore, ISender sender) {
    this.employees = new ArrayList<Employee>();
    this.authFacade = authFacade;
    this.roleStore = roleStore;
    this.sender = sender;
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
    String id = generateId();
    Employee employee = new Employee(id, name, phoneNumber, email, address, citizenCard, roleId);

    return employee;
  }

  private String generateId() {
    return String.format("%010d", employees.size() + 1);
  }

  /**
   * Gets all employees with a given role.
   * 
   * @param roleId the employee roleId
   * @return a List of employees with that given role
   */
  public List<Employee> getEmployeesWithRole(String roleId) {
    if (roleId == null) throw new IllegalArgumentException("Role id cannot be null.");
    if (!roleStore.existsRole(roleId)) throw new IllegalArgumentException("Role does not exist.");

    List<Employee> lstEmp = new ArrayList<>();

    for (Employee employee : employees)
      if (employee.hasRoleId(roleId)) lstEmp.add(employee);

    return lstEmp;
  }

  /**
   * Checks if there are duplicates.
   * 
   * @param employee the employee to be checked
   */
  public void validateEmployee(Employee employee) {
    if (employee == null) throw new IllegalArgumentException("Employee cannot be null.");

    String email = employee.getEmail();

    if (this.authFacade.existsUser(email))
      throw new IllegalArgumentException("Email already exists.");

    checkDuplicates(employee);
  }

  /**
   * Inserts an employee into the store and creates a system user.
   * 
   * @param employee the employee to be inserted
   */
  public void saveEmployee(Employee employee) {
    this.employees.add(employee);

    String email = employee.getEmail();
    String phoneNumber = employee.getPhoneNumber();
    String pwd = PasswordGenerator.generatePwd();

    this.authFacade.addUserWithRole(employee.getName(), email, pwd, employee.getRoleId());

    String message = String.format("A new user has been created.\nEmail: %s\nPassword: %s", email, pwd);
    UserNotificationDTO notiDto = UserNotificationMapper.toDto(email, phoneNumber, message);

    // send notification with the password
    try {
      this.sender.send(notiDto);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Checks if there are duplicates in the store.
   * 
   * @param employee the employee to be checked
   */
  public void checkDuplicates(Employee employee) {
    if (employees.contains(employee))
      throw new IllegalArgumentException("Duplicate employee found.");
  }

  /**
   * Gets the size of the store.
   * 
   * @return int of number of Employees in the store.
   */
  public int size() {
    return employees.size();
  }
}
