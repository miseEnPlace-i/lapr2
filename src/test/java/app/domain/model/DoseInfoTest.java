package app.domain.model;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class DoseInfoTest {

  // Check that it is not possible to create a dose information with null arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullDoseInfoArgumentsNotValid() {
    new DoseInfo(0, 0);
  }

  // Check that it is not possible to create a dose information with negative arguments
  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeDoseInfoArgumentsNotValid() {
    new DoseInfo(-10, -5);
  }

  // Check that Vaccine constructor is working correctly
  @Test
  public void ensureIsPossibleToCreateDoseInfo() {
    DoseInfo di = new DoseInfo(120, 365);

    assertNotNull(di);
  }
}
