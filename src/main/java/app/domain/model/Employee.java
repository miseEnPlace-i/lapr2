package app.domain.model;

/**
 * Employee model class.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Employee {
  int id = 0;
  String name = "";
  String phoneNumber = "";
  String email = "";
  String address = "";
  int citizenCard = 0;
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
  public Employee(String name, String phoneNumber, String email, String address, int citizenCard,
      String roleId) {
    this.id = 123456789;

    // TODO
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

  @Override
  public boolean equals(Object obj) {
    // TODO
    return false;
  }

  @Override
  public String toString() {
    // TODO
    return "";
  }

  /**
   * Gets the employee id.
   * 
   * @return the employee id
   */
  public int getId() {
    return id;  
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
   * Gets the employee phoneNumber.
   * 
   * @return the employee phoneNumber
   */
  public String getPhoneNumber() {
    return phoneNumber;
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
   * Gets the employee address.
   * 
   * @return the employee address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Gets the employee citizenCard.
   * 
   * @return the employee citizenCard
   */
  public int getCitizenCard() {
    return citizenCard;
  }

  /**
   * Gets the employee roleId.
   * 
   * @return the employee roleId
   */
  public String getRoleId() {
    return roleId;
  }
}
