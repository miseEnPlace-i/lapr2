package app.ui.console;

import java.util.List;
import app.controller.App;
import app.controller.ListEmployeesByRoleController;
import app.domain.model.Employee;
import app.domain.model.MyUserRole;
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
    ctrl = new ListEmployeesByRoleController(App.getInstance().getCompany());
  }

  public void run() {
    System.out.println("\nList Employees By Role:");

    List<MyUserRole> employeeRoles = ctrl.getEmployeeRoles();

    displayEmployeeRoles(employeeRoles);

    int roleIndex = Utils.selectsIndex(employeeRoles);

    if (roleIndex == -1) return;

    MyUserRole role = employeeRoles.get(roleIndex);

    List<Employee> employees = getEmployeesWithRole(role.getId());

    if (employees.isEmpty()) System.out.println("\nNo employees with this role.");
    else Utils.showList(employees, "\n" + role.getDescription());
  }

  /**
   * Displays in the screen all the available employee roles
   * 
   * @param employeeRoles the list of employee roles
   */
  private void displayEmployeeRoles(List<MyUserRole> employeeRoles) {
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
