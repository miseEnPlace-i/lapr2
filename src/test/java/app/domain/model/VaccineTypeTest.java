package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccineTypeTest {
  VaccineType vType;

  /**
   * Check that it is not possible to create a Vaccine Type with null values
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullIsNotAllowed() {
    new VaccineType(null, null, null);
  }

  /**
   * Check that it is not possible to create a Vaccine Type with empty values.
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyIsNotAllowed() {
    new VaccineType("", "", "");
  }

  /**
   * Check that it is possible to create a Vaccination Center with all valid parameters.
   */
  @Test
  public void ensureItsPossibleToCreateVaccinationCenter() {
    VaccineType vType = new VaccineType("12345", "description", "LIVE_ATTENUATED_TECHNOLOGY");

    assert (vType != null);
  }

  /**
   * Check that it is not possible to create a Vaccine Type with an invalid code (less than 5 char)
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCodeIsCorrect() {
    new VaccineType("123", "test", "LIVE_ATTENUATED_TECHNOLOGY");
  }

  /**
   * Check that it is not possible to create a Vaccine Type with an invalid code (more than 5 char)
   * 
   * @throws Exception IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCode2IsCorrect() {
    new VaccineType("123456", "test", "LIVE_ATTENUATED_TECHNOLOGY");
  }

  /**
   * Check that two different Vaccine type are not equal
   */
  @Test
  public void ensureTwoVacTypeAreDifferent() {
    VaccineType vType1 = new VaccineType("12355", "test1", "test1");

    VaccineType vType2 = new VaccineType("12345", "test2", "test2");

    boolean result = vType1.equals(vType2);

    assertFalse(result);
  }

  /**
   * Check that two Vaccine type equal returns true
   */
  @Test
  public void ensureTwoVacTypeAreEqual() {
    VaccineType vType1 = new VaccineType("12345", "test1", "test1");

    VaccineType vType2 = new VaccineType("12345", "test1", "test1");

    boolean result = vType1.equals(vType2);

    assertTrue(result);
  }

  /**
   * Check that method hasCode is working properly
   */
  @Test
  public void ensureHasCodeIsWorkingCorrectly() {
    VaccineType vType1 = new VaccineType("12345", "test1", "test1");

    boolean result = vType1.hasCode("12345");

    assertTrue(result);
  }

  /**
   * Check that getCode is working properly
   */
  @Test
  public void ensureGetCodeIsWorkingCorrectly() {
    VaccineType vType1 = new VaccineType("12345", "test1", "test1");

    String result = vType1.getCode();

    assertEquals(result, "12345");
  }
}
