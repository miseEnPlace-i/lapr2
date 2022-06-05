package app.domain.model.dto;

import java.util.Date;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserDTO {

    String citizenCard;
    String snsNumber;
    String name;
    Date birthDay;
    char gender;
    String phoneNumber;
    String email;
    String address;

    public String getCitizenCard() {
        return this.citizenCard;
    }

    public String getSnsNumber() {
        return this.snsNumber;
    }

    public String getName() {
        return this.name;
    }

    public Date getBirthDay() {
        return this.birthDay;
    }

    public char getGender() {
        return this.gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }


    public SNSUserDTO(String citizenCard, String snsNumber, String name, Date birthDay, char gender,
      String phoneNumber, String email, String address) {

        this.citizenCard = citizenCard.toUpperCase();
        this.snsNumber = snsNumber;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        
    }
    
}
