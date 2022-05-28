package app.mapper;

import app.dto.UserNotificationDTO;

/**
 * Mapper for UserNotificationDTO.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class UserNotificationMapper {
    private UserNotificationMapper() {}

    /**
     * Creates an instance of UserNotificationDTO.
     * 
     * @param email the user email.
     * @param phoneNumber the user phone number.
     * @param pwd the u1ser passsword.
     * @return UserNotificationDTO
     */
    public static UserNotificationDTO toDto(String email, String phoneNumber, String pwd) {
        return new UserNotificationDTO(email, phoneNumber, pwd);
    }
}
