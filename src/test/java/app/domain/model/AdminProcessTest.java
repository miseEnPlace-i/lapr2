package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class AdminProcessTest {

  // Check that it is not possible to create an administration process with null arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullAdminProcArgumentsNotValid() {
    new AdminProcess(0, 0, 0);
  }

  // Check that it is not valid to create an administration process with negative arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeAdminProcArgumentsNotValid() {
    new AdminProcess(-10, -5, -3);
  }

  // Check that it is not valid to create an administration process with negative arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeAdminProcArgumentsNotValid2() {
    new AdminProcess(-10, 5, -3);
  }

  // Check that it is not possible to create an administration process with a min age bigger than max age
  @Test(expected = IllegalArgumentException.class)
  public void ensureMinAgeBiggerThanMaxAgeNotValid() {
    new AdminProcess(18, 2, 3);
  }

  // Check that Admin Process constructor is working correctly
  @Test
  public void ensureIsPossibleToCreateAdminProc() {
    AdminProcess ap = new AdminProcess(1, 10, 2);

    assertNotNull(ap);
  }

  // Check that is possible to add a dose information
  @Test
  public void ensureIsPossibleToAddVaccine() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    assert adminProcess.getDoseInfoList().getList().size() == 0;

    DoseInfo di = new DoseInfo(120, 365);
    adminProcess.addDoseInfo(di);

    assertEquals(adminProcess.getDoseInfoList().getList().size(), 1);
  }

  @Test
  public void ensureAdmitsAgeIsWorking() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    assert adminProcess.admitsAge(2);
    assert !adminProcess.admitsAge(11);
  }

  @Test
  public void ensureGettersAreWorking() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    assertEquals(adminProcess.getMinAge(), 1);
    assertEquals(adminProcess.getMaxAge(), 10);
    assertEquals(adminProcess.getNumberOfDoses(), 2);
  }

  @Test
  public void ensureIsPossibleToCreateDoseInfo() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    DoseInfo di = adminProcess.createDoseInfo(12, 30);
    assertNotNull(di);
  }

  @Test
  public void ensureToStringIsWorking() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    assertEquals(adminProcess.toString(), "Min age: 1\nMax age: 10\nNumberOfDoses: 2");
  }

  @Test
  public void ensureGetDosageByNumberIsWorking() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    DoseInfo di = adminProcess.createDoseInfo(12, 30);
    adminProcess.addDoseInfo(di);
    DoseInfo di2 = adminProcess.createDoseInfo(22, 34);
    adminProcess.addDoseInfo(di2);
    assertEquals(adminProcess.getDosageByDoseNumber(1), 12);
    assertEquals(adminProcess.getDosageByDoseNumber(2), 22);
  }
}
