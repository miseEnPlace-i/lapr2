package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUserTest {

  /**
   * Tests if the class doesn't accept null values for the parameters.
   *
   * @throws Exception
   */
  @Test(expected = Exception.class)
  public void ensureNullArgumentsAreNotAllowed() {
    new SNSUser(null, null, null, null, ' ', null, null, null);
  }

  /**
   * Tests if the class doesn't accept birthdays older than 150 years.
   *
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidBirthDayIsNotAccepted() {
    String citizenCard = "123456789";
    String snsNumber = "123456789";
    String name = "Teste";
    char gender = 'm';
    String phoneNumber = "+351900000000";
    String email = "example@example.com";
    String address = "Test Address 101";

    Date birthDay = new Date();
    DateUtils.addYears(birthDay, -151);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }

  /**
   * Tests if the class doesn't accept birthdays in the future.
   *
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidFutureBirthDayAreNotAccepted() {
    String citizenCard = "123456789";
    String snsNumber = "123456789";
    String name = "Teste";
    char gender = 'm';
    String phoneNumber = "+351900000000";
    String email = "example@example.com";
    String address = "Test Address 101";

    Date birthDay = new Date();
    DateUtils.addYears(birthDay, 1);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }

  /**
   * TTests if it is not possible to create an SNS User object with an invalid phone number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidPhoneNumbersAreNotAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "0", "email@email.com", "address");
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid Citizen Card number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidCCIsNotAllowed() {
    Date c = new Date();
    new SNSUser("987987", "123456789", "name", c, 'M', "+351912345678", "email@email.com",
        "address");
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid Email.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidEmailIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351912345678", "2h1125h1q5as",
        "address");
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid SNS Number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidSNSNumberIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "1111111111111111111", "name", c, 'M', "+351912345678",
        "2h1125h1q5as", "address");
  }

  /**
   * Tests if it is possible to create an SNS User object correctly
   */
  @Test
  public void ensureIsPossibleToCreateSNSUser() {
    Date c = new Date();
    SNSUser instance = new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351211111111",
        "email@email.com", "address");

    assertEquals(instance.getCitizenCard(), "123456789ZZ1");
    assertEquals(instance.getSnsNumber(), "123456789");
    assertNotNull(instance);
  }
}
