package app.ui.console;

import java.util.List;
import app.controller.ListEmployeesByRoleController;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * List all employees with a given role
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */

public class ListEmployeesByRoleUI implements Runnable {
  private ListEmployeesByRoleController ctrl;

  public ListEmployeesByRoleUI() {
    ctrl = new ListEmployeesByRoleController();
  }

  public void run() {
    System.out.println("\nList Employees By Role UI:");

    List<UserRole> employeeRoles = ctrl.getEmployeeRoles();

    displayEmployeeRoles(employeeRoles);

    selectEmployeeRole(employeeRoles);

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
  private void selectEmployeeRole(List<UserRole> employeeRoles) {
    int roleIndex = Utils.selectsIndex(employeeRoles);

    ctrl.listEmployeesWithRole(employeeRoles.get(roleIndex).getId());
  }
}
