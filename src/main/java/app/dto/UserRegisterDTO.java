package app.dto;

/**
 * DTO for User Registration.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class UserRegisterDTO {
    private String email;
    private String password;

    public UserRegisterDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
