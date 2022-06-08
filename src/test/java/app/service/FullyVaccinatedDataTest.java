package app.service;

import java.util.Calendar;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.VaccinationCenter;
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


    @Before
    public void setup() {}

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
