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
        Date birthDay = null;
    }

    /**
     * Tests if the class doesn't accept birthdays older than 150 years.
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBirthDay() {
        String snsNumber = "123456789";
        String name = "Teste";
        char gender = 'm';
        String phoneNumber = "+351900000000";
        String email = "example@example.com";

        Date birthDay = new Date();
        birthDay.setYear(birthDay.getYear() - 151);

        SNSUser user = new SNSUser(snsNumber, name, birthDay, gender, phoneNumber, email);
    }

    /**
     * Tests if the class doesn't accept birthdays in the future.
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFutureBirthDay() {
        String snsNumber = "123456789";
        String name = "Teste";
        char gender = 'm';
        String phoneNumber = "+351900000000";
        String email = "example@example.com";

        Date birthDay = new Date();
        birthDay.setYear(birthDay.getYear() + 1);

        SNSUser user = new SNSUser(snsNumber, name, birthDay, gender, phoneNumber, email);
    }
}
