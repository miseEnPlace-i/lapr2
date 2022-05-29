package app.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import app.domain.shared.Gender;

public class ArrivalDTO {
  private String name;
  private Gender gender;
  private String birthDate;
  private String snsUserNumber;
  private String phoneNumber;
  private String time;

  public ArrivalDTO(String name, Gender gender, String birthDate, String snsUserNumber, String phoneNumber, String time) {
    this.name = name;
    this.gender = gender;
    this.birthDate = birthDate;
    this.snsUserNumber = snsUserNumber;
    this.phoneNumber = phoneNumber;
    this.time = time;
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
  public Date getBirthDate() {
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    sb.append(String.format("Name: %s%n", name));
    sb.append(String.format("Gender: %s%n", gender));
    sb.append(String.format("BirthDate: %s%n", sdf.format(birthDate)));
    sb.append(String.format("SNS User Number: %s%n", snsUserNumber));
    sb.append(String.format("Phone Number: %s%n", phoneNumber));
    sb.append(String.format("Time: %s%n", time));

    return sb.toString();
  }
}
