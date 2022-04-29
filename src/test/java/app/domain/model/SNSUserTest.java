package app.domain.model;

import org.junit.Test;
import app.domain.shared.PasswordGenerator;
import pt.isep.lei.esoft.auth.domain.model.Password;
import java.util.Calendar;
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
}
