package app.mappers;

import app.domain.model.SNSUser;
import app.domain.model.dto.UserDTO;
import app.service.PasswordGenerator;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UserMapper {
    
    // Private constructor to prevent instantiation from other classes
    private UserMapper() {}

    // converts SNSUser to UserDTO
    public UserDTO toDto(SNSUser user){
        return new UserDTO(user.getName(), user.getEmail(), PasswordGenerator.generatePwd(), "SNS_USER");
    }

}
