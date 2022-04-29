package app.domain.model;

import org.junit.Test;
import java.util.List;
import org.junit.Before;

import app.controller.App;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;

public class ListEmployeeTest {
  EmployeeStore employeeStore;
  AuthFacade authFacade;
  EmployeeRoleStore roleStore;

  @Before
  public void setUp() {
    this.authFacade = new AuthFacade();

    this.roleStore = new EmployeeRoleStore(this.authFacade);
    this.employeeStore = new EmployeeStore(this.authFacade, this.roleStore);

    this.roleStore.addEmployeeRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArguments() {
    employeeStore.getEmployeesWithRole(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole(Constants.ROLE_COORDINATOR);
    assert (result != null);
  }

  @Test
  public void testExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole(Constants.ROLE_RECEPTIONIST);
    assert (result != null);
  }
}
