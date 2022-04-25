package app.domain.model;

import java.util.Date;

public class SNSUser {

    // SNS number
    private String snsNumber;

    // SNS User name
    private String name;

    // SNS User birth date
    private Date birthDate;

    // SNS User gender
    private char gender;

    // SNS User phone number
    private String phoneNumber;

    // SNS User email
    private String email;

    public SNSUser(String snsNumber, String name, Date birthDate, char gender, String phoneNumber, String email) {
        this.snsNumber = snsNumber;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        
    }

    private static void validateAge(Date birthDate) {
        
    }

}
