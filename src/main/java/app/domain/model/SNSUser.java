package app.domain.model;

import java.util.Calendar;

/**
 * SNSUser model class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUser {

    // Max age a user can have
    private static final int MAX_AGE = 150;

    // Citizen Card
    private String citizenCard;

    // SNS number
    private String snsNumber;

    // SNS User name
    private String name;

    // SNS User birth day
    private Calendar birthDay;

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
    public SNSUser(String citizenCard, String snsNumber, String name, Calendar birthDay, char gender,
            String phoneNumber, String email, String address) {
        validateAge(birthDay);

        this.citizenCard = citizenCard;
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

    public Calendar getBirthDay() {
        return birthDay;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof SNSUser))
            return false;

        SNSUser other = (SNSUser) obj;

        // Fields email, phone number, citizen card & SNS number should be unique for
        // each SNS User
        if (this.email.equals(other.email))
            return true;
        if (this.phoneNumber.equals(other.phoneNumber))
            return true;
        if (this.citizenCard.equals(other.citizenCard))
            return true;
        if (this.snsNumber.equals(other.snsNumber))
            return true;

        return false;
    }

    /**
     * Validates the age given a birthday.
     * Throws IllegalArgumentException.
     * 
     * @param birthDay
     */
    private static void validateAge(Calendar birthDay) {
        // checks if the birthday is in the future
        if (birthDay.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Birthday cannot be in the future");
        }

        // check if the birthday is more than MAX_AGE years ago
        int age = CalendarUtils.calculateAge(birthDay);

        if (age > MAX_AGE) {
            throw new IllegalArgumentException(String.format("Birthday cannot be more than %d years ago", MAX_AGE));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("SNS User name: %s\n", this.name));
        sb.append(String.format("Citizen card number: %s\n", this.citizenCard));
        sb.append(String.format("SNS number: %s\n", this.snsNumber));
        sb.append(String.format("Birthday: %s\n", CalendarUtils.calendarToString(this.birthDay)));
        sb.append(String.format("Gender: %s\n", this.gender == 'm' ? "Male" : "Female"));
        sb.append(String.format("Phone number: %s\n", this.phoneNumber));
        sb.append(String.format("Email: %s\n", this.email));
        sb.append(String.format("Address: %s\n", this.address));

        return sb.toString();
    }

}
