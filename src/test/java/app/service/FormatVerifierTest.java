package app.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class FormatVerifierTest {
  @Test
  public void ensureCCFormatIsValid() {
    assertTrue(FormatVerifier.validateCitizenCard("12345678"));
  }

  @Test
  public void ensureCCFormatWithMissingLastDigit() {
    assertFalse(FormatVerifier.validateCitizenCard("123456789ZZ"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCCFormatWithInvalidLastDigit() {
    CCFormatVerifier ccFormatVerifier = new CCFormatVerifier();

    ccFormatVerifier.validate("000000000~'1");
  }

  @Test
  public void ensureCCFormatWithMissingLetter() {
    assertFalse(FormatVerifier.validateCitizenCard("123456789Z1"));
  }

  @Test
  public void ensureCCFormatWithMoreDigits() {
    assertFalse(FormatVerifier.validateCitizenCard("123456789Z111"));
  }

  @Test
  public void ensureCCFormatViolatingDigitRules() {
    assertFalse(FormatVerifier.validateCitizenCard("122222222Z11"));
  }

  @Test
  public void ensureCCFormatViolatingLetterRules() {
    assertFalse(FormatVerifier.validateCitizenCard("000000000ZZ1"));
  }

  @Test
  public void ensureEmailFormatIsValid() {
    assertTrue(FormatVerifier.validateEmail("email@email.com"));
  }

  @Test
  public void ensureEmailFormatViolatingExtension() {
    assertFalse(FormatVerifier.validateEmail("email@email."));
  }

  @Test
  public void ensureEmailFormatWithoutDomain() {
    assertFalse(FormatVerifier.validateEmail("email.com"));
  }

  @Test
  public void ensureEmailFormatWithoutExtension() {
    assertFalse(FormatVerifier.validateEmail("email@email"));
  }

  @Test
  public void ensurePhoneNumberIsValid() {
    assertTrue(FormatVerifier.validatePhoneNumber("+351223456789"));
  }

  @Test
  public void ensurePhoneNumberWithForeignExtension() {
    assertTrue(FormatVerifier.validatePhoneNumber("+44923456789"));
  }

  // Invalid test following new client's clarifications
  // @Test
  // public void ensurePhoneNumberWithoutExtension() {
  //   assertFalse(FormatVerifier.validatePhoneNumber("123456789"));
  // }

  @Test
  public void ensurePhoneNumberWithoutPlusInExtension() {
    assertFalse(FormatVerifier.validatePhoneNumber("351123456789"));
  }

  @Test
  public void ensurePhoneNumberWithMoreDigits() {
    assertFalse(FormatVerifier.validatePhoneNumber("+3511234567890"));
  }

  @Test
  public void ensureFaxNumberIsValid() {
    assertTrue(FormatVerifier.validateFaxNumber("+351223456789"));
  }

  @Test
  public void ensureFaxNumberIsValidWithForeignExtension() {
    assertTrue(FormatVerifier.validateFaxNumber("+44223456789"));
  }

  @Test
  public void ensureFaxNumberWithoutValidFirstDigit() {
    assertFalse(FormatVerifier.validateFaxNumber("+351723456789"));
  }

  // Invalid test following new client's clarifications
  // @Test
  // public void ensureFaxNumberWithoutExtension() {
  //   assertFalse(FormatVerifier.validateFaxNumber("723456789"));
  // }

  @Test
  public void ensureFaxNumberWithoutPlusInExtension() {
    assertFalse(FormatVerifier.validateFaxNumber("351723456789"));
  }

  @Test
  public void ensureFaxNumberWithoutWithMoreDigits() {
    assertFalse(FormatVerifier.validateFaxNumber("+3517234567890"));
  }

  @Test
  public void ensureWebsiteIsValid() {
    assertTrue(FormatVerifier.validateURL("http://www.google.com"));
  }

  @Test
  public void ensureWebsiteIsValidWithSSL() {
    assertTrue(FormatVerifier.validateURL("https://www.google.com"));
  }

  @Test
  public void ensureWebsiteWithInvalidExtension() {
    assertFalse(FormatVerifier.validateURL("http://google."));
  }

  @Test
  public void ensureWebsiteWithoutExtension() {
    assertFalse(FormatVerifier.validateURL("http://google"));
  }

  @Test
  public void ensureWebsiteWithoutProtocol() {
    assertFalse(FormatVerifier.validateURL("www.google.com"));
  }

  @Test
  public void ensureWebsiteWithInvalidProtocol() {
    assertFalse(FormatVerifier.validateURL("abc://www.google.com"));
  }

  @Test
  public void ensureSNSNumberValid() {
    assertTrue(FormatVerifier.validateSNSNumber("123456789"));
  }

  @Test
  public void ensureSNSNumberWithMoreDigits() {
    assertFalse(FormatVerifier.validateSNSNumber("1234567890"));
  }

  @Test
  public void ensureSNSNumberWithLessDigits() {
    assertFalse(FormatVerifier.validateSNSNumber("12345678"));
  }
}
