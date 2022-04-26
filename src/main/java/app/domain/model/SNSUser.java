package app.domain.model;

import java.util.Date;

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
    public SNSUser(String citizenCard, String snsNumber, String name, Date birthDay, char gender, String phoneNumber, String email) {
        validateAge(birthDay);

        this.snsNumber = snsNumber;
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // TODO: Getters
    public String getEmail() {
        return email;
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
