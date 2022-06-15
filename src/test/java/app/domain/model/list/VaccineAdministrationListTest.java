package app.domain.model.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Address;
import app.domain.model.AdminProcess;
import app.domain.model.AdverseReaction;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.HealthData;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccineStore;
import app.domain.shared.Gender;
import app.dto.VaccineDTO;
import app.utils.Time;

/**
 * Vaccine Administration List Tests
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineAdministrationListTest {
  private VaccinationCenter vaccinationCenter;
  private VaccineType vaccineType;
  private VaccineAdministrationList vaccineAdministrationList;
  private VaccineStore vaccineStore;
  private Vaccine vaccine;
  private SNSUser user1;
  private SNSUser user2;
  private SNSUser user3;
  private SNSUser user4;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", new Address("street", 0, "11-1", "city"), "00000000", "ROLE");
    vaccineType = new VaccineType("12345", "description", "technology");
    vaccine = new Vaccine("pfizer", "123456", "pfizer", vaccineType);
    vaccineStore = new VaccineStore();
    AdminProcess adminProcess = new AdminProcess(0, 10, 2);
    vaccine.addAdminProc(adminProcess);
    vaccineStore.saveVaccine(vaccine);
    Time openingHours = new Time(10, 0);
    Time closingHours = new Time(11, 0);
    Slot slot = new Slot(5, 5);

    vaccinationCenter = new HealthCareCenter("name", new Address("street", 0, "11-1", "city"), "email@email.com", "+351212345678", "+351212345678",
        "http://www.site.com", openingHours, closingHours, slot, coordinator, "ages", "ars");

    user1 = new SNSUser("00000000", "123456788", "name", DateUtils.addDays(new Date(), -400), Gender.MALE, "+351212345675", "email1@email.com",
        new Address("street", 1, "11-1", "city"));
    // user2 = new SNSUser("185352901ZZ6", "123456789", "name", new Date(), Gender.MALE, "+351212345678",
    // "email2@email.com", new Address("street", 0, "11-1", "city"));
    // user3 = new SNSUser("191052469ZZ5", "123456787", "name", new Date(), Gender.MALE, "+351212345671",
    // "email3@email.com", new Address("street", 0, "11-1", "city"));
    // user4 = new SNSUser("332952754ZW7", "123456786", "name", new Date(), Gender.MALE, "+351212345670",
    // "email4@email.com", new Address("street", 0, "11-1", "city"));

    HealthData healthData = user1.getUserHealthData();

    vaccineAdministrationList = healthData.getVaccineAdministrationList();
  }

  @Test
  public void ensureThatVaccineAdministrationListIsCreated() {
    assertNotNull(vaccineAdministrationList);
  }

  @Test
  public void ensureThatIsPossibleToCreateVaccineAdministration() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    assertNotNull(vaccineAdministration);
  }

  @Test
  public void ensureThatIsPossibleToSaveVaccineAdministration() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    assertEquals(vaccineAdministrationList.size(), 1);
    assertEquals(vaccinationCenter.getVaccineAdministrations().size(), 1);
    assertEquals(vaccinationCenter.getWaitingRoom().size(), 0);
    assertEquals(vaccinationCenter.getRecoveryRoom().size(), 1);
    assertEquals(vaccinationCenter.getEvents().size(), 2);
  }

  @Test
  public void ensureThatIsPossibleToGetLastVaccineTakenByType() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    VaccineDTO expectedDto = vaccineAdministrationList.getLastTakenVaccineByVaccineType(vaccineType);
    Vaccine expected = vaccineStore.findVaccineById(expectedDto.getId());

    assertEquals(expected, vaccine);
  }

  @Test
  public void ensureThatIsPossibleToGetLastVaccineTakenByTypeWithoutGettingVaccine() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    VaccineType vaccineType2 = new VaccineType("54321", "description1", "technology2");

    assertNull(vaccineAdministrationList.getLastTakenVaccineByVaccineType(vaccineType2));
  }

  @Test
  public void ensureThatIsPossibleToGetNextDoseNumber() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    int expected = 2;
    int actual = vaccineAdministrationList.getNextDoseNumberOfVaccine(vaccine);

    assertEquals(expected, actual);
  }

  @Test
  public void ensureThatIsPossibleToGetNextDoseNumberForNewVaccine() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    Vaccine vaccine2 = new Vaccine("pfizer", "654321", "pfizer", vaccineType);

    int expected = 1;
    int actual = vaccineAdministrationList.getNextDoseNumberOfVaccine(vaccine2);

    assertEquals(expected, actual);
  }

  @Test
  public void ensureThatIsPossibleToGetEmptyAdverseReactions() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    assertEquals(vaccineAdministrationList.getAdverseReactions().size(), 0);
  }

  @Test
  public void ensureThatIsPossibleToGetAdverseReactions() {
    VaccineAdministration vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(user1, vaccine, "12345-12", 1, vaccinationCenter, Calendar.getInstance());

    vaccineAdministrationList.saveVaccineAdministration(vaccineAdministration);

    AdverseReaction adverseReaction = new AdverseReaction("adverseReaction");

    vaccineAdministration.addAdverseReaction(adverseReaction);

    assertEquals(vaccineAdministrationList.getAdverseReactions().size(), 1);
  }
}
