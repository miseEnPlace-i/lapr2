package app.ui.console;

import java.text.ParseException;
import java.util.List;
import app.controller.RegisterEmployeeController;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * Register Employee View
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */

public class RegisterEmployeeUI extends RegisterUI<RegisterEmployeeController> {
  private String roleId = "";

  /**
   * Constructor for RegisterEmployeeUI.
   */
  public RegisterEmployeeUI() {
    super(new RegisterEmployeeController());
  }

  /**
   * Displays in the screen all the available employee roles
   * 
   * @param employeeRoles the list of employee roles
   */
  private void displayEmployeeRoles(List<UserRole> employeeRoles) {
    Utils.showList(employeeRoles, "Employee Roles");
  }

  /**
   * Selects an employee role
   * 
   * @param employeeRoles the list of employee roles
   */
  private UserRole selectEmployeeRole(List<UserRole> employeeRoles) {
    int roleIndex = Utils.selectsIndex(employeeRoles);

    return employeeRoles.get(roleIndex);
  }

  /**
   * Asks user to input employee data
   */
  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
    List<UserRole> employeeRoles = ctrl.getEmployeeRoles();
    displayEmployeeRoles(employeeRoles);

    UserRole role = selectEmployeeRole(employeeRoles);
    this.roleId = role.getId();

    String name = Utils.readLineFromConsole("Name: ");
    String address = Utils.readLineFromConsole("Address: ");
    String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
    String email = Utils.readLineFromConsole("Email: ");
    String citizenCard = Utils.readLineFromConsole("Citizen Card Number: ");

    super.ctrl.create(name, address, phoneNumber, email, citizenCard, this.roleId);
  }
}
