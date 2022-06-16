package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.AdminProcess;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.HealthData;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.service.FullyVaccinatedData;
import app.utils.Time;

/**
 * FullyVaccinatedData test
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class FullyVaccinatedDataTest {
    /*
    Company company = new Company("designation", "12345");
    FullyVaccinatedData fullyVaccinatedData;

    String filePath = "test.csv";
    Calendar startDate;
    Calendar endDate;
    HealthCareCenter center;
    Map<Calendar, Integer> data = new HashMap<>();
    VaccineAdministration vacAdmin;
    SNSUser user;
    Vaccine vaccine;
    String lotNumbeer;
    int doseNumber;
    Calendar date1;
    Calendar date2;
    Calendar birthday;
    VaccineAdministrationList vacAdminList = new VaccineAdministrationList();
    HealthData userHealthData;
    Time openingHours;
    Time closingHours;
    Slot slot;
    Employee coordinator;

    @Before
    public void setup() {

        openingHours = new Time(8, 0);
        closingHours = new Time(19, 0);
        slot = new Slot(5, 10);

        birthday = Calendar.getInstance();
        birthday.set(2020, 5, 23);

        date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH, 1);

        date2 = Calendar.getInstance();
        date2.set(Calendar.DAY_OF_MONTH, 2);

        coordinator = new Employee("010101", "coordinator", "+351913456789", "mail@mail.com", "test", "00000000", Constants.ROLE_COORDINATOR);
        EmployeeStore empStore = company.getEmployeeStore();
        empStore.saveEmployee(coordinator);

        center = new HealthCareCenter("test", "test", "email@mail.com", "+351913456789", "+351913456789", "https://www.test.com", openingHours, closingHours,
                slot, coordinator, "ACES", "ARS");
        VaccinationCenterStore centerStore = company.getVaccinationCenterStore();
        centerStore.saveVaccinationCenter(center);

        user = new SNSUser("00000000", "123456789", "name", birthday.getTime(), Gender.MALE, "+351913456789", "mail@mail.com", "test");

        VaccineType vacType = new VaccineType("12345", "test", "test_technology");
        VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
        vaccineTypeStore.saveVaccineType(vacType);

        Vaccine vaccine = new Vaccine("test", "12345", "brand", vacType);
        AdminProcess ap = new AdminProcess(1, 10, 2);
        DoseInfo di1 = new DoseInfo(120, 0);
        DoseInfo di2 = new DoseInfo(120, 365);
        ap.addDoseInfo(di1);
        ap.addDoseInfo(di2);
        vaccine.addAdminProc(ap);
        VaccineStore vaccineStore = company.getVaccineStore();
        vaccineStore.saveVaccine(vaccine);

        userHealthData = user.getUserHealthData();
        vacAdminList = userHealthData.getVaccineAdministrationList();

        vacAdmin = new VaccineAdministration(user, vaccine, "00000-11", 2, center, date1);
        vacAdminList.saveVaccineAdministration(vacAdmin);

        fullyVaccinatedData = new FullyVaccinatedData("test.csv", date1, date2, center);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgumentsAreNotAllowed() {
        fullyVaccinatedData = new FullyVaccinatedData(null, null, null, null);
    }

    @Test
    public void ensureGetFullyVaccinatedUsersPerDayMapWorks() {
        Map<Calendar, Integer> dataExpected = new HashMap<Calendar, Integer>();
        dataExpected.put(date1, 1);
        dataExpected.put(date2, 0);

        data = fullyVaccinatedData.getFullyVaccinatedUsersPerDayMap();

        assertEquals(dataExpected, data);
    }

    @Test
    public void ensureGetVacAdminListPerDayWorks() {}*/

    Company company;
    SNSUserStore snsUserStore;
    VaccinationCenterStore vcStore;
    VaccineTechnologyStore vtechStore;
    VaccineTypeStore vtStore;
    EmployeeStore empStore;
    VaccineType vacType1;
    VaccineType vacType2;
    VaccinationCenter center1;
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

        this.snsUser =
            snsUserStore.createSNSUser("00000000", "123456789", "name", Calendar.getInstance().getTime(), Gender.MALE, "+351212345678", "s@user.com", "address");
        this.snsUserStore.saveSNSUser(snsUser);

        Employee emp = empStore.createEmployee("Name", "+351916919268", "c1@user.com", "address", "15542404", Constants.ROLE_COORDINATOR);
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


        this.center1 = vcStore.createHealthCareCenter("Centro Vacinação de Teste", "Rua de Teste", "test@gmail.com", "+351212345678", "+351212345679",
        "http://www.test.com", "20:00", "21:00", 7, 5, emp, "ages", "ags");
        this.vcStore.saveVaccinationCenter(this.center1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgumentsAreNotAllowed() {
        new FullyVaccinatedData(null, null, null, null);
    }

    @Test
    public void ensureGetFullyVaccinatedUsersPerDayMapWorks() {
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, -2);

        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, -1);

        Map<Calendar, Integer> dataExpected = new HashMap<Calendar, Integer>();
        dataExpected.put(date1, 1);
        dataExpected.put(date2, 0);

        center1.addVaccineAdministrationToList(new VaccineAdministration(snsUser, vac1, "AAAAA-11", 2, center1, date1));

        FullyVaccinatedData fullyVaccinatedData = new FullyVaccinatedData("path", date1, date2, center1);

        Map<Calendar, Integer> data = fullyVaccinatedData.getFullyVaccinatedUsersPerDayMap();

        assertEquals(dataExpected, data);
    }

    //ensures that getFullyVaccinatedUsersPerDayMap checks if it is the last dose for the admin process for user's age
    @Test
    public void ensureGetFullyVaccinatedUsersPerDayMapWorksForNotLastDoseInAdminProcAge() {
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, -2);

        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, -1);

        Map<Calendar, Integer> dataExpected = new HashMap<Calendar, Integer>();
        dataExpected.put(date1, 0);
        dataExpected.put(date2, 0);

        center1.addVaccineAdministrationToList(new VaccineAdministration(snsUser, vac2, "AAAAA-11", 1, center1, date1));

        FullyVaccinatedData fullyVaccinatedData = new FullyVaccinatedData("path", date1, date2, center1);

        Map<Calendar, Integer> data = fullyVaccinatedData.getFullyVaccinatedUsersPerDayMap();

        assertEquals(dataExpected, data);
    }

    //ensures that getFullyVaccinatedUsersPerDayMap doesn't accept invalid date intervals
    @Test(expected = IllegalArgumentException.class)
    public void ensureGetFullyVaccinatedUsersPerDayMapWorksDoesNotAcceptInvalidInterval() {
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, -2);

        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, -1);

        new FullyVaccinatedData("path", date2, date1, center1);
    }

    //ensures that getFullyVaccinatedUsersPerDayMap doesn't accept future date intervals
    @Test(expected = IllegalArgumentException.class)
    public void ensureGetFullyVaccinatedUsersPerDayMapWorksDoesNotAcceptFutureDateInterval() {
        Calendar date1 = Calendar.getInstance();
        date1.add(Calendar.DAY_OF_MONTH, 1);

        Calendar date2 = Calendar.getInstance();
        date2.add(Calendar.DAY_OF_MONTH, 2);

        new FullyVaccinatedData("path", date1, date2, center1);
    }

}
