package app.domain.model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.User;
import pt.isep.lei.esoft.auth.AuthFacade;

public class UserStore implements Serializable {
  private List<User> users;

  public UserStore() {
    this.users = new ArrayList<User>();
  }

  public boolean addUser(String name, String email, String password, String roleId) {
    User user = new User(name, email, password, roleId);

    return users.add(user);
  }

  public void loadUsersToAuthFacade(AuthFacade authFacade) {
    for (User user : users)
      authFacade.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), user.getRoleId());
  }

  public List<User> getUsers() {
    return users;
  }
}
