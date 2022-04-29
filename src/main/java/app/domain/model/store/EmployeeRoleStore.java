package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;

import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class EmployeeRoleStore {
  private List<UserRole> roles;

  private AuthFacade authFacade;

  /**
   * Constructor for EmployeeRoleStore.
   */
  public EmployeeRoleStore(AuthFacade authFacade) {
    this.roles = new ArrayList<UserRole>();
    this.authFacade = authFacade;
  }

  /**
   * Creates a new role
   * 
   * @param id the role id
   * @param description the role description
   */
  public UserRole addEmployeeRole(String id, String description) {
    if (authFacade.existsRole(id)) throw new IllegalArgumentException("Role already exists");

    authFacade.addUserRole(id, description);

    UserRole role = new UserRole(id, description);
    roles.add(role);

    return role;
  }

  public List<UserRole> getRoles() {
    return roles;
  }

  public boolean existsRole(String id) {
    return authFacade.existsRole(id);
  }
}
