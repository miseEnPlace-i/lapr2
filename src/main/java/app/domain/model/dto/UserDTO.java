package app.domain.model.dto;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UserDTO {

    private String name;
    private String email;
    private String pass;
    private String role;

    public UserDTO(String name, String email, String pass, String role){
            this.name = name;
            this.email = email;
            this.pass = pass;
            this.role = role;
        }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPass() {
        return this.pass;
    }

    public String getRole() {
        return this.role;
    }

}
