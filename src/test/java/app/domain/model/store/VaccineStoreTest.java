package app.domain.model.store;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;


/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccineStoreTest {
  VaccineStore store = new VaccineStore();
  private Vaccine vaccine;
  private Vaccine vaccine2;
  private VaccineType vacType;


  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    vacType = new VaccineType("12345", "description", "TEST");
  }

  /**
   * Check that it is possible to create a new vaccine
   */
  @Test
  public void ensureCreateVaccineIsWorkingCorrectly() {

    assert store.size() == 0;

    vaccine = store.createVaccine("designation", "id", "brand", this.vacType);

    assert (vaccine != null);

  }

  /**
   * Check that saveVaccinationCenter is working properly
   *
   */
  @Test
  public void ensureAddCenterIsWorkingCorrectly() {
    assert store.size() == 0;

    vaccine = store.createVaccine("designation", "id", "brand", this.vacType);

    store.saveVaccine(vaccine);

    assert store.size() == 1;
  }

  /**
   * Check that it is possible to add multiple vaccines to the system
   */
  @Test
  public void ensureIsPossibleToAddAnotherCenter() {
    assert store.size() == 0;

    vaccine = store.createVaccine("designation", "id", "brand", this.vacType);

    store.saveVaccine(vaccine);

    assert store.size() == 1;

    vaccine2 = store.createVaccine("designation", "id", "brand", this.vacType);

    store.saveVaccine(vaccine2);

    assert store.size() == 2;
  }

}
