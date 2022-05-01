package app.domain.model;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import pt.isep.lei.esoft.auth.AuthFacade;

public class ListEmployeeTest {
  EmployeeStore employeeStore;

  @Before
  public void setUp() {
    AuthFacade authFacade = new AuthFacade();

    EmployeeRoleStore roleStore = new EmployeeRoleStore(authFacade);
    this.employeeStore = new EmployeeStore(authFacade, roleStore);

    roleStore.addEmployeeRole("TEST_ROLE", "Test description");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArguments() {
    employeeStore.getEmployeesWithRole(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole("INVALID_ROLE");
    assert (result != null);
  }

  @Test
  public void testExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole("TEST_ROLE");
    assert (result != null);
  }
}
