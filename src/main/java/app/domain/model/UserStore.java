package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class UserStore {

    // User List
    private List<User> users = new ArrayList<User>();

    /**
     * Constructor for UserStore.
     */
    public UserStore() {
    }

    public void createUser() {
    }

    public void validateUser() {
    }

    /**
     * Inserts a user to the list.
     * 
     * @param user
     */
    public void saveUser(User user) {
        users.add(user);
    }

    public void checkDuplicates() {
    }
}
