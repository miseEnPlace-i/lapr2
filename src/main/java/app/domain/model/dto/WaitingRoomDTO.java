package app.domain.model.dto;

import java.util.Date;

public class WaitingRoomDTO {
  // TODO Implement WaitingRoom DTO with Name, Sex, Birth Date, SNS User Number and Phone Number.
  private String name;
  private String sex;
  private Date birthDate;
  private String snsUserNumber;
  private String phoneNumber;

  public WaitingRoomDTO(String name, String sex, Date birthDate, String snsUserNumber,
      String phoneNumber) {
    this.name = name;
    this.sex = sex;
    this.birthDate = birthDate;
    this.snsUserNumber = snsUserNumber;
    this.phoneNumber = phoneNumber;
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
    public String getSex() {
        return sex;
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

}
