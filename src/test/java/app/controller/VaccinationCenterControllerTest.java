package app.controller;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.shared.Constants;

public class VaccinationCenterControllerTest {
    RegisterVaccinationCenterController controller = new RegisterVaccinationCenterController();
    VaccinationCenterStore store = new VaccinationCenterStore();
    String centerName = "Vaccination Center";
    Employee coordinator;

    @Before
    public void setUp() {
        coordinator = new Employee("Joana", "+351916478865", "email@email.com", "address",
                "000000000ZZ4", Constants.ROLE_COORDINATOR);
    }

    /**
     * Check that getResourceName method is working properly
     */
    @Test
    public void ensureGetResourceNameIsWorkingCorrectly() {
        assert centerName == controller.getResourceName();
    }

    /**
     * Check that it is not possible to create a vaccination center with null values
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullValuesNotAllowed() {
        controller.create(null, null, null, null, null, null, null, null, 0, 0, null);
    }

    /**
     * Check that it is not possible to create a vaccination center with empty values
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyValuesNotAllowed() {
        controller.create("", "", "", "", "", "", "", "", 0, 0, coordinator);
    }



}
