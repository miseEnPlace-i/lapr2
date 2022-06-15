package app.domain.model;

import app.service.FormatVerifier;

public class Address {
  private String street;
  private int number;
  private String postalCode;
  private String city;

  public Address(String street, int number, String postalCode, String city) {
    this.street = street;
    this.number = number;
    setPostalCode(postalCode);
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public int getNumber() {
    return number;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }

  @Override
  public String toString() {
    return street + " " + number + ", " + postalCode + " " + city;
  }

  public void setPostalCode(String postalCode) {
    if (!FormatVerifier.validatePostalCode(postalCode)) throw new IllegalArgumentException("Invalid postal code.");
    this.postalCode = postalCode;
  }
}
