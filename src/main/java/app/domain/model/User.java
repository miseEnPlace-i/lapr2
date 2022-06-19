package app.domain.model;

import java.io.Serializable;

/**
 * This class is needed to store duplicate users to workaround the AuthFacade not being serializable.
 * 
 * Given that, this class is used to store the user and the user's serialized version. On App startup all the users
 * stored are loaded in the AuthFacade. (This causes performance issues, but it is the only way to avoid the AuthFacade)
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class User implements Serializable {
  private String name;
  private String password;
  private String email;
  private String roleId;

  public User(String name, String email, String password, String roleId) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getEmail() {
    return email;
  }

  public String getRoleId() {
    return roleId;
  }
}
