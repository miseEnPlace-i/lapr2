package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.utils.Time;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class VaccinationCenterTest {
  Employee coordinator;
  VaccinationCenter center;
  VaccineType vaccineType;
  Time openingHours;
  Time closingHours;
  Slot slot;

  @Before
  public void setUp() {
    coordinator = new Employee("00000001", "Joana", "+351916478865", "email@email.com", new Address("street", 1, "111-11", "city"), "00000000",
        Constants.ROLE_COORDINATOR);
    vaccineType = new VaccineType("12345", "description", "technology");

    openingHours = new Time(8, 0);
    closingHours = new Time(19, 0);
    slot = new Slot(5, 10);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with null values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullIsNotAllowedInCommunityMassVaccinationCenters() {
    new CommunityMassVaccinationCenter(null, null, null, null, null, null, null, null, null, null, null);

  }

  /**
   * Check that it is not possible to create a Vaccination Center with null values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullIsNotAllowedInHealthCareCenters() {
    new HealthCareCenter(null, null, null, null, null, null, null, null, null, null, null, null);
  }

  /**
   * Check that it is not possible to create a Vaccination Center with empty values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyIsNotAllowed() {
    Employee coordinator = new Employee("", "", "", "", null, "", "");

    new HealthCareCenter("", null, "", "", "", "", null, null, null, coordinator, "", "");
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the email address invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmailIsCorrect() {
    new HealthCareCenter("Centro", new Address("street", 1, "11-11", "city"), "vacinacaoportoAgmail.com", "+351912345678", "+351-123-1234567",
        "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the phone number invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensurePhoneNumberIsCorrect() {
    new HealthCareCenter("Centro", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com", "91919191", "+351-123-1234567",
        "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the fax number invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureFaxNumberIsCorrect() {
    new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com", "+351912345678", "+351-123-12345",
        "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
  }

  /**
   * Check that it is not possible to create a Vaccination Center with the website address invalid
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureWebsiteAddressIsCorrect() {
    new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567",
        "abc://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
  }

  /**
   * Check that it is possible to create a Vaccination Center with all valid parameters.
   */
  @Test
  public void ensureItsPossibleToCreateVaccinationCenter() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    assertNotNull(center);
  }

  /**
   * Check that it is possible to create a Vaccination Center with all valid parameters.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureItIsNotPossibleToCreateVaccinationCenterWithClosingHoursBeforeOpening() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto.com", closingHours, openingHours, slot, this.coordinator, "a", "a");

    assertNotNull(center);
  }

  /**
   * Check that two different centers (following the predefined rules) are not equal
   */
  @Test
  public void ensureTwoCentersAreDifferent() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Lisboa", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    VaccinationCenter center2 = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto2@gmail.com",
        "+351912345689", "+351223456999", "https://www.centrovacinaoporto2.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    assertNotEquals(center, center2);
  }

  /**
   * Check that that two centers with the same email are the same
   */
  @Test
  public void ensureSameEmailEqualsTrue() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Lisboa", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    VaccinationCenter center2 = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345689", "+351223456999", "https://www.centrovacinaoporto2.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    assertEquals(center, center2);
  }

  /**
   * Check that that two centers with the phone number are the same
   */
  @Test
  public void ensureSamePhoneNumberEqualsTrue() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    VaccinationCenter center2 = new HealthCareCenter("Centro Vacinação Lisboa", new Address("street", 1, "11-11", "city"), "vacinacaoporto2@gmail.com",
        "+351912345678", "+351223456999", "https://www.centrovacinaoporto2.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    assertEquals(center, center2);
  }

  /**
   * Check that that two centers with the fax number are the same
   */
  @Test
  public void ensureSameNameEqualsTrue() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345688", "+351223456789", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    VaccinationCenter center2 = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto2@gmail.com",
        "+351912345678", "+351223456789", "https://www.centrovacinaoporto2.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    assertEquals(center, center2);
  }

  /**
   * Check that two centers with everything the same are equal even with different types
   */
  @Test
  public void ensureEqualCentersTrue() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456799", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");

    VaccinationCenter center2 =
        new CommunityMassVaccinationCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com", "+351912345678",
            "+351223456799", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, vaccineType);

    assertEquals(center, center2);
  }

  /**
   * Check that if there are not any vaccine administration registered getVacAdminFromYesterdayList returns an empty list
   */
  @Test
  public void ensureEmptyListWorks() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456799", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
    List<VaccineAdministration> emptyList = new ArrayList<>();

    assertEquals(emptyList, center.getVacAdminFromTodayList());
  }

  /**
   * Check that getVacAdminFromYesterdayList works
   */
  @Test
  public void ensureGetVacAdminFromYesterdayListMethodWorks() {
    VaccinationCenter center = new HealthCareCenter("Centro Vacinação Porto", new Address("street", 1, "11-11", "city"), "vacinacaoporto@gmail.com",
        "+351912345678", "+351223456799", "https://www.centrovacinaoporto.com", openingHours, closingHours, slot, this.coordinator, "a", "a");
    List<VaccineAdministration> list = new ArrayList<>();

    SNSUser snsUser = new SNSUser("12345678", "123456789", "name", Calendar.getInstance().getTime(), Gender.FEMALE, "+351923848576", "email@email.com", new Address("street", 2, "11111", "city"));
    Vaccine vac = new Vaccine("designation", "id", "brand", new VaccineType("12345", "description", "technology"));

    Calendar date = Calendar.getInstance();

    VaccineAdministration vacAdmin = new VaccineAdministration( snsUser , vac, "AAAAA-11", 2, center, date);
    center.addVaccineAdministrationToList(vacAdmin);

    list.add(vacAdmin);

    assertEquals(list, center.getVacAdminFromTodayList());
  }

}
