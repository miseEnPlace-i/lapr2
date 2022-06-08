package app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.provider.EmptySource;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccineTypeStore;
import app.session.EmployeeSession;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsControllerTest {
    Company company = new Company("designation", "12345");

    @Before
    public void setUp() {
        Employee coordinator = new Employee("123", "test", "+351933456789", "email@email.com", "address", "000000000ZZ4", Constants.ROLE_COORDINATOR);
        EmployeeStore empStore = company.getEmployeeStore();
        empStore.saveEmployee(coordinator);

        VaccineType vacType = new VaccineType("12345", "test", "test_technology");
        VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
        vaccineTypeStore.saveVaccineType(vacType);

        CommunityMassVaccinationCenter center = new CommunityMassVaccinationCenter("name", "address", "email@email.com", "+351913456789", "+351913456789",
                "https ://domain.ext", "20:00", "23:00", 5, coordinator, vacType);
        EmployeeSession session = new EmployeeSession(center);
        ExportCenterStatisticsController ctrl = new ExportCenterStatisticsController(company, session);


    }
}
