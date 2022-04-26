package app.domain.model;

public class User {

    // User name
    private String name;

    // User email
    private String email;

    // User password
    private String pwd;

    // User role id
    private String roleId;

    /**
     * Constructor for User.
     * 
     * @param name
     * @param email
     * @param pwd
     * @param roleId
     */
    public User(String name, String email, String pwd, String roleId) {
        this.name = name;
        this.email = email;
        this.pwd = pwd;
        this.roleId = roleId;
    }

    /**
     * Getter for name.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for email.
     * 
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for roleId.
     * 
     * @return roleId
     */
    public String getRoleId() {
        return roleId;
    }
}
