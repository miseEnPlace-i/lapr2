package app.mappers;

import app.domain.model.SNSUser;
import app.domain.model.dto.SNSUserRegisterInfoDTO;
import app.service.PasswordGenerator;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserRegisterInfoMapper {
    
    // Private constructor to prevent instantiation from other classes
    private SNSUserRegisterInfoMapper() {}

    // converts SNSUser to SNSUserRegisterInfoDTO
    public SNSUserRegisterInfoDTO toDto(SNSUser user){
        return new SNSUserRegisterInfoDTO(user.getName(), user.getEmail(), PasswordGenerator.generatePwd(), "SNS_USER");
    }

}
