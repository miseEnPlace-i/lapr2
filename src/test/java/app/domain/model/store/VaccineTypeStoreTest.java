package app.domain.model.store;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.VaccineType;

public class VaccineTypeStoreTest {
  VaccineTechnologyStore technologyStore = new VaccineTechnologyStore();
  VaccineTypeStore vaccineTypeStore;

  @Before
  public void setup() {
    technologyStore.addVaccineTechnology("TEST_TECHNOLOGY");
    vaccineTypeStore = new VaccineTypeStore(technologyStore);
  }

  @Test
  public void ensureListEmptyIsWorking() {
    List<VaccineType> list = vaccineTypeStore.getList();
    assertEquals(list.size(), 0);
  }

  @Test
  public void ensureAddVaccineTypeIsWorking() {
    VaccineType vaccineType = vaccineTypeStore.addVaccineType("55555", "aa", "TEST_TECHNOLOGY");
    assertNotNull(vaccineType);

    vaccineTypeStore.saveVaccineType(vaccineType);

    List<VaccineType> list = vaccineTypeStore.getList();
    assertEquals(list.size(), 1);
    assertEquals(list.get(0), vaccineType);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCodeCannotHaveLessThanFiveDigits() {
    vaccineTypeStore.addVaccineType("333", "aa", "TEST_TECHNOLOGY");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCodeCannotHaveMoreThanFiveDigits() {
    vaccineTypeStore.addVaccineType("666666", "aa", "TEST_TECHNOLOGY");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCodeCannotHaveAlphanumeric() {
    vaccineTypeStore.addVaccineType("aaaaa", "aa", "TEST_TECHNOLOGY");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNullCodeIsNotAllowed() {
    vaccineTypeStore.getVaccineTypeByCode(null);
  }
}
