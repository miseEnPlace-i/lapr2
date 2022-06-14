package app.domain.model;

import java.io.Serializable;

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
