package app.domain.model;

import java.util.Date;
import app.domain.shared.Gender;
import app.dto.SNSUserDTO;
import app.service.CCFormatVerifier;
import app.service.FormatVerifier;
import app.service.TimeUtils;

/**
 * SNSUser model class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
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
  private Date birthDay;

  // SNS User gender
  private Gender gender;

  // SNS User phone number
  private String phoneNumber;

  // SNS User email
  private String email;

  // SNS User address
  private String address;

  private HealthData userHealthData;

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
  public SNSUser(String citizenCard, String snsNumber, String name, Date birthDay, Gender gender, String phoneNumber, String email, String address) {
    validateBirthday(birthDay);
    validateCitizenCard(citizenCard);
    validateSNSNumber(snsNumber);
    validateName(name);
    validatePhoneNumber(phoneNumber);
    validateEmail(email);
    validateAddress(address);

    this.citizenCard = citizenCard.toUpperCase();
    this.snsNumber = snsNumber;
    this.name = name;
    this.birthDay = birthDay;
    this.gender = gender;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.address = address;

    this.userHealthData = new HealthData(this);
  }

  public SNSUser(SNSUserDTO snsUserDTO) {
    validateBirthday(snsUserDTO.getBirthDay());
    validateCitizenCard(snsUserDTO.getCitizenCard());
    validateSNSNumber(snsUserDTO.getSnsNumber());
    validateName(snsUserDTO.getName());
    validatePhoneNumber(snsUserDTO.getPhoneNumber());
    validateEmail(snsUserDTO.getEmail());
    validateAddress(snsUserDTO.getAddress());

    this.citizenCard = snsUserDTO.getCitizenCard().toUpperCase();
    this.snsNumber = snsUserDTO.getSnsNumber();
    this.name = snsUserDTO.getName();
    this.birthDay = snsUserDTO.getBirthDay();
    this.gender = snsUserDTO.getGender();
    this.phoneNumber = snsUserDTO.getPhoneNumber();
    this.email = snsUserDTO.getEmail();
    this.address = snsUserDTO.getAddress();

    this.userHealthData = new HealthData(this);
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

  public Gender getGender() {
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

  // public Vaccine getLastTakenVaccineFromType(VaccineType vaccineType) {
  //   return this.userHealthData.getLastVaccineTakenWithType(vaccineType);
  // }

  public int getAge() {
    return TimeUtils.calculateAge(birthDay);
  }

  public HealthData getUserHealthData() {
    return this.userHealthData;
  }

  // public Vaccine getLastTakenVaccineFromType(VaccineType vaccineType) {
  // return userHealthData.getLastVaccineTakenWithType(vaccineType);
  // }

  // public boolean hasTakenAnyVaccineFromVaccineType(VaccineType vaccineType) {
  // if (getLastTakenVaccineFromType(vaccineType) == null) return false;
  // else return true;
  // }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof SNSUser)) return false;

    SNSUser other = (SNSUser) obj;

    // Fields email, phone number, citizen card & SNS number should be unique for
    // each SNS User
    if (this.email.equals(other.email)) return true;
    if (this.phoneNumber.equals(other.phoneNumber)) return true;
    if (this.citizenCard.equals(other.citizenCard)) return true;
    if (this.snsNumber.equals(other.snsNumber)) return true;

    return false;
  }

  /**
   * Validates the age given a birthday. Throws IllegalArgumentException.
   * 
   * @param birthDay
   */
  private static void validateBirthday(Date birthDay) {
    // checks if the birthday is in the future
    if (birthDay.after(new Date())) {
      throw new IllegalArgumentException("Birthday cannot be in the future.");
    }

    // check if the birthday is more than MAX_AGE years ago
    int age = TimeUtils.calculateAge(birthDay);

    if (age > MAX_AGE) {
      throw new IllegalArgumentException(String.format("Birthday cannot be more than %d years ago.", MAX_AGE));
    }
  }

  private static void validateCitizenCard(String citizenCard) {
    CCFormatVerifier verifier = new CCFormatVerifier();

    if (!verifier.validate(citizenCard)) throw new IllegalArgumentException("Citizen Card is not valid.");
  }

  private static void validateSNSNumber(String snsNumber) {
    if (!FormatVerifier.validateSNSNumber(snsNumber)) throw new IllegalArgumentException("SNS Number is not valid.");
  }

  private static void validateName(String name) {
    // should not be empty
    // regex: ^.+$
    if (!name.matches("^.+$")) {
      throw new IllegalArgumentException("Name is not valid.");
    }
  }

  private static void validatePhoneNumber(String phoneNumber) {
    if (!FormatVerifier.validatePhoneNumber(phoneNumber)) throw new IllegalArgumentException("Phone Number is not valid.");
  }

  private static void validateEmail(String email) {
    if (!FormatVerifier.validateEmail(email)) throw new IllegalArgumentException("Email is not valid.");
  }

  private static void validateAddress(String address) {
    // should not be empty
    // regex: ^.+$
    if (!address.matches("^.+$")) {
      throw new IllegalArgumentException("Address is not valid.");
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("SNS User name: %s\n", this.name));
    sb.append(String.format("Citizen card number: %s\n", this.citizenCard));
    sb.append(String.format("SNS number: %s\n", this.snsNumber));
    sb.append(String.format("Birthday: %s\n", TimeUtils.dateToString(this.birthDay)));
    sb.append(String.format("Gender: %s\n", this.gender.toString()));
    sb.append(String.format("Phone number: %s\n", this.phoneNumber));
    sb.append(String.format("Email: %s\n", this.email));
    sb.append(String.format("Address: %s\n", this.address));

    return sb.toString();
  }

  public void addAppointmentToList(Appointment appointment) {
    this.userHealthData.addAppointment(appointment);
  }

  public boolean hasAppointmentForVaccineType(VaccineType vaccineType) {
    return this.userHealthData.hasAppointmentForVaccineType(vaccineType);
  }

  public boolean hasAppointmentForVaccineType(VaccineType vaccineType, String number) {
    return this.userHealthData.hasAppointmentForVaccineType(vaccineType);
  }
}
