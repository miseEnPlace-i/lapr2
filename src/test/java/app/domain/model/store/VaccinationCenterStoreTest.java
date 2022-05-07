package app.domain.model.store;

import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterStoreTest {
    VaccinationCenterStore store = new VaccinationCenterStore();

    /**
     * Check that saveVaccinationCenter method is saving properly the new center
     * 
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureAddCenterItsWorkingCorrectly() {
        Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida",
                "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

        VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567",
                "https://www.centrovacinaoporto.com", "20:00", "30:60", 5, 10, coordinator);

        store.saveVaccinationCenter(center);
        assert (store.size() != 0);
    }

    /**
     * Check that checkDuplicates method is checking duplicated centers properly
     * 
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureCheckDuplicatesIsWorkingCorrectly() {
        Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida",
                "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

        VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
                "https://www.centrovacinaoporto.com", "20:00", "30:60", 5, 10, coordinator);
        store.saveVaccinationCenter(center);
        store.checkDuplicates(center);

    }

    /**
     * Check that validateCenter method is validating correctly
     * 
     * @throws IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateCenterIsWorkingCorrectly() {
        Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida",
                "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

        VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351223456789",
                "https://www.centrovacinaoporto.com", "20:00", "30:60", 5, 10, coordinator);

        store.validateVaccinationCenter(center);
    }
}
