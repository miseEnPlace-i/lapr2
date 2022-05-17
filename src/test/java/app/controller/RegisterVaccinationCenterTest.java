package app.controller;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class RegisterVaccinationCenterTest {
  RegisterVaccinationCenterController controller =
      new RegisterVaccinationCenterController(new Company("designation"));
  String centerName = "Vaccination Center";
  Employee coordinator;

  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    coordinator = new Employee("Joana", "+351916478865", "email@email.com", "address",
        "000000000ZZ4", Constants.ROLE_COORDINATOR);
  }

  /**
   * To string method for tests purpose
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccination Center data:\n");
    sb.append(String.format("Name: %s\n", "name"));
    sb.append(String.format("Address: %s\n", "address"));
    sb.append(String.format("Email: %s\n", "example@gmail.com"));
    sb.append(String.format("Phone number: %s\n", "+351913456789"));
    sb.append(String.format("Fax number: %s\n", "+351913456788"));
    sb.append(String.format("Web address: %s\n", "https://www.teste.com"));
    sb.append(String.format("Opening hours: %s\n", "11:00"));
    sb.append(String.format("Closing hours: %s\n", "23:00"));
    sb.append(String.format("Slot duration: %s\n", "5"));
    sb.append(String.format("Maximum vaccines per slot: %s\n", "5"));
    sb.append(String.format("Coordinator: %s\n", "Joana"));

    return sb.toString();
  }


  /**
   * Check that getResourceName method is working properly
   */
  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assert centerName == controller.getResourceName();
  }

  /**
   * Check that it is not possible to create a vaccination center with null values
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullValuesNotAllowed() {
    controller.create(null, null, null, null, null, null, null, null, 0, 0, null);
  }

  /**
   * Check that it is not possible to create a vaccination center with empty values
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyValuesNotAllowed() {
    controller.create("", "", "", "", "", "", "", "", 0, 0, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with email invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidEmailThrowsException() {
    controller.create("name", "address", "emailaddress", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with phone invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidPhoneThrowsException() {
    controller.create("name", "address", "example@gmail.com", "913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with fax invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidFaxThrowsException() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "913456788",
        "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with website invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidWebsiteThrowsException() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "abc://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with slot duration invalid (x=<0)
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidSlotDurationThrowsException() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", -5, 5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with maximum vaccines per slot invalid (x=<0)
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidMaxVacSlotThrowsException() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", 5, -5, coordinator);
  }

  /**
   * Check that it is not possible to create a vaccination center with invalid coordinator
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidCoordinatorThrowsException() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", 5, 5, null);
  }

  /**
   * Check that it is possible to create a vaccination center with all valid values
   */
  @Test
  public void ensureValidValuesCreateNewCenter() {
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
  }

  /**
   * Check that StringifyData method is working properly
   */
  @Test
  public void ensureStringifyDataWorking() {
    String center = toString();
    controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
        "https://www.teste.com", "11:00", "23:00", 5, 5, coordinator);
    controller.save();
    assertEquals(controller.stringifyData(), center);
  }

  @Test
  public void ensureCoordinatorListWorking() {
    List<Employee> list = controller.getCoordinators();
    assertEquals(list.size(), 1);
    assertEquals(list.get(0), coordinator);
  }
}
