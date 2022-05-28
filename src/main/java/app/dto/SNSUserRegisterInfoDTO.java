package app.dto;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserRegisterInfoDTO {


  private String citizenCard;
  private String snsNumber;
  private String name;
  private String birthDay;
  private String gender;
  private String phoneNumber;
  private String email;
  private String address;
  private String pass;
  private String role;

    public SNSUserRegisterInfoDTO(String citizenCard, String snsNumber, String name, String birthDay, String gender,
    String phoneNumber, String email, String address, String pass, String role){
        this.citizenCard = citizenCard;
        this.snsNumber = snsNumber;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.pass = pass;
        this.role = role;
        }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }


    @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("SNS User name: %s\n", this.name));
    sb.append(String.format("Citizen card number: %s\n", this.citizenCard));
    sb.append(String.format("SNS number: %s\n", this.snsNumber));
    sb.append(String.format("Birthday: %s\n", this.birthDay));
    sb.append(String.format("Gender: %s\n", this.gender == "m" ? "Male" : "Female"));
    sb.append(String.format("Phone number: %s\n", this.phoneNumber));
    sb.append(String.format("Email: %s\n", this.email));
    sb.append(String.format("Address: %s\n", this.address));
    sb.append(String.format("Password: %s\n", this.pass));
    sb.append(String.format("Role: %s\n", this.role));

    return sb.toString();
  }

}
