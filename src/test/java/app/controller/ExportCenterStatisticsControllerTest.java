package app.controller;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Address;

import app.domain.model.AdminProcess;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Employee;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.session.EmployeeSession;
import app.utils.Time;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.exception.NotAuthorizedException;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class ExportCenterStatisticsControllerTest {
    Company company = new Company("designation", "12345");
    ExportCenterStatisticsController ctrl;

    Time openingHours;
    Time closingHours;
    Slot slot;
    Calendar startDate;
    Calendar endDate;
    SNSUserStore snsUserStore;
    VaccinationCenterStore vcStore;
    VaccineTechnologyStore vtechStore;
    VaccineTypeStore vtStore;
    EmployeeStore empStore;
    VaccineType vacType1;
    VaccineType vacType2;
    VaccinationCenter center;
    VaccineStore vacStore;
    Vaccine vac1;
    Vaccine vac2;
    SNSUser snsUser;


    @Before
    public void setUp() throws NotAuthorizedException {
        this.snsUserStore = this.company.getSNSUserStore();
        this.vcStore = company.getVaccinationCenterStore();
        this.snsUserStore = company.getSNSUserStore();
        this.vtStore = company.getVaccineTypeStore();
        this.empStore = company.getEmployeeStore();
        this.vacStore = company.getVaccineStore();
        this.vtechStore = company.getVaccineTechnologyStore();

        openingHours = new Time(8, 0);
        closingHours = new Time(19, 0);
        slot = new Slot(5, 10);

        startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -10);

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DAY_OF_MONTH, -9);

         this.snsUser =
            snsUserStore.createSNSUser("00000000", "123456789", "name", Calendar.getInstance().getTime(), Gender.MALE, "+351212345678", "s@user.com", new Address("street", 1, "10-10", "city"));
        this.snsUserStore.saveSNSUser(snsUser);

        Employee emp = empStore.createEmployee("Name", "+351916919268", "c1@user.com", new Address("street", 1, "10-10", "city"), "15542404", Constants.ROLE_COORDINATOR);
        this.empStore.saveEmployee(emp);
        

        this.vtechStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

        this.vacType1 = vtStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        this.vtStore.saveVaccineType(vacType1);
        this.vac1 = new Vaccine("vacTest1", "vac1", "brand1", vacType1);
        AdminProcess ap = new AdminProcess(0, 100, 2);
        DoseInfo di = new DoseInfo(120, 0);
        DoseInfo di2 = new DoseInfo(120, 365);
        ap.addDoseInfo(di);
        ap.addDoseInfo(di2);
        vac1.addAdminProc(ap);
        this.vacStore.saveVaccine(vac1);

        this.vacType2 = vtStore.addVaccineType("00001", "GRIPE-A", "M_RNA_TECHNOLOGY");
        this.vtStore.saveVaccineType(vacType2);
        this.vac2 = new Vaccine("vacTest2", "vac2", "brand2", vacType2);
        ap = new AdminProcess(0, 10, 2);
        di = new DoseInfo(120, 0);
        di2 = new DoseInfo(120, 365);
        ap.addDoseInfo(di);
        ap.addDoseInfo(di2);
        vac2.addAdminProc(ap);
        ap = new AdminProcess(10, 100, 1);
        di = new DoseInfo(120, 0);
        ap.addDoseInfo(di);
        vac2.addAdminProc(ap);
        this.vacStore.saveVaccine(vac2);


        this.center = vcStore.createHealthCareCenter("Centro Vacinação de Teste", new Address("street", 1, "10-10", "city"), "test@gmail.com", "+351212345678", "+351212345679",
        "http://www.test.com", "20:00", "21:00", 7, 5, emp, "ages", "ags");
        this.vcStore.saveVaccinationCenter(this.center);

        EmployeeSession session = new EmployeeSession();
        session.setVaccinationCenter(center);

        ctrl = new ExportCenterStatisticsController(company, session);
    }

    /**
     * Checks that it is not possible to create a csv exporter with null values
     */
    @Test(expected = NullPointerException.class)
    public void ensureNullValuesNotAllowed() {
        ctrl.createFullyVaccinatedData(null, null, null);
    }

    /**
     * Checks that it is not possible to create a csv exporter with null values
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidIntervalNotAllowed() {
        ctrl.createFullyVaccinatedData(null, endDate, startDate);
    }

    /**
     * Checks that it is not possible to create a csv exporter with null values
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureFutureIntervalNotAllowed() {
        Calendar futureStartDate = Calendar.getInstance();
        futureStartDate.add(Calendar.DAY_OF_MONTH, 5);

        Calendar futureEndDate = Calendar.getInstance();
        futureEndDate.add(Calendar.DAY_OF_MONTH, 6);

        ctrl.createFullyVaccinatedData(null, futureStartDate, futureEndDate);
    }

    /**
     * Check that it is possible to create a CsvExporter
     */
    @Test
    public void ensureItIsPossibleToCreateCsvExporter() {
        ctrl.createFullyVaccinatedData("test.csv", startDate, endDate);
    }

    @Test
    public void exportFileString() throws IOException {
        LinkedHashMap<Calendar, Integer> dataExpected = new LinkedHashMap<Calendar, Integer>();
        dataExpected.put(startDate, 1);
        dataExpected.put(endDate, 0);
        
        center.addVaccineAdministrationToList(new VaccineAdministration(snsUser, vac1, "AAAAA-11", 2, center, startDate));

        ctrl.createFullyVaccinatedData("Path.csv", startDate, endDate);

        ctrl.generateFullyVaccinatedUsersInterval();
       
        String expected = "Date;NumberOfFullyVaccinatedUsers\n";

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        expected += df.format(startDate.getTime()) + ";1\n" + df.format(endDate.getTime()) + ";0\n";

        assertEquals(expected, ctrl.exportFileString());
    }

    @Test
    public void ensureSaveDataWorks() throws IOException {
        LinkedHashMap<Calendar, Integer> dataExpected = new LinkedHashMap<Calendar, Integer>();
        dataExpected.put(startDate, 1);
        dataExpected.put(endDate, 0);
        
        center.addVaccineAdministrationToList(new VaccineAdministration(snsUser, vac1, "AAAAA-11", 2, center, startDate));

        ctrl.createFullyVaccinatedData("Path.csv", startDate, endDate);

        ctrl.generateFullyVaccinatedUsersInterval();

        ctrl.saveData("Path.csv"); 
        
        String expected = "Date;NumberOfFullyVaccinatedUsers\n";

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        expected += df.format(startDate.getTime()) + ";1\n" + df.format(endDate.getTime()) + ";0\n";

        Path expectedFilepath = Path.of("Path.csv");

        assertEquals(expected, Files.readString(expectedFilepath));
    }

    //ensures that saveData checks if it is the last dose for the admin process for user's age
    @Test
    public void ensureSaveDataWorksForNotLastDoseInAdminProcAge() throws IOException {
        LinkedHashMap<Calendar, Integer> dataExpected = new LinkedHashMap<Calendar, Integer>();
        dataExpected.put(startDate, 1);
        dataExpected.put(endDate, 0);
        
        center.addVaccineAdministrationToList(new VaccineAdministration(snsUser, vac1, "AAAAA-11", 1, center, startDate));

        ctrl.createFullyVaccinatedData("Path.csv", startDate, endDate);

        ctrl.generateFullyVaccinatedUsersInterval();

        ctrl.saveData("Path.csv"); 
        
        String expected = "Date;NumberOfFullyVaccinatedUsers\n";

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        expected += df.format(startDate.getTime()) + ";0\n" + df.format(endDate.getTime()) + ";0\n";

        Path expectedFilepath = Path.of("Path.csv");

        assertEquals(expected, Files.readString(expectedFilepath));
    }
}
