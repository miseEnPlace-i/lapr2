package app.domain.model;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class AdminProcessTest {
    
  //Check that it is not possible to create an administration process with null arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullAdminProcArgumentsNotValid() {
    new AdminProcess(0, 0, 0);
  }

  //Check that it is not valid to create an administration process with negative arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeAdminProcArgumentsNotValid() {
    new AdminProcess(-10, -5, -3);
  }

  //Check that it is not possible to create an administration process with a min age bigger than max age 
  @Test(expected = IllegalArgumentException.class)
  public void ensureMinAgeBiggerThanMaxAgeNotValid() {
    new AdminProcess(18, 2, 3);
  }

  //Check that Admin Process constructor is working correctly
  @Test
  public void ensureIsPossibleToCreateAdminProc() {
    AdminProcess ap = new AdminProcess(1,10,2);

    assertNotNull(ap);
  }

  //Check that is possible to add a dose information
  @Test
  public void ensureIsPossibleToAddVaccine() {
    AdminProcess adminProcess = new AdminProcess(1, 10, 2);
    assert adminProcess.getDoseInfoList().getList().size() == 0;

    DoseInfo di = new DoseInfo(120, 365);
    adminProcess.addDoseInfo(di);

    assert adminProcess.getDoseInfoList().getList().size() == 1;
  }

}
