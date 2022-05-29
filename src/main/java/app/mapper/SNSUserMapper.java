package app.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import app.domain.shared.Gender;
import app.dto.SNSUserDTO;

/**
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserMapper {

  // Private constructor to prevent instantiation from other classes
  private SNSUserMapper() {}

  // converts userData to SNSUserDTO
  public static SNSUserDTO toDto(String[] userData) throws ParseException {

    // convert String to date
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date birthDay = df.parse(userData[2]);

    Gender gender = Gender.valueOf(userData[1]);

    // userData: Name, Sex, Birth Date, Address, Phone Number, E-mail, SNS User Number and Citizen Card Number.
    return new SNSUserDTO(userData[7], userData[6], userData[0], birthDay, gender, userData[4], userData[5], userData[3]);
  }
}
