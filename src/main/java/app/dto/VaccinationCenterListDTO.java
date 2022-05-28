package app.dto;

public class VaccinationCenterListDTO {
  private String type;
  private String name;
  private String address;
  private String email;
  private String phone;
  private String openingHours;
  private String closingHours;

  public VaccinationCenterListDTO(String type, String name, String address, String email, String phone, String openingHours, String closingHours) {
    this.type = type;
    this.name = name;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.openingHours = openingHours;
    this.closingHours = closingHours;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getOpeningHours() {
    return openingHours;
  }

  public String getClosingHours() {
    return closingHours;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccination Center data:\n");
    sb.append(String.format("  Type: %s%n", this.type));
    sb.append(String.format("  Name: %s%n", this.name));
    sb.append(String.format("  Address: %s%n", this.address));
    sb.append(String.format("  Email: %s%n", this.email));
    sb.append(String.format("  Phone number: %s%n", this.phone));
    sb.append(String.format("  Schedule: %s - %s%n", this.openingHours, this.closingHours));

    return sb.toString();
  }
}
