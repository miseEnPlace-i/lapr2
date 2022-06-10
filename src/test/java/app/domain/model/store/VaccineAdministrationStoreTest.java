// package app.domain.model.store;

// import static org.junit.Assert.assertEquals;
// import java.text.ParseException;
// import java.util.Calendar;
// import java.util.Date;
// import javax.xml.namespace.QName;
// import org.junit.Before;
// import org.junit.Test;
// import app.domain.model.Company;
// import app.domain.model.Employee;
// import app.domain.model.HealthCareCenter;
// import app.domain.model.SNSUser;
// import app.domain.model.Slot;
// import app.domain.model.VaccinationCenter;
// import app.domain.model.Vaccine;
// import app.domain.model.VaccineAdministration;
// import app.domain.shared.Constants;
// import app.domain.shared.Gender;
// import app.service.CalendarUtils;
// import app.utils.Time;
// import pt.isep.lei.esoft.auth.AuthFacade;

// /**
// * Vaccine administration store tests.
// *
// * @author Tomás Russo <1211288@isep.ipp.pt>
// */
// public class VaccineAdministrationStoreTest {
// VaccineAdministrationStore store;
// VaccinationCenter vaccinationCenter;
// SNSUser snsUser;
// Vaccine vaccine;
// VaccineAdministration vaccineAdministration;
// VaccineAdministration vaccineAdministration2;

// @Before
// public void setUp() {
// Company company = new Company("abc", "12345");
// store = company.getVaccineAdministrationStore();
// Date d = new Date();

// Employee coordinator = new Employee("00000001", "Joana", "+351916478865", "email@email.com", "address",
// "000000000ZZ4", Constants.ROLE_COORDINATOR);

// snsUser = new SNSUser("123456789ZZ1", "123456789", "name", d, Gender.MALE, "+351211111111", "email@email.com",
// "address");

// Time openingHours = new Time(8, 0);
// Time closingHours = new Time(19, 0);
// Slot slot = new Slot(5, 10);
// vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351961919169", "+351961919169",
// "http://www.google.com", openingHours,
// closingHours, slot, coordinator, "test", "test");

// Calendar c = Calendar.getInstance();
// Calendar c2 = Calendar.getInstance();
// try {
// c = CalendarUtils.parseDateTime(d, "10:00");
// c2 = CalendarUtils.parseDateTime(d, "18:00");
// } catch (ParseException ex) {
// }

// vaccineAdministration = new VaccineAdministration(snsUser, vaccine, "213C5-98", 1, vaccinationCenter, c);
// vaccineAdministration2 = new VaccineAdministration(snsUser, vaccine, "213C5-98", 2, vaccinationCenter, c2);
// }

// /**
// * Test if it is possible to add a new Vaccine Administration object to the store.
// */
// @Test
// public void ensureAddVaccineAdministrationIsWorkingCorrectly() {
// assertEquals(store.size(), 0);
// store.saveVaccineAdministration(vaccineAdministration);
// store.saveVaccineAdministration(vaccineAdministration2);
// assertEquals(store.size(), 2);
// }

// @Test
// public void ensureGetLastDoseOfSnsUserIsWorkingCorrectly() {
// store.saveVaccineAdministration(vaccineAdministration);
// store.saveVaccineAdministration(vaccineAdministration2);
// assertEquals(store.getDoseNumber(vaccineAdministration), 1);
// assertEquals(store.getDoseNumber(vaccineAdministration2), 2);
// }
// }
