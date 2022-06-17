package app.domain.model;

import java.io.Serializable;
import app.service.FormatVerifier;

public class Address implements Serializable {
  private String street;
  private int doorNumber;
  private String postalCode;
  private String city;

  public Address(String street, int doorNumber, String postalCode,
      String city) {
    if (street == null || street.isEmpty()) {
      throw new IllegalArgumentException("Street is not valid.");
    }

    if (doorNumber < 0) {
      throw new IllegalArgumentException("Number is not valid.");
    }

    if (postalCode == null || postalCode.isEmpty()) {
      throw new IllegalArgumentException("Postal code is not valid.");
    }

    if (city == null || city.isEmpty()) {
      throw new IllegalArgumentException("City is not valid.");
    }

    this.street = street;
    this.doorNumber = doorNumber;
    setPostalCode(postalCode);
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public int getNumber() {
    return doorNumber;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setPostalCode(String postalCode) {
    if (!FormatVerifier.validatePostalCode(postalCode))
      throw new IllegalArgumentException("Invalid postal code.");
    this.postalCode = postalCode;
  }

  @Override
  public String toString() {
    return street + " " + doorNumber + ", " + postalCode + " " + city;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Address)) return false;
    Address otherAddress = (Address) other;
    return this.street.equals(otherAddress.street)
        && this.doorNumber == otherAddress.doorNumber
        && this.postalCode.equals(otherAddress.postalCode)
        && this.city.equals(otherAddress.city);
  }
}
