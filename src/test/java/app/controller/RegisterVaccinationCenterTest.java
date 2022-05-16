package app.controller;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.shared.Constants;

public class RegisterVaccinationCenterTest {
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

    /**
     * Check that it is not possible to create a vaccination center with email invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidEmailThrowsException() {
        controller.create("name", "address", "emailaddress", "+351913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with phone invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidPhoneThrowsException() {
        controller.create("name", "address", "example@gmail.com", "913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with fax invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidFaxThrowsException() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "913456788",
                "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with website invalid
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidWebsiteThrowsException() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
                "abc://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with slot duration invalid (x=<0)
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidSlotDurationThrowsException() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", -5, 5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with maximum vaccines per slot invalid (x=<0)
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidMaxVacSlotThrowsException() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", 5, -5, coordinator);
    }

    /**
     * Check that it is not possible to create a vaccination center with maximum vaccines per slot invalid (x=<0)
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureInvalidCoordinatorThrowsException() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", 5, 5, null);
    }

    /**
     * Check that it is possible to create a vaccination center with all valid values
     */
    @Test
    public void ensureValidValuesCreateNewCenter() {
        controller.create("name", "address", "example@gmail.com", "+351913456789", "+351913456788",
                "https://www.teste.com", "11:00", "12:00", 5, 5, coordinator);
    }
}
