package app.dto;

import java.util.Date;
import app.domain.model.Address;
import app.domain.shared.Gender;

/**
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserDTO {
  String citizenCard;
  String snsNumber;
  String name;
  Date birthDay;
  Gender gender;
  String phoneNumber;
  String email;
  Address address;

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

  public Gender getGender() {
    return this.gender;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public String getEmail() {
    return this.email;
  }

  public Address getAddress() {
    return this.address;
  }


  public SNSUserDTO(String citizenCard, String snsNumber, String name, Date birthDay, Gender gender, String phoneNumber, String email, Address address) {
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
