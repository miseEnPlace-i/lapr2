package app.domain.model;

import app.service.CCFormatVerifier;

/**
 * Employee model class.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class Employee {
  String id = "";
  String name = "";
  String phoneNumber = "";
  String email = "";
  String address = "";
  String citizenCard = "";
  String roleId = "";

  /**
   * Constructor for Employee.
   * 
   * @param name the employee name
   * @param phoneNumber the employee phoneNumber
   * @param email the employee email
   * @param address the employee address
   * @param citizenCard the employee citizenCard
   * @param roleId the employee roleId
   */
  public Employee(String id, String name, String phoneNumber, String email, String address,
      String citizenCard, String roleId) {
    this.id = id;
    setName(name);
    setPhoneNumber(phoneNumber);
    setEmail(email);
    setAddress(address);
    setCitizenCard(citizenCard);
    setRoleId(roleId);
  }

  /**
   * Checks if the employee has the given role.
   * 
   * @param roleId the employee roleId
   * @return true if the employee has the given role, false otherwise
   */
  public boolean hasRoleId(String roleId) {
    return roleId.equals(this.roleId);
  }

  /**
   * Checks if the employee is the same as the given employee.
   * 
   * @param employee the employee to compare
   * 
   * @return true if the employee is the same as the given employee, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Employee)) return false;

    Employee other = (Employee) obj;

    return this.id == other.id;
  }

  /**
   * Gets all the information about the employee.
   * 
   * @return the employee information
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("ID: %s\n", id));
    sb.append(String.format("Name: %s\n", this.name));
    sb.append(String.format("Phone number: %s\n", this.phoneNumber));
    sb.append(String.format("Email: %s\n", this.email));
    sb.append(String.format("Address: %s\n", this.address));
    sb.append(String.format("Citizen Card number: %s\n", this.citizenCard));
    sb.append(String.format("Role: %s\n", this.roleId));

    return sb.toString();
  }

  /**
   * Gets the employee id.
   * 
   * @return the employee id
   */
  public String getId() {
    return String.format("%010d", this.id);
  }

  /**
   * Gets the employee name.
   * 
   * @return the employee name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the employee email.
   * 
   * @return the employee email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Gets the employee roleId.
   * 
   * @return the employee roleId
   */
  public String getRoleId() {
    return roleId;
  }

  /**
   * Sets the employee name.
   * 
   * @param name the employee name
   * 
   * @throws IllegalArgumentException if the name is null, empty or not valid
   */
  private void setName(String name) {
    if (name == null) throw new IllegalArgumentException("Name cannot be null");
    if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");

    this.name = name;
  }

  /**
   * Sets the employee phoneNumber.
   * 
   * @param phoneNumber the employee phoneNumber
   * 
   * @throws IllegalArgumentException if the phoneNumber is null, empty or not valid
   */
  private void setPhoneNumber(String phoneNumber) {
    if (phoneNumber == null) throw new IllegalArgumentException("Phone number cannot be null");
    if (phoneNumber.isEmpty()) throw new IllegalArgumentException("Phone number cannot be empty");

    if (!phoneNumber
        .matches("^(\\+\\d{1,3}?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$"))
      throw new IllegalArgumentException("Phone Number is not valid");

    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets the employee email.
   * 
   * @param email the employee email
   * 
   * @throws IllegalArgumentException if the email is null, empty or not valid
   */
  private void setEmail(String email) {
    if (email == null) throw new IllegalArgumentException("Email cannot be null");
    if (email.isEmpty()) throw new IllegalArgumentException("Email cannot be empty");

    if (!email.matches(
        "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"))
      throw new IllegalArgumentException("Email is not valid");

    this.email = email;
  }

  /**
   * Sets the employee address.
   * 
   * @param address the employee address
   * 
   * @throws IllegalArgumentException if the address is null, empty or not valid
   */

  private void setAddress(String address) {
    if (address == null) throw new IllegalArgumentException("Address cannot be null");
    if (address.isEmpty()) throw new IllegalArgumentException("Address cannot be empty");

    this.address = address;
  }

  /**
   * Sets the employee citizenCard.
   * 
   * @param citizenCard the employee citizenCard
   * 
   * @throws IllegalArgumentException if the citizenCard is null, empty or not valid
   */
  private void setCitizenCard(String citizenCard) {
    CCFormatVerifier formatVerifier = new CCFormatVerifier();

    if (citizenCard == null) throw new IllegalArgumentException("Citizen Card cannot be null.");
    if (citizenCard.isEmpty()) throw new IllegalArgumentException("Citizen Card cannot be empty.");

    if (!formatVerifier.validate(citizenCard))
      throw new IllegalArgumentException("Citizen Card is not valid.");

    this.citizenCard = citizenCard;
  }

  /**
   * Sets the employee roleId.
   * 
   * @param roleId the employee roleId
   * 
   * @throws IllegalArgumentException if the roleId is is null, empty or not valid
   */
  private void setRoleId(String roleId) {
    if (roleId == null) throw new IllegalArgumentException("Role id cannot be null");
    if (roleId.isEmpty()) throw new IllegalArgumentException("Role id cannot be empty");

    this.roleId = roleId;
  }
}
