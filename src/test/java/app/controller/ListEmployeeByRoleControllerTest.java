package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class ListEmployeeByRoleControllerTest {
  Company company = new Company("designation", "12345");
  private ListEmployeesByRoleController ctrl = new ListEmployeesByRoleController(company);

  @Before
  public void setUp() {
    EmployeeRoleStore roleStore = company.getEmployeeRoleStore();
    roleStore.addEmployeeRole("TEST", "TEST");
    roleStore.addEmployeeRole("TEST1", "TEST1");

    EmployeeStore employeeStore = company.getEmployeeStore();
    Employee employee = new Employee("00000001", "Joana Maria", "+351123456789", "email@email.com", "Av. da Liberdade", "123456789ZZ1", "TEST");
    employeeStore.saveEmployee(employee);
  }

  @Test
  public void ensureGetEmployeeRolesIsWorking() {
    List<UserRole> roles = ctrl.getEmployeeRoles();
    assertEquals(2, roles.size());
    assertEquals("TEST", roles.get(0).getId());
    assertEquals("TEST1", roles.get(1).getId());
  }

  @Test
  public void ensureListEmployeesWithRoleIsWorking() {
    List<Employee> list = ctrl.getEmployeesWithRole("TEST");
    assertEquals(list.size(), 1);
  }

  @Test
  public void ensureListEmployeesWithNoElementsIsWorking() {
    List<Employee> list = ctrl.getEmployeesWithRole("TEST1");
    assertEquals(list.size(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureListEmployeesWithInvalidRoleIsWorking() {
    ctrl.getEmployeesWithRole("NON EXISTING ROLE");
  }
}
