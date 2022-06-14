package app.dto;

import app.domain.model.Appointment;
import app.domain.shared.Gender;

public class ArrivalDTO {
  private String name;
  private Gender gender;
  private String birthDate;
  private String snsUserNumber;
  private String phoneNumber;
  private String time;
  private Appointment appointment;

  public ArrivalDTO(String name, Gender gender, String birthDate, String snsUserNumber, String phoneNumber, String time, Appointment appointment) {
    this.name = name;
    this.gender = gender;
    this.birthDate = birthDate;
    this.snsUserNumber = snsUserNumber;
    this.phoneNumber = phoneNumber;
    this.time = time;
    this.appointment = appointment;
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return String return the sex
   */
  public Gender getGender() {
    return gender;
  }

  /**
   * @return Date return the birthDate
   */
  public String getBirthDate() {
    return birthDate;
  }

  /**
   * @return String return the snsUserNumber
   */
  public String getSnsUserNumber() {
    return snsUserNumber;
  }

  /**
   * @return String return the phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @return String return the phoneNumber
   */
  public String getTime() {
    return time;
  }

  /**
   * @return Appointment return the appointment
   */
  public Appointment getAppointment() {
    return appointment;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(String.format("Name: %s%n", name));
    sb.append(String.format("Gender: %s%n", gender));
    sb.append(String.format("Birth Date: %s%n", birthDate));
    sb.append(String.format("SNS User Number: %s%n", snsUserNumber));
    sb.append(String.format("Phone Number: %s%n", phoneNumber));
    sb.append(String.format("Arrival Time: %s", time));

    return sb.toString();
  }
}
