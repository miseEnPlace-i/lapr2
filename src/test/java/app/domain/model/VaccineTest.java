package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.list.AdminProcList;
import app.domain.model.list.DoseInfoList;


/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class VaccineTest {
  AdminProcList adminProcList;
  DoseInfoList doseInfoList;
  VaccineType vaccineType;

  /**
   * Set up for the tests
   */
  @Before
  public void setUp() {
    vaccineType = new VaccineType("12345", "description", "TEST");
  }


  // Check that it is not possible to create a vaccine with null arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullVacArgumentsNotValid() {
    new Vaccine(null, null, null, null);
  }

  // Check that it is not possible to create a vaccine with empty arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyVacArgumentsNotValid() {
    new Vaccine("", "", "", null);
  }

  // Check that Vaccine constructor is working correctly
  @Test
  public void ensureIsPossibleToCreateVaccine() {
    Vaccine vaccine = new Vaccine("designation", "id", "brade", vaccineType);

    assertNotNull(vaccine);
  }

  // Check that is possible to add an administration process
  @Test
  public void ensureIsPossibleToAddVaccine() {
    Vaccine vaccine = new Vaccine("designation", "id", "brade", vaccineType);
    assert vaccine.getAdminProcList().getList().size() == 0;

    AdminProcess ap = new AdminProcess(1, 10, 2);

    vaccine.addAdminProc(ap);
    assertEquals(vaccine.getAdminProcList().getList().size(), 1);
  }
}
