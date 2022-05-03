package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.controller.RegisterEmployeeController;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * Register Employee View
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */

public class RegisterEmployeeUI implements Runnable, IRegisterUI {
  private RegisterEmployeeController controller;

  private String roleId = "";

  /**
   * Constructor for RegisterEmployeeUI.
   */
  public RegisterEmployeeUI() {
    this.controller = new RegisterEmployeeController();
  }

  public void run() {
    System.out.println("\nRegister Employee UI:");

    List<UserRole> employeeRoles = controller.getEmployeeRoles();
    displayEmployeeRoles(employeeRoles);

    UserRole role = selectEmployeeRole(employeeRoles);
    this.roleId = role.getId();

    try {
      insertData();
    } catch (Exception e) {
      System.out.println(String.format("Error: %s\n", e.getMessage()));
    }

    boolean confirmed = confirmData();

    if (confirmed) {
      controller.saveEmployee();
      System.out.println("Employee successfully registered!");
    }
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
    String name = Utils.readLineFromConsole("Name: ");
    String address = Utils.readLineFromConsole("Address: ");
    String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
    String email = Utils.readLineFromConsole("Email: ");
    String citizenCard = Utils.readLineFromConsole("Citizen Card Number: ");

    controller.addEmployee(name, address, phoneNumber, email, citizenCard, this.roleId);
  }

  /**
   * Asks user to confirm the employee data
   *
   * @return true if the user confirms, false otherwise
   */
  @Override
  public boolean confirmData() {
    System.out.println("\nPlease confirm the data below.\n");
    String stringifiedEmployee = controller.stringifyEmployee();
    System.out.println(stringifiedEmployee);

    List<String> options = new ArrayList<String>();
    options.add("y");
    options.add("n");
    Object input = Utils.showAndSelectOne(options, "Is this information correct? (y/n):  ");
    String inputStr = (String) input;

    return inputStr.equals("y");
  }
}
