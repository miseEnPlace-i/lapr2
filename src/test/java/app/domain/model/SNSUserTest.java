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
   * Tests if it is not possible to create an SNS User object with an invalid Citizen Card number length.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidCCLengthIsntAllowed() {
    Date c = new Date();
    new SNSUser("987987", "123456789", "name", c, 'M', "+351912345678", "email@email.com",
        "address");
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
   * Tests if it is not possible to create an SNS User object with an invalid name.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidNameIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "222222222", "", c, 'M', "+351912345678", "example@example.com",
        "address");
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid address.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidAddressIsNotAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "222222222", "test", c, 'M', "+351912345678", "example@example.com",
        "");
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

  /**
   * Tests if gets returns the correct values.
   */
  @Test
  public void ensureGetAddressWorksAsExpected() {
    Date c = new Date();
    SNSUser instance = new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351211111111",
        "email@email.com", "address");

    assert instance.getAddress().equals("address");
    assert instance.getBirthDay().equals(c);
    assert instance.getCitizenCard().equals("123456789ZZ1");
    assert instance.getEmail().equals("email@email.com");
    assert instance.getGender() == 'M';
    assert instance.getName().equals("name");
    assert instance.getPhoneNumber().equals("+351211111111");
    assert instance.getSnsNumber().equals("123456789");
  }

  /**
   * Tests if toString() works as expected.
   */
  @Test
  public void ensureToStringWorksAsExpected() {
    Date c = new Date();
    SNSUser instance = new SNSUser("123456789ZZ1", "123456789", "name", c, 'F', "+351211111111",
        "email@email.com", "address");

    String expected =
        "SNS User name: name\nCitizen card number: 123456789ZZ1\nSNS number: 123456789\nBirthday: 19/05/2022\nGender: Female\nPhone number: +351211111111\nEmail: email@email.com\nAddress: address\n";

    assertEquals(instance.toString(), expected);
  }

  /**
   * Tests if equals() works as expected.
   */
  @Test
  public void ensureEqualsWorksAsExpected() {
    Date c = new Date();
    SNSUser instance = new SNSUser("123456789ZZ1", "123456789", "name", c, 'F', "+351211111111",
        "email@email.com", "address");
    SNSUser instance2 = new SNSUser("135510490ZX8", "323456789", "name", c, 'F', "+351211111112",
        "email2@email.com", "address");
    Object randomObject = new Object();
    SNSUser phoneEquals = new SNSUser("346715253ZY4", "523456789", "name", c, 'F', "+351211111111",
        "email3@email.com", "address");
    SNSUser ccEquals = new SNSUser("123456789ZZ1", "523456789", "name", c, 'F', "+351211111115",
        "email3@email.com", "address");
    SNSUser snsNumberEquals = new SNSUser("346715253ZY4", "123456789", "name", c, 'F',
        "+351211111115", "email3@email.com", "address");

    assert instance.equals(instance);
    assert !instance.equals(instance2);
    assert !instance.equals(randomObject);
    assert !instance.equals(null);
    assert instance.equals(phoneEquals);
    assert instance.equals(ccEquals);
    assert instance.equals(snsNumberEquals);
  }
}
