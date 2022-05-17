package app.domain.model.dto;

public class VaccinationCenterListDTO {
  private String name;
  private String address;
  private String email;
  private String phone;
  private String coordinatorName;

  public VaccinationCenterListDTO(String name, String address, String email, String phone,
      String coordinatorName) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.coordinatorName = coordinatorName;
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

  public String getCoordinatorName() {
    return coordinatorName;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccination Center data:\n");
    sb.append(String.format("Name: %s\n", this.name));
    sb.append(String.format("Address: %s\n", this.address));
    sb.append(String.format("Email: %s\n", this.email));
    sb.append(String.format("Phone number: %s\n", this.phone));
    sb.append(String.format("Coordinator: %s\n", this.getCoordinatorName()));

    return sb.toString();
  }
}
