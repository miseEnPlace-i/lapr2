package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccineTechnologyStoreTest {
  VaccineTechnologyStore store = new VaccineTechnologyStore();
  String technology;

  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    technology = "TEST";
  }

  /**
   * Check that addVaccineTechnology is working correctly
   */
  @Test
  public void ensureAddVaccineTechnologyIsWorkingCorrectly() {
    assertEquals(store.size(), 0);

    store.addVaccineTechnology(technology);

    assertEquals(store.size(), 1);
  }

  /**
   * Check that existsType is working correctly
   */
  @Test
  public void ensureExistsTypeIsWorkingCorrectly() {
    store.addVaccineTechnology(technology);
    String tech = "TEST";

    assertTrue(store.existsType(tech));
  }
}
