package app.domain.model;

import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

/**
 * Employee model class.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Employee extends User {
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
    super(new Email(email), new Password("pwd"), name);

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
