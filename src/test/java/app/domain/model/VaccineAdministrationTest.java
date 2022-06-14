package app.domain.model;

import static org.junit.Assert.assertEquals;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.shared.Gender;
import app.utils.Time;

/**
 * Vaccine Administration Tests
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineAdministrationTest {
  private VaccineType vaccineType;
  private Vaccine vaccine;
  private SNSUser user1;
  private VaccinationCenter vaccinationCenter;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "00000000", "ROLE");
    vaccineType = new VaccineType("12345", "description", "technology");
    vaccine = new Vaccine("pfizer", "123456", "pfizer", vaccineType);
    Time openingHours = new Time(10, 0);
    Time closingHours = new Time(11, 0);
    Slot slot = new Slot(5, 5);

    vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", openingHours,
        closingHours, slot, coordinator, "ages", "ars");

    user1 = new SNSUser("00000000", "123456788", "name", new Date(), Gender.MALE, "+351212345675", "email1@email.com", "address");
  }

  @Test
  public void ensureThatIsPossibleToCreateVaccineAdministration() {
    new VaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNullIsNotAllowed() {
    new VaccineAdministration(null, null, null, 0, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyLotNumberIsNotAllowed() {
    new VaccineAdministration(user1, vaccine, "", 1, vaccinationCenter, Calendar.getInstance());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidLotNumberIsNotAllowed() {
    new VaccineAdministration(user1, vaccine, "aaaaa", 1, vaccinationCenter, Calendar.getInstance());
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeDoseNumberIsNotAllowed() {
    new VaccineAdministration(user1, vaccine, "12345-12", 0, vaccinationCenter, Calendar.getInstance());
  }

  @Test
  public void ensureThatIsPossibleToCompareVaccineAdministrations() {
    VaccineAdministration va1 = new VaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());
    VaccineAdministration va2 = new VaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    assertEquals(va1.compareTo(va2), 0);
  }
}
