package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.Date;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;

public class RegisterSNSUserTest {
    Company company = new Company("designation");
    private RegisterSNSUserController ctrl = new RegisterSNSUserController(company);

    @Before
    public void setUp() {}

    @Test
    public void ensureSNSUserIsCreatedSuccessfully() throws ParseException {
        String cc = "325173354ZV5";
        String snsNumber = "323456789";
        String name = "test";
        Date birthDay = new Date(20000);
        char gender = 'm';
        String phoneNumber = "+351910000002";
        String email = "example3@example.com";
        String address = "test";

        ctrl.create(cc, snsNumber, name, birthDay, gender, phoneNumber, email, address);
    }

    @Test
    public void ensureStringifyDataIsCorrect() throws ParseException {
        String cc = "189257695ZY4";
        String snsNumber = "223456789";
        String name = "test";
        Date birthDay = new Date(20000);
        char gender = 'm';
        String phoneNumber = "+351910000001";
        String email = "example2@example.com";
        String address = "test";

        ctrl.create(cc, snsNumber, name, birthDay, gender, phoneNumber, email, address);

        String stringified = ctrl.stringifyData();
        String expected =
                "SNS User name: test\nCitizen card number: 189257695ZY4\nSNS number: 223456789\nBirthday: 01/01/1970\nGender: Male\nPhone number: +351910000001\nEmail: example2@example.com\nAddress: test\n";

        assertEquals(stringified, expected);
    }

    @Test
    public void ensureSaveWorksAsExpected() throws ParseException {
        String cc = "192339524ZY2";
        String snsNumber = "123456789";
        String name = "test";
        Date birthDay = new Date(20000);
        char gender = 'm';
        String phoneNumber = "+351910000000";
        String email = "example@example.com";
        String address = "test";

        ctrl.create(cc, snsNumber, name, birthDay, gender, phoneNumber, email, address);
        ctrl.save();
    }

    @Test
    public void ensureResourceNameIsCorrect() {
        assertEquals(ctrl.getResourceName(), "SNS User");
    }
}

