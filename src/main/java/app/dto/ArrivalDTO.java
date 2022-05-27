package app.dto;

import java.util.Date;

public class ArrivalDTO {
  // TODO Implement WaitingRoom DTO with Name, Sex, Birth Date, SNS User Number and Phone Number.
  private String name;
  private char gender;
  private Date birthDate;
  private String snsUserNumber;
  private String phoneNumber;
  private String time;

  public ArrivalDTO(String name, char gender, Date birthDate, String snsUserNumber, String phoneNumber, String time) {
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
  public char getSex() {
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
}
