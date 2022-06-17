package app.mapper;

import app.domain.model.SNSUser;
import app.dto.SNSUserRegisterInfoDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserRegisterInfoMapper {

  // Private constructor to prevent instantiation from other classes
  private SNSUserRegisterInfoMapper() {}

  // converts SNSUser to SNSUserRegisterInfoDTO
  public static SNSUserRegisterInfoDTO toDto(SNSUser user, String pwd, String role) {
    return new SNSUserRegisterInfoDTO(user.getCitizenCard(), user.getSnsNumber(), user.getName(), user.getBirthDay().toString(), user.getGender().toString(),
        user.getPhoneNumber(), user.getEmail(), user.getAddress(), pwd, role);
  }

}
