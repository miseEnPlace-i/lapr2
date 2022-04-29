package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.store.EmployeeStore;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

/**
 * Register SNS User Controller
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class ListEmployeesByRoleController {
  private App app;
  private Company company;
  private EmployeeStore store;

  /**
   * Constructor for ListEmployeesByRole.
   */
  public ListEmployeesByRoleController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.store = this.company.getEmployeeStore();
  }

  /**
   * Lists all the employees for a given role
   * 
   * @param role
   * @return Employee List with Roles
   */
  public void listEmployeesWithRole(String roleId) {
    store.getEmployeesWithRole(roleId);
  }

  public List<UserRoleDTO> getEmployeeRoles() {
    return this.company.getAuthFacade().getUserRoles();
  }
}
