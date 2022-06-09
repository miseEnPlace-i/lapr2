package app.controller;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.provider.EmptySource;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;
import app.session.EmployeeSession;
import app.utils.Time;
import app.domain.shared.Constants;
import app.exception.NotAuthorizedException;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsControllerTest {
    Company company = new Company("designation", "12345");
    ExportCenterStatisticsController ctrl;

    Time openingHours;
    Time closingHours;
    Slot slot;
    Calendar startDate;
    Calendar endDate;


    @Before
    public void setUp() throws NotAuthorizedException {
        openingHours = new Time(8, 0);
        closingHours = new Time(19, 0);
        slot = new Slot(5, 10);

        startDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 20);

        endDate = Calendar.getInstance();
        startDate.set(Calendar.DAY_OF_MONTH, 25);

        Employee coordinator = new Employee("123", "test", "+351933456789", "email@email.com", "address", "000000000ZZ4", Constants.ROLE_COORDINATOR);
        EmployeeStore empStore = company.getEmployeeStore();
        empStore.saveEmployee(coordinator);

        VaccineType vacType = new VaccineType("12345", "test", "test_technology");
        VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
        vaccineTypeStore.saveVaccineType(vacType);

        CommunityMassVaccinationCenter center = new CommunityMassVaccinationCenter("name", "address", "email@email.com", "+351913456789", "+351913456789",
                "https://domain.ext", openingHours, closingHours, slot, coordinator, vacType);
        VaccinationCenterStore centerStore = company.getVaccinationCenterStore();
        centerStore.saveVaccinationCenter(center);

        EmployeeSession session = new EmployeeSession();
        session.setVaccinationCenter(center);

        ctrl = new ExportCenterStatisticsController(company, session);
    }

    /**
     * Checks that it is not possible to create a csv exporter with null values
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullValuesNotAllowed() {
        ctrl.createCsvExporterData(null, null, null);
    }

    /**
     * Check that it is possible to create a CsvExporter
     */
    @Test
    public void ensureItIsPossibleToCreateCsvExporter() {
        ctrl.createCsvExporterData("test.csv", startDate, endDate);
    }
}
