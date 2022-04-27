package app.domain.model;

import java.util.Date;

/**
 * SNSUser model class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUser {

    // Citizen Card
    private String citizenCard;

    // SNS number
    private String snsNumber;

    // SNS User name
    private String name;

    // SNS User birth day
    private Date birthDay;

    // SNS User gender
    private char gender;

    // SNS User phone number
    private String phoneNumber;

    // SNS User email
    private String email;

    // SNS User address
    private String address;

    /**
     * Constructor for SNSUser.
     * 
     * @param snsNumber
     * @param name
     * @param birthDay
     * @param gender
     * @param phoneNumber
     * @param email
     */
    public SNSUser(String citizenCard, String snsNumber, String name, Date birthDay, char gender, String phoneNumber, String email, String address) {
        validateAge(birthDay);

        this.snsNumber = snsNumber;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    // Getters
    public String getCitizenCard() {
        return citizenCard;
    }

    public String getSnsNumber() {
        return snsNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof SNSUser)) return false;

        SNSUser other = (SNSUser) obj;

        // Fields email, phone number, citizen card & SNS number should be unique for each SNS User
        if (this.email.equals(other.email)) return true;
        if (this.phoneNumber.equals(other.phoneNumber)) return true;
        if (this.citizenCard.equals(other.phoneNumber)) return true;
        if (this.snsNumber.equals(other.phoneNumber)) return true;

        return false;
    }

    /**
     * Validates the age given a birthday.
     * Throws IllegalArgumentException.
     * 
     * @param birthDay
     */
    private static void validateAge(Date birthDay) {
        // checks if the birthday is in the future
        if (birthDay.after(new Date())) {
            throw new IllegalArgumentException("Birthdays can't be in the future.");
        }

        // check if the birthday is more than 150 years ago
        if (birthDay.before(new Date(new Date().getTime() - (150 * 365 * 24 * 60 * 60 * 1000)))) {
            throw new IllegalArgumentException("Birthdays can't be more than 150 years ago.");
        }
    }

}
