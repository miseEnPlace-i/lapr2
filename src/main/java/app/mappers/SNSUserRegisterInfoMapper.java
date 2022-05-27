package app.mappers;

import app.domain.model.SNSUser;
import app.domain.model.dto.SNSUserRegisterInfoDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserRegisterInfoMapper {
    
    // Private constructor to prevent instantiation from other classes
    private SNSUserRegisterInfoMapper() {}

    // converts SNSUser to SNSUserRegisterInfoDTO
    public static SNSUserRegisterInfoDTO toDto(SNSUser user, String pwd, String role){
        return new SNSUserRegisterInfoDTO(user.getName(), user.getEmail(), pwd, role);
    }

}
