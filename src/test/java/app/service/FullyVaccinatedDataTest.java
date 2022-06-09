package app.service;

import java.util.Calendar;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.shared.Gender;
import app.service.FullyVaccinatedData;

/**
 * FullyVaccinatedData test
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class FullyVaccinatedDataTest {
    FullyVaccinatedData fullyVaccinatedData;

    String filePath = "test.csv";
    Calendar startDate;
    Calendar endDate;
    VaccinationCenter center;
    Map<Calendar, Integer> data;
    VaccineAdministration vacAdmin;
    SNSUser user;
    Vaccine vaccine;
    String lotNumbeer;
    int doseNumber;
    Calendar date;
    Calendar birthday;
    VaccineAdministrationList vacAdminList;


    @Before
    public void setup() {

        birthday = Calendar.getInstance();
        birthday.set(1980, 5, 23);

        date = Calendar.getInstance();
        date.set(Calendar.DAY_OF_MONTH, 20);

        user = new SNSUser("000000000ZZ4", "123456789", "name", birthday.getTime(), Gender.MALE, "+351913456789", "mail@mail.com", "test");

        vacAdmin = new VaccineAdministration(user, vaccine, "00000-0C", doseNumber, center, date);

    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgumentsAreNotAllowed() {
        fullyVaccinatedData = new FullyVaccinatedData(null, null, null, null);
    }

    @Test
    public void ensureGetFullyVaccinatedUsersPerDayMapWorks() {

    }

    @Test
    public void ensureGetVacAdminListPerDayWorks() {}

}
