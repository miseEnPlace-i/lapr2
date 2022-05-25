package app.domain.model.dto;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserRegisterInfoDTO {

    private String name;
    private String email;
    private String pass;
    private String role;

    public SNSUserRegisterInfoDTO(String name, String email, String pass, String role){
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

    @Override
    public String toString(){
        return "Registered User:\nName: " + this.name + "\nE-mail: " + this.email
        + "\nPassword: " + this.pass + "\nRole: " + this.role;
    }

}
