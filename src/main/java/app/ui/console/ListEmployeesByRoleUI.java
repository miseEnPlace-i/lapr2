package app.ui.console;

import java.util.List;
import app.controller.ListEmployeesByRoleController;
import app.domain.model.Employee;
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

    int roleIndex = Utils.selectsIndex(employeeRoles);

    UserRole role = employeeRoles.get(roleIndex);

    List<Employee> employees = getEmployeesWithRole(role.getId());

    Utils.showList(employees, "\n" + role.getDescription());
  }

  /**
   * Displays in the screen all the available employee roles
   * 
   * @param employeeRoles the list of employee roles
   */
  private void displayEmployeeRoles(List<UserRole> employeeRoles) {
    Utils.showList(employeeRoles, "\nEmployee Roles");
  }

  /**
   * Selects an employee role
   * 
   * @param employeeRoles the list of employee roles
   */
  private List<Employee> getEmployeesWithRole(String roleId) {
    return ctrl.getEmployeesWithRole(roleId);
  }
}
