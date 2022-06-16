package app.controller;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.MyUserRole;
import app.domain.model.store.EmployeeRoleStore;

public class RegisterEmployeeControllerTest {
  Company company = new Company("designation", "12345");
  private RegisterEmployeeController ctrl =
      new RegisterEmployeeController(company);

  @Before
  public void setUp() {
    EmployeeRoleStore roleStore = company.getEmployeeRoleStore();
    roleStore.addEmployeeRole("COORDINATOR", "COORDINATOR");
    roleStore.addEmployeeRole("COORDINATOR2", "COORDINATOR2");
  }

  /**
   * Method toString for tests purpose
   */
  private String getEmployeeTestString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("ID: %s\n", "0000000001"));
    sb.append(String.format("Name: %s\n", "name"));
    sb.append(String.format("Phone number: %s\n", "+351913456789"));
    sb.append(String.format("Email: %s\n", "example@gmail.com"));
    sb.append(String.format("Address: %s\n", "street 1, 11-11 city"));
    sb.append(String.format("Citizen Card number: %s\n", "00000000"));
    sb.append(String.format("Role: %s\n", "COORDINATOR"));

    return sb.toString();
  }

  /**
   * Check that StringifyData method is working properly
   */
  @Test
  public void ensureStringifyDataWorking() {
    String empString = getEmployeeTestString();
    ctrl.create("name", "street", 1, "11-11", "city", "+351913456789",
        "example@gmail.com", "00000000", "COORDINATOR");
    ctrl.save();
    assertEquals(empString, ctrl.stringifyData());
  }

  /**
   * Check that it is possible to create a employee
   */
  @Test
  public void ensureEmployeeIsCreatedSuccessfully() {
    String name = "test";
    String street = "test";
    int number = 1;
    String door = "11-11";
    String city = "test";
    String phoneNumber = "+351913456789";
    String email = "test@email.com";
    String citizenCardNumber = "00000000";
    String roleId = "NURSE";
    ctrl.create(name, street, number, door, city, phoneNumber, email,
        citizenCardNumber, roleId);
  }

  /**
   * Check that it is possible to save a employee
   */
  @Test
  public void ensureSaveWorksAsExpected() {
    String name = "test";
    String street = "test";
    int number = 1;
    String door = "11-11";
    String city = "test";
    String phoneNumber = "+351913456789";
    String email = "test@email.com";
    String citizenCardNumber = "00000000";
    String roleId = "NURSE";
    ctrl.create(name, street, number, door, city, phoneNumber, email,
        citizenCardNumber, roleId);
    ctrl.save();
  }

  /**
   * Check that getResourceName method is working properly
   */
  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assertEquals(ctrl.getResourceName(), "Employee");
  }

  /**
   * Check that list employee roles is working properly
   */
  @Test
  public void ensureEmployeeRolesListWorking() {
    List<MyUserRole> list = ctrl.getEmployeeRoles();
    assertEquals(list.size(), 2);
  }
}
