package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class EmployeeRoleStoreTest {
  private EmployeeRoleStore employeeRoleStore;
  private AuthFacade authFacade;

  @Before
  public void setUp() {
    this.authFacade = new AuthFacade();
    this.employeeRoleStore = new EmployeeRoleStore(authFacade);
  }

  @Test
  public void ensureIsPossibleToAddNewRole() {
    List<UserRole> roles = employeeRoleStore.getRoles();

    assert roles.size() == 0;

    UserRole role = employeeRoleStore.addEmployeeRole("role", "role");

    roles = employeeRoleStore.getRoles();

    assertEquals(roles.size(), 1);
    assertEquals(roles.get(0), role);
  }

  @Test
  public void ensureRoleIsBeingAddedToAuthFacade() {
    assert !authFacade.existsRole("role");

    employeeRoleStore.addEmployeeRole("role", "role");

    assertTrue(authFacade.existsRole("role"));
  }

  @Test
  public void ensureExistsRoleIsWorking() {
    assertFalse(employeeRoleStore.existsRole("role"));

    employeeRoleStore.addEmployeeRole("role", "role");

    System.out.println(employeeRoleStore.getRoles());

    assertTrue(employeeRoleStore.existsRole("role"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureItsNotPossibleToCreateDuplicateRoles() {
    employeeRoleStore.addEmployeeRole("role", "role");
    employeeRoleStore.addEmployeeRole("role", "role");
  }

  @Test
  public void ensureItsPossibleToCreateDuplicateRolesWithDifferentIds() {
    employeeRoleStore.addEmployeeRole("role", "role");
    employeeRoleStore.addEmployeeRole("role1", "role");

    assertTrue(employeeRoleStore.existsRole("role"));
    assertTrue(employeeRoleStore.existsRole("role1"));
    assertEquals(employeeRoleStore.getRoles().size(), 2);
  }
}
