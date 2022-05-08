package app.domain.model.store;

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

    assert roles.size() == 1;
    assert roles.get(0).equals(role);
  }

  @Test
  public void ensureRoleIsBeingAddedToAuthFacade() {
    assert !authFacade.existsRole("role");

    employeeRoleStore.addEmployeeRole("role", "role");

    assert authFacade.existsRole("role");
  }

  @Test
  public void ensureExistsRoleIsWorking() {
    assert !employeeRoleStore.existsRole("role");

    employeeRoleStore.addEmployeeRole("role", "role");

    System.out.println(employeeRoleStore.getRoles());

    assert employeeRoleStore.existsRole("role");
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

    assert employeeRoleStore.existsRole("role");
    assert employeeRoleStore.existsRole("role1");
    assert employeeRoleStore.getRoles().size() == 2;
  }
}
