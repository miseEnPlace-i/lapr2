package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.shared.Gender;

public class RegisterSNSUserTest {
  private RegisterSNSUserController ctrl;
  private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

  @Before
  public void setUp() {
    ctrl = new RegisterSNSUserController(new Company("designation", "12345"));
  }

  @Test
  public void ensureSNSUserIsCreatedSuccessfully() throws ParseException {
    String cc = "32517335";
    String snsNumber = "323456789";
    String name = "test";
    Date birthDay = sdf.parse("01/01/2000");
    Gender gender = Gender.MALE;
    String phoneNumber = "+351910000002";
    String email = "example3@example.com";
    String address = "test";

    ctrl.create(cc, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }

  @Test
  public void ensureStringifyDataIsCorrect() throws ParseException {
    String cc = "18925769";
    String snsNumber = "223456789";
    String name = "test";
    Date birthDay = sdf.parse("01/01/2000");
    Gender gender = Gender.MALE;
    String phoneNumber = "+351910000001";
    String email = "example2@example.com";
    String address = "test";

    ctrl.create(cc, snsNumber, name, birthDay, gender, phoneNumber, email, address);

    String stringified = ctrl.stringifyData();
    String expected =
        "SNS User name: test\nCitizen card number: 18925769\nSNS number: 223456789\nBirthday: 01/01/2000\nGender: Male\nPhone number: +351910000001\nEmail: example2@example.com\nAddress: test\n";

    assertEquals(stringified, expected);
  }

  @Test
  public void ensureSaveWorksAsExpected() throws ParseException {
    String cc = "19233952";
    String snsNumber = "123456789";
    String name = "test";
    Date birthDay = sdf.parse("01/01/2000");
    Gender gender = Gender.MALE;
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

