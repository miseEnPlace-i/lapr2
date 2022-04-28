package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.store.EmployeeStore;

/**
 * Register SNS User Controller
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
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
  public void listEmployeesByRole(String roleId) {
    // TODO
  }
}
