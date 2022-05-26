package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.controller.App;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;

public class ListEmployeeByRoleTest {
  EmployeeStore employeeStore;

  @Before
  public void setUp() {
    Company company = new Company("abc", "12345", Constants.PARAMS_SENDER);
    this.employeeStore = company.getEmployeeStore();
    
    EmployeeRoleStore roleStore = company.getEmployeeRoleStore();

    roleStore.addEmployeeRole("TEST_ROLE", "Test description");
    roleStore.addEmployeeRole("TEST_ROLE2", "Test description 2");

    Employee e = employeeStore.createEmployee("name", "+351212345678", "email@email.com", "address",
        "000000000ZZ4", "TEST_ROLE");

    employeeStore.saveEmployee(e);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArguments() {
    employeeStore.getEmployeesWithRole(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole("INVALID_ROLE");
    assertNotNull(result);
  }

  @Test
  public void testExistingRole() {
    List<Employee> result = employeeStore.getEmployeesWithRole("TEST_ROLE");

    assertNotNull(result);
    assertEquals(result.size(), 1);
  }

  @Test
  public void testExistingRoleWithoutEmployees() {
    List<Employee> result = employeeStore.getEmployeesWithRole("TEST_ROLE2");

    assertNotNull(result);
    assertEquals(result.size(), 0);
  }
}
