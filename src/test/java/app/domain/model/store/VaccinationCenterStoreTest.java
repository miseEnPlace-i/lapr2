package app.domain.model.store;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterStoreTest {
  VaccinationCenterStore store = new VaccinationCenterStore();
  Employee coordinator;
  VaccinationCenter center;

  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    coordinator = new Employee("Joana", "+351916478865", "email@email.com", "address",
        "000000000ZZ4", Constants.ROLE_COORDINATOR);
  }

  /**
   * Check that saveVaccinationCenter is working properly
   * 
   * @throws IllegalArgumentException
   */
  @Test
  public void ensureAddCenterIsWorkingCorrectly() {
    center = store.createVaccinationCenter("name", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator);

    assert store.size() == 0;

    store.saveVaccinationCenter(center);

    assert store.size() == 1;
  }

  /**
   * Check that checkDuplicates method is checking duplicated centers properly
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCheckDuplicatesIsWorkingCorrectly() {
    store.validateVaccinationCenter(center);
  }

  /**
   * Check that validateVaccinationCenter is working properly
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureValidateIsWorking() {
    store.validateVaccinationCenter(null);
  }

  /**
   * Check that it is possible to add centers to the system
   */
  @Test
  public void ensureIsPossibleToAddAnotherCenter() {
    assert store.size() == 0;

    store.saveVaccinationCenter(center);

    assert store.size() == 1;

    center = store.createVaccinationCenter("name123", "address", "email@email.com", "+351961919169",
        "+351961919169", "http://www.google.com", "10:00", "19:00", 5, 5, this.coordinator);

    store.validateVaccinationCenter(center);

    store.saveVaccinationCenter(center);

    assert store.size() == 2;
  }
}
