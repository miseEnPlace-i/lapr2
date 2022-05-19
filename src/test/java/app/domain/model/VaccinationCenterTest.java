package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterTest {
  Employee coordinator;
  VaccinationCenter center;

  @Before
  public void setUp() {
    coordinator = new Employee("Joana", "+351916478865", "email@email.com", "address",
        "000000000ZZ4", Constants.ROLE_COORDINATOR);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with null values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullIsNotAllowed() {
    new VaccinationCenter(null, null, null, null, null, null, null, null, 0, 0, null);

  }

  /**
   * Check that it is not possible to create a Vaccination Center with empty values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyIsNotAllowed() {
    Employee coordinator = new Employee("", "", "", "", "", "");

    new VaccinationCenter("", "", "", "", "", "", "", "", 0, 0, coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the email address invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmailIsCorrect() {
    new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoportoAgmail.com", "+351912345678",
        "+351-123-1234567", "https://www.centrovacinaoporto.com", "8:00", "19:00", 5, 10,
        this.coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the phone number invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensurePhoneNumberIsCorrect() {
    new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoporto@gmail.com", "91919191",
        "+351-123-1234567", "https://www.centrovacinaoporto.com", "8:00", "19:00", 5, 10,
        this.coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the fax number invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureFaxNumberIsCorrect() {
    new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com",
        "+351912345678", "+351-123-12345", "https://www.centrovacinaoporto.com", "8:00", "19:00", 0,
        10, this.coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the website address invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureWebsiteAddressIsCorrect() {
    new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com",
        "+351912345678", "+351-123-1234567", "abc://www.centrovacinaoporto.com", "8:00", "19:00", 0,
        10, this.coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with invalid opening hours
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureOpenHoursIsCorrect() {
    new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com",
        "+351912345678", "+351-123-1234567", "https://www.centrovacinaoporto.com", "25:123",
        "19:00", 5, 10, this.coordinator);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with invalid closing hours
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureClosHoursIsCorrect() {
    new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com",
        "+351912345678", "+351-123-1234567", "https://www.centrovacinaoporto.com", "20:00", "30:60",
        5, 10, this.coordinator);
  }

  /**
   * Check that it is possible to create a Vaccination Center with all valid parameters.
   */
  @Test
  public void ensureItsPossibleToCreateVaccinationCenter() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertNotNull(center);
  }

  /**
   * Check that two different centers (following the predefined rules) are not equal
   */
  @Test
  public void ensureTwoCentersAreDifferent() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto2@gmail.com", "+351912345689", "+351223456999",
        "https://www.centrovacinaoporto2.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertNotEquals(center, center2);
  }

  /**
   * Check that that two centers with the same email are the same
   */
  @Test
  public void ensureSameEmailEqualsTrue() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345689", "+351223456999",
        "https://www.centrovacinaoporto2.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertEquals(center, center2);
  }

  /**
   * Check that that two centers with the phone number are the same
   */
  @Test
  public void ensureSamePhoneNumberEqualsTrue() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto2@gmail.com", "+351912345678", "+351223456999",
        "https://www.centrovacinaoporto2.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertEquals(center, center2);
  }

  /**
   * Check that that two centers with the fax number are the same
   */
  @Test
  public void ensureSameFaxNumberEqualsTrue() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345688", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto2@gmail.com", "+351912345678", "+351223456789",
        "https://www.centrovacinaoporto2.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertEquals(center, center2);
  }

  /**
   * Check that that two centers with the website address are the same
   */
  @Test
  public void ensureSameWebAddressEqualsTrue() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345688", "+351223456789",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto2@gmail.com", "+351912345678", "+351223456799",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertEquals(center, center2);
  }

  /**
   * Check that two centers with everything the same are equal
   */
  @Test
  public void ensureEqualCentersTrue() {
    VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456799",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    VaccinationCenter center2 = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
        "vacinacaoporto@gmail.com", "+351912345678", "+351223456799",
        "https://www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, this.coordinator);

    assertEquals(center, center2);
  }
}
