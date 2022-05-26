package app.mappers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import app.dto.SNSUserDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserMapper {

    // Private constructor to prevent instantiation from other classes
    private SNSUserMapper() {}

    // converts userData to SNSUserDTO
    public static SNSUserDTO toDto(String[] userData) throws ParseException{ 
        
        //convert String to date
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDay = df.parse(userData[2]);

        //userData: Name, Sex, Birth Date, Address, Phone Number, E-mail, SNS User Number and Citizen Card Number.
        return new SNSUserDTO(userData[7], userData[6], userData[0], birthDay, userData[1].charAt(0), userData[4], userData[5], userData[3]);
    }
}
