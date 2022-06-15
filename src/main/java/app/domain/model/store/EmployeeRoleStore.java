package app.domain.model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.MyUserRole;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.UserRole;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class EmployeeRoleStore implements Serializable {
  private List<MyUserRole> roles;

  private transient AuthFacade authFacade;

  /**
   * Constructor for EmployeeRoleStore.
   */
  public EmployeeRoleStore(AuthFacade authFacade) {
    this.roles = new ArrayList<MyUserRole>();
    this.authFacade = authFacade;
  }

  /**
   * Creates a new role
   * 
   * @param id the role id
   * @param description the role description
   */
  public MyUserRole addEmployeeRole(String id, String description) {
    if (authFacade.existsRole(id)) throw new IllegalArgumentException("Role already exists.");
    if (existsRole(id)) throw new IllegalArgumentException("Role already exists.");

    authFacade.addUserRole(id, description);

    MyUserRole role = new MyUserRole(id, description);
    roles.add(role);

    return role;
  }

  /**
   * Gets a list of all roles.
   * 
   * @return List of all Employee Roles
   */
  public List<MyUserRole> getRoles() {
    return roles;
  }

  /**
   * Checks id a given role exists.
   * 
   * When creating a new User Role the ID is upper cased, so, it is necessary to use equalsIgnoreCase()
   * 
   * @param id the employee role id
   * @return true if the role exists and false otherwise
   */
  public boolean existsRole(String id) {
    for (MyUserRole userRole : roles)
      if (userRole.getId().equalsIgnoreCase(id)) return true;

    return false;
  }
}
