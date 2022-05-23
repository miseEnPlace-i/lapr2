package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.store.VaccineTechnologyStore;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class RegisterVaccineTypeControllerTest {
  Company company = new Company("designation");
  RegisterNewVaccineTypeController controller = new RegisterNewVaccineTypeController(company);
  String vaccineType = "Vaccine Type";


  @Before
  public void setUp() {
    VaccineTechnologyStore store = company.getVaccineTechnologyStore();
    store.addVaccineTechnology("LIVE_ATTENUATED_TECHNOLOGY");
  }

  /**
   * To string method for tests purpose
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccine type specifications:\n");
    sb.append(String.format("Code: %s\n", "12345"));
    sb.append(String.format("Description: %s\n", "test"));
    sb.append(String.format("Technology: %s\n", "LIVE_ATTENUATED_TECHNOLOGY"));


    return sb.toString();
  }

  /**
   * Check that getResourceName method is working properly
   */
  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assertEquals(controller.getResourceName(), vaccineType);
  }

  /**
   * Check that it is not possible to create a vaccine type with null values
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullValuesNotAllowed() {
    controller.create(null, null, null);
  }

  /**
   * Check that it is not possible to create a vaccine type with empty values
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyValuesNotAllowed() {
    controller.create("", "", "");
  }

  /**
   * Check that it is not possible to create a vaccine type with code invalid (less than 5 char)
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCodeIsCorrect() {
    controller.create("123", "description", "technology");
  }

  /**
   * Check that it is not possible to create a vaccine type with code invalid (more than 5 char)
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCode2IsCorrect() {
    controller.create("123456", "description", "technology");
  }

  /**
   * Check that it is possible to create a vaccine type with all valid values
   */
  @Test
  public void ensureValidValuesCreateNewVaccineType() {
    controller.create("12345", "test", "LIVE_ATTENUATED_TECHNOLOGY");
  }

  /**
   * Check that StringifyData method is working properly
   */
  @Test
  public void ensureStringifyDataWorking() {
    String vaccineType = toString();
    controller.create("12345", "test", "LIVE_ATTENUATED_TECHNOLOGY");
    controller.save();
    assertEquals(controller.stringifyData(), vaccineType);
  }
}
