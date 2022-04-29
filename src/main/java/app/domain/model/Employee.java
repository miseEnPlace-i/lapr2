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
  public Employee(String name, String phoneNumber, String email, String address, String citizenCard,
      String roleId) {
    this.id = 123456789;
    // TODO
  }

  public String getRoleId() {
    return roleId;
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
}
