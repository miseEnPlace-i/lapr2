package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.list.AdminProcList;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;

public class RegisterVaccineControllerTest {
  Company company = new Company("designation", "12345");
  RegisterVaccineController controller = new RegisterVaccineController(company);

  private VaccineStore vaccineStore;
  private VaccineTypeStore vaccineTypeStore;
  private VaccineType vaccineType;
  private String resourceName;

  @Before
  public void setUp() {
    vaccineStore = company.getVaccineStore();

    vaccineTypeStore = company.getVaccineTypeStore();
    vaccineType = new VaccineType("11111", "test", "test_technology");
    vaccineTypeStore.saveVaccineType(vaccineType);

    resourceName = "Vaccine";

    new Vaccine("teste", "123123", "brand", vaccineType);

    new AdminProcList();
  }

  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assertEquals(controller.getResourceName(), resourceName);
  }

  /**
   * Check that it is possible to create a vaccine
   */
  @Test
  public void ensureItIsPossibleToCreateVaccine() {
    controller.createVaccine("test", "00000", "Test", "11111");
    controller.save();

    assertEquals(vaccineStore.size(), 1);
  }

  /**
   * Check that list of vaccine types is working
   */
  @Test
  public void ensureListVaccineTypesWorks() {
    List<VaccineType> list = controller.getVacTypes();
    assertEquals(list.size(), 1);
    assertEquals(list.get(0), vaccineType);
  }
}
