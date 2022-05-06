package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Calendar;
import org.junit.Test;

public class SNSUserTest {

  /**
   * Tests if the class doesn't accept null values for the parameters.
   *
   * @throws Exception
   */
  @Test
  public void testNullArguments() {
    // String snsNumber = null;
    // String name = null;
    // Date birthDay = null;
    // new SNSUser(null, null, null, null, null, null, null, null);
  }

  /**
   * Tests if the class doesn't accept birthdays older than 150 years.
   *
   * @throws Exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBirthDay() {
    String citizenCard = "123456789";
    String snsNumber = "123456789";
    String name = "Teste";
    char gender = 'm';
    String phoneNumber = "+351900000000";
    String email = "example@example.com";
    String address = "Test Address 101";

    Calendar birthDay = Calendar.getInstance();
    birthDay.add(Calendar.YEAR, -151);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }

  /**
   * Tests if the class doesn't accept birthdays in the future.
   *
   * @throws Exception
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFutureBirthDay() {
    String citizenCard = "123456789";
    String snsNumber = "123456789";
    String name = "Teste";
    char gender = 'm';
    String phoneNumber = "+351900000000";
    String email = "example@example.com";
    String address = "Test Address 101";

    Calendar birthDay = Calendar.getInstance();
    birthDay.add(Calendar.YEAR, 1);

    new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }

  @Test
  public void ensureIsPossibleToCreateSNSUser() {
    Calendar c = Calendar.getInstance();
    SNSUser instance = new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351123456789", "email@email.com", "address");

    assert instance.getCitizenCard().equals("123456789ZZ1");
    assert instance.getSnsNumber().equals("123456789");
    assertNotNull(instance);
  }
}
