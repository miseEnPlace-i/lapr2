package app.dto;

/**
 * DTO to send the user's password to the phone number or email.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class UserNotificationDTO {
    private String email;
    private String phoneNumber;
    private String message;

    public UserNotificationDTO(String email, String phoneNumber, String message) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getMessage() {
        return this.message;
    }
}
