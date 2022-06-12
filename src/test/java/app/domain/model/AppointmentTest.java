package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
import app.domain.shared.Gender;

public class AppointmentTest {
  Company company;
  SNSUserStore snsUserStore;
  EmployeeStore empStore;
  VaccinationCenterStore vcStore;
  VaccineTechnologyStore vtechStore;
  VaccineTypeStore vtStore;
  VaccineType vacType;
  VaccinationCenter center;

  @Before
  public void setUp() {
    this.company = new Company("designation", "12345");
    this.snsUserStore = this.company.getSNSUserStore();
    this.vcStore = company.getVaccinationCenterStore();
    this.snsUserStore = company.getSNSUserStore();
    this.empStore = company.getEmployeeStore();
    this.vtStore = company.getVaccineTypeStore();
    this.vtechStore = company.getVaccineTechnologyStore();

    SNSUser snsUser =
        snsUserStore.createSNSUser("00000000", "123456789", "name", Calendar.getInstance().getTime(), Gender.MALE, "+351212345678", "s@user.com", "address");
    this.snsUserStore.saveSNSUser(snsUser);

    Employee e2 = empStore.createEmployee("Name2", "+351916919269", "c@user.com", "address", "15542404", Constants.ROLE_COORDINATOR);
    this.empStore.saveEmployee(e2);

    this.vtechStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

    this.vacType = vtStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
    this.vtStore.saveVaccineType(vacType);

    this.center = vcStore.createCommunityMassCenter("Centro Vacinação de Teste", "Rua de Teste", "test@gmail.com", "+351212345678", "+351212345679",
        "http://www.test.com", "20:00", "21:00", 7, 5, e2, vacType);
    this.vcStore.saveVaccinationCenter(this.center);
  }

  @Test
  public void ensureToStringWorksAsExpected() {
    SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
    Calendar date = Calendar.getInstance();
    date.set(Calendar.HOUR_OF_DAY, 20);
    date.set(Calendar.MINUTE, 30);

    Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
    String result = appointment.toString();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String expected = String.format(
        "Appointment: \n\nSNS Number: 123456789\nDate: %s 20:30\nVaccination Center: Community Mass Vaccination Center data:\n\nName: Centro Vacinação de Teste\nAddress: Rua de Teste\nEmail: test@gmail.com\nPhone number: +351212345678\nFax number: +351212345679\nWeb address: http://www.test.com\nOpening hours: 20:00\nClosing hours: 21:00\nSlot duration: 7\nMaximum vaccines per slot: 5\nCoordinator: Name2\nVaccine type given on the center:\n\nVaccine type specifications:\nCode: 00000\nDescription: COVID-19\nTechnology: M_RNA_TECHNOLOGY\n\nVaccine Type: Vaccine type specifications:\nCode: 00000\nDescription: COVID-19\nTechnology: M_RNA_TECHNOLOGY\n\nSend SMS: Yes",
        sdf.format(Calendar.getInstance().getTime()));

    assertEquals(expected, result);

    appointment = new Appointment(snsUser, date, center, this.vacType, false);
    result = appointment.toString();

    expected = String.format(
        "Appointment: \n\nSNS Number: 123456789\nDate: %s 20:30\nVaccination Center: Community Mass Vaccination Center data:\n\nName: Centro Vacinação de Teste\nAddress: Rua de Teste\nEmail: test@gmail.com\nPhone number: +351212345678\nFax number: +351212345679\nWeb address: http://www.test.com\nOpening hours: 20:00\nClosing hours: 21:00\nSlot duration: 7\nMaximum vaccines per slot: 5\nCoordinator: Name2\nVaccine type given on the center:\n\nVaccine type specifications:\nCode: 00000\nDescription: COVID-19\nTechnology: M_RNA_TECHNOLOGY\n\nVaccine Type: Vaccine type specifications:\nCode: 00000\nDescription: COVID-19\nTechnology: M_RNA_TECHNOLOGY\n\nSend SMS: No",
        sdf.format(Calendar.getInstance().getTime()));

    assertEquals(expected, result);
  }

  @Test
  public void ensureHasVaccineTypeWorks() {
    SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
    Calendar date = Calendar.getInstance();
    date.set(Calendar.HOUR_OF_DAY, 20);
    date.set(Calendar.MINUTE, 30);

    Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);

    assertEquals(true, appointment.hasVaccineType(this.vacType));
    assertEquals(false, appointment.hasVaccineType(null));
  }
}
