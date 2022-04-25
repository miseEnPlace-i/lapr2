package app.domain.model;

import org.junit.Test;

import java.util.Date;

public class SNSUserTest {

    /**
     * Tests if the class doesn't accept null values for the parameters.
     *
     * @throws Exception
     */
    @Test
    public void testNullArguments() {
        String snsNumber = null;
        String name = null;
        Date birthDate = null;
    }

    /**
     * Tests if the class doesn't accept birthdays older than 150 years.
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBirthDate() {
        String snsNumber = "123456789";
        String name = "Teste";
        char gender = 'm';
        String phoneNumber = "+351900000000";
        String email = "example@example.com";

        Date birthDate = new Date();
        birthDate.setYear(birthDate.getYear() - 151);

        SNSUser user = new SNSUser(snsNumber, name, birthDate, gender, phoneNumber, email);
    }

    /**
     * Tests if the class doesn't accept birthdays in the future.
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFutureBirthDate() {
        String snsNumber = "123456789";
        String name = "Teste";
        char gender = 'm';
        String phoneNumber = "+351900000000";
        String email = "example@example.com";

        Date birthDate = new Date();
        birthDate.setYear(birthDate.getYear() + 1);

        SNSUser user = new SNSUser(snsNumber, name, birthDate, gender, phoneNumber, email);
    }
}
