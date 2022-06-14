package app.domain.model;

import java.io.Serializable;

public class MyUserRole implements Serializable {
  private String id;
  private String description;

  public MyUserRole(String id, String description) {
    this.id = id;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return description;
  }
}
