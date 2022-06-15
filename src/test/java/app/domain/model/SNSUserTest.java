package app.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import app.domain.shared.Gender;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class SNSUserTest {

  /**
   * Tests if the class doesn't accept null values for the parameters.
   *
   * @throws Exception
   */
  @Test(expected = Exception.class)
  public void ensureNullArgumentsAreNotAllowed() {
    new SNSUser(null, null, null, null, null, null, null, null);
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
    Gender gender = Gender.MALE;
    String phoneNumber = "+351900000000";
    String email = "example@example.com";

    Date birthDay = new Date();
    birthDay = DateUtils.addYears(birthDay, -151);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, new Address("street", 1, "11-11", "city"));
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
    Gender gender = Gender.MALE;
    String phoneNumber = "+351900000000";
    String email = "example@example.com";

    Date birthDay = new Date();
    birthDay = DateUtils.addYears(birthDay, 1);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, new Address("street", 1, "11-11", "city"));
  }

  /**
   * TTests if it is not possible to create an SNS User object with an invalid phone number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidPhoneNumbersAreNotAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "123456789", "name", c, Gender.MALE, "0", "email@email.com", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid Citizen Card number length.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidCCLengthIsntAllowed() {
    Date c = new Date();
    new SNSUser("987987", "123456789", "name", c, Gender.MALE, "+351912345678", "email@email.com", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid Citizen Card number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidCCIsNotAllowed() {
    Date c = new Date();
    new SNSUser("000000000ZZ0", "123456789", "name", c, Gender.MALE, "+351912345678", "email@email.com", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid Email.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidEmailIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "123456789", "name", c, Gender.MALE, "+351912345678", "2h1125h1q5as", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid SNS Number.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidSNSNumberIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "1111111111111111111", "name", c, Gender.MALE, "+351912345678", "2h1125h1q5as", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid name.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidNameIsntAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "222222222", "", c, Gender.MALE, "+351912345678", "example@example.com", new Address("street", 1, "11-11", "city"));
  }

  /**
   * Tests if it is not possible to create an SNS User object with an invalid address.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidAddressIsNotAllowed() {
    Date c = new Date();
    new SNSUser("123456789ZZ1", "222222222", "test", c, Gender.MALE, "+351912345678", "example@example.com", new Address("", 0, "", ""));
  }

  /**
   * Tests if it is possible to create an SNS User object correctly
   */
  @Test
  public void ensureIsPossibleToCreateSNSUser() {
    Date c = new Date();
    SNSUser instance =
        new SNSUser("12345678", "123456789", "name", c, Gender.MALE, "+351211111111", "email@email.com", new Address("street", 1, "11-11", "city"));

    assertEquals(instance.getCitizenCard(), "12345678");
    assertEquals(instance.getSnsNumber(), "123456789");
    assertNotNull(instance);
  }

  /**
   * Tests if gets returns the correct values.
   */
  @Test
  public void ensureGetsWorkAsExpected() {
    Date c = new Date();
    c = DateUtils.addYears(c, -23);
    SNSUser instance =
        new SNSUser("12345678", "123456789", "name", c, Gender.MALE, "+351211111111", "email@email.com", new Address("street", 1, "11-11", "city"));

    assert instance.getAddress().equals(new Address("street", 1, "11-11", "city"));
    assert instance.getBirthDay().equals(c);
    assert instance.getCitizenCard().equals("12345678");
    assert instance.getEmail().equals("email@email.com");
    assert instance.getGender() == Gender.MALE;
    assert instance.getAge() == 23;
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    SNSUser instance =
        new SNSUser("12345678", "123456789", "name", c, Gender.FEMALE, "+351211111111", "email@email.com", new Address("street", 1, "11-11", "city"));

    String expected = "SNS User name: name\nCitizen card number: 12345678\nSNS number: 123456789\nBirthday: " + sdf.format(new Date())
        + "\nGender: Female\nPhone number: +351211111111\nEmail: email@email.com\nAddress: address\n";

    assertEquals(instance.toString(), expected);
  }

  /**
   * Tests if equals() works as expected.
   */
  @Test
  public void ensureEqualsWorksAsExpected() {
    Date c = new Date();
    SNSUser instance =
        new SNSUser("12345678", "123456789", "name", c, Gender.FEMALE, "+351211111111", "email@email.com", new Address("street", 1, "11-11", "city"));
    SNSUser instance2 =
        new SNSUser("13551049", "323456789", "name", c, Gender.FEMALE, "+351211111112", "email2@email.com", new Address("street", 1, "11-11", "city"));
    Object randomObject = new Object();
    SNSUser emailEquals =
        new SNSUser("34671525", "523456789", "name", c, Gender.FEMALE, "+351211111117", "email@email.com", new Address("street", 1, "11-11", "city"));
    SNSUser phoneEquals =
        new SNSUser("34671525", "523456789", "name", c, Gender.FEMALE, "+351211111111", "email3@email.com", new Address("street", 1, "11-11", "city"));
    SNSUser ccEquals =
        new SNSUser("12345678", "523456789", "name", c, Gender.FEMALE, "+351211111115", "email3@email.com", new Address("street", 1, "11-11", "city"));
    SNSUser snsNumberEquals =
        new SNSUser("34671525", "123456789", "name", c, Gender.FEMALE, "+351211111115", "email3@email.com", new Address("street", 1, "11-11", "city"));

    assert instance.equals(instance);
    assert !instance.equals(instance2);
    assert !instance.equals(randomObject);
    assert !instance.equals(null);
    assert instance.equals(emailEquals);
    assert instance.equals(phoneEquals);
    assert instance.equals(ccEquals);
    assert instance.equals(snsNumberEquals);
  }
}
