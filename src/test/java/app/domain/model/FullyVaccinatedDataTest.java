package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Address;
import app.domain.model.AdminProcess;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Employee;
import app.domain.model.FullyVaccinatedData;
import app.domain.model.SNSUser;
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
import app.domain.shared.Constants;
import app.domain.shared.Gender;

/**
 * FullyVaccinatedData test
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class FullyVaccinatedDataTest {

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


        this.center1 = vcStore.createHealthCareCenter("Centro Vacinação de Teste", new Address("street", 1, "10-10", "city"), "test@gmail.com", "+351212345678", "+351212345679",
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
