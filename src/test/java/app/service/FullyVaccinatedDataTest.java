package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.AdminProcess;
import app.domain.model.Company;
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
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
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
    Company company = new Company("designation", "12345");
    FullyVaccinatedData fullyVaccinatedData;

    String filePath = "test.csv";
    Calendar startDate;
    Calendar endDate;
    HealthCareCenter center;
    Map<Calendar, Integer> data;
    VaccineAdministration vacAdmin;
    SNSUser user;
    Vaccine vaccine;
    String lotNumbeer;
    int doseNumber;
    Calendar date1;
    Calendar date2;
    Calendar birthday;
    VaccineAdministrationList vacAdminList;
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
        birthday.set(1980, 5, 23);

        date1 = Calendar.getInstance();
        date1.set(Calendar.DAY_OF_MONTH, 20);

        date2 = Calendar.getInstance();
        date2.set(Calendar.DAY_OF_MONTH, 21);

        coordinator = new Employee("010101", "coordinator", "+351913456789", "mail@mail.com", "test", "00000000", Constants.ROLE_COORDINATOR);
        EmployeeStore empStore = company.getEmployeeStore();
        empStore.saveEmployee(coordinator);

        center = new HealthCareCenter("test", "test", "email@mail.com", "+351913456789", "+351913456789", "https://www.test.com", openingHours, closingHours,
                slot, coordinator, "ACES", "ARS");
        VaccinationCenterStore centerStore = company.getVaccinationCenterStore();
        centerStore.saveVaccinationCenter(center);

        user = new SNSUser("000000000ZZ4", "123456789", "name", birthday.getTime(), Gender.MALE, "+351913456789", "mail@mail.com", "test");

        VaccineType vacType = new VaccineType("12345", "test", "test_technology");
        VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
        vaccineTypeStore.saveVaccineType(vacType);

        Vaccine vaccine = new Vaccine("test", "12345", "brand", vacType);
        AdminProcess ap = new AdminProcess(1, 10, 2);
        vaccine.addAdminProc(ap);
        VaccineStore vaccineStore = company.getVaccineStore();
        vaccineStore.saveVaccine(vaccine);

        userHealthData = user.getUserHealthData();
        vacAdminList = userHealthData.getVaccineAdministrationList();

        vacAdmin = new VaccineAdministration(user, vaccine, "00000-AA", 2, center, date1);
        vacAdminList.saveVaccineAdministration(vacAdmin);

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
    public void ensureGetVacAdminListPerDayWorks() {}

}
