package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
import app.domain.shared.Gender;

public class ExportDailyVaccinatedTaskTest {
  Company company;
  SNSUserStore snsUserStore;
  VaccinationCenterStore vcStore;
  VaccineTechnologyStore vtechStore;
  VaccineTypeStore vtStore;
  EmployeeStore empStore;
  VaccineType vacType1;
  VaccineType vacType2;
  VaccinationCenter center1;
  VaccinationCenter center2;
  VaccineStore vacStore;
  Vaccine vac1;
  Vaccine vac2;
  SNSUser snsUser;


  @Before
  public void setUp() {
    this.company = new Company("designation", "12345");
    this.snsUserStore = this.company.getSNSUserStore();
    this.vcStore = company.getVaccinationCenterStore();
    this.snsUserStore = company.getSNSUserStore();
    this.vtStore = company.getVaccineTypeStore();
    this.empStore = company.getEmployeeStore();
    this.vacStore = company.getVaccineStore();
    this.vtechStore = company.getVaccineTechnologyStore();

    this.snsUser = snsUserStore.createSNSUser("00000000", "123456789", "name", Calendar.getInstance().getTime(), Gender.MALE, "+351212345678", "s@user.com",
        new Address("street", 1, "10-10", "city"));
    this.snsUserStore.saveSNSUser(snsUser);

    Employee emp =
        empStore.createEmployee("Name", "+351916919268", "c1@user.com", new Address("street", 1, "10-10", "city"), "15542404", Constants.ROLE_COORDINATOR);
    this.empStore.saveEmployee(emp);

    Employee emp2 =
        empStore.createEmployee("Name2", "+351916919269", "c2@user.com", new Address("street", 1, "10-10", "city"), "15542405", Constants.ROLE_COORDINATOR);
    this.empStore.saveEmployee(emp2);


    this.vtechStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

    this.vacType1 = vtStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
    this.vtStore.saveVaccineType(vacType1);
    this.vac1 = new Vaccine("vacTest1", "vac1", "brand1", vacType1);
    this.vacStore.saveVaccine(vac1);

    this.vacType2 = vtStore.addVaccineType("00001", "GRIPE-A", "M_RNA_TECHNOLOGY");
    this.vtStore.saveVaccineType(vacType2);
    this.vac2 = new Vaccine("vacTest2", "vac2", "brand2", vacType2);
    this.vacStore.saveVaccine(vac2);


    this.center1 = vcStore.createHealthCareCenter("Centro Vacinação de Teste", new Address("street", 1, "10-10", "city"), "test@gmail.com", "+351212345678",
        "+351212345679", "http://www.test.com", "20:00", "21:00", 7, 5, emp, "ages", "ags");
    this.vcStore.saveVaccinationCenter(this.center1);

    this.center2 = vcStore.createHealthCareCenter("Centro Vacinação de Teste2", new Address("street", 1, "10-10", "city"), "test2@gmail.com", "+351212345679",
        "+351212345678", "http://www.test2.com", "20:00", "21:00", 7, 5, emp2, "ages", "ags");
    this.vcStore.saveVaccinationCenter(this.center2);
  }

  @Test
  public void ensureTaskExportWorksWithNoVacAdmin() throws IOException {
        ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask("out/test/test1", ";".charAt(0), vcStore, vtStore);
    task.run();


    Calendar today = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Path expectedFilepath = Path.of("out/test/test1" + format.format(today.getTime()) + ".csv");

    String expected = "Number of Vaccinated people in day " + format.format(today.getTime()) + "\n" +
        "Center;" + vacType1.getDescription() + ";" + vacType2.getDescription() + "\n" + center1.getName() + ";0;0\n" + center2.getName() + ";0;0\n";

    assertEquals(expected, Files.readString(expectedFilepath));
  }

  @Test
  public void ensureTaskExportingCorrectly() throws IOException {
    Calendar today = Calendar.getInstance();

    VaccineAdministration vacAdmin = new VaccineAdministration(snsUser, vac1, "AAAAA-11", 1, center1, today);
    center1.addVaccineAdministrationToList(vacAdmin);

    VaccineAdministration vacAdmin2 = new VaccineAdministration(snsUser, vac2, "BBBBB-22", 1, center1, today);
    center1.addVaccineAdministrationToList(vacAdmin2);

    VaccineAdministration vacAdmin3 = new VaccineAdministration(snsUser, vac2, "CCCCC-22", 1, center2, today);
    center2.addVaccineAdministrationToList(vacAdmin3);

    ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask("out/test/test2", ";".charAt(0), vcStore, vtStore);
    task.run();

   
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    Path expectedFilepath = Path.of("out/test/test2" + format.format(today.getTime()) + ".csv");

    String expected = "Number of Vaccinated people in day " + format.format(today.getTime()) + "\n" +
    "Center;" + vacType1.getDescription() + ";" + vacType2.getDescription() + "\n" + center1.getName() + ";1;1\n" + center2.getName() + ";0;1\n";


    assertEquals(expected, Files.readString(expectedFilepath));
  }
}
