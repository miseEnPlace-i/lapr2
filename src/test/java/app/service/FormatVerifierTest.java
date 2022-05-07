package app.service;

import org.junit.Test;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class FormatVerifierTest {
  @Test
  public void ensureCCFormatIsValid() {
    assert FormatVerifier.validateCC("123456789ZZ1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCCFormatWithMissingLastDigit() {
    assert !FormatVerifier.validateCC("123456789ZZ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCCFormatWithMissingLetter() {
    assert !FormatVerifier.validateCC("123456789Z1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureCCFormatWithMoreDigits() {
    assert !FormatVerifier.validateCC("123456789Z111");
  }

  @Test
  public void ensureCCFormatViolatingDigitRules() {
    assert !FormatVerifier.validateCC("122222222Z11");
  }

  @Test
  public void ensureCCFormatViolatingLetterRules() {
    assert !FormatVerifier.validateCC("000000000ZZ1");
  }

  @Test
  public void ensureEmailFormatIsValid() {
    assert FormatVerifier.validateEmail("email@email.com");
  }

  @Test
  public void ensureEmailFormatViolatingExtension() {
    assert !FormatVerifier.validateEmail("email@email.");
  }

  @Test
  public void ensureEmailFormatWithoutDomain() {
    assert !FormatVerifier.validateEmail("email.com");
  }

  @Test
  public void ensureEmailFormatWithoutExtension() {
    assert !FormatVerifier.validateEmail("email@email");
  }

  @Test
  public void ensurePhoneNumberIsValid() {
    assert FormatVerifier.validatePhoneNumber("+351223456789");
  }

  @Test
  public void ensurePhoneNumberWithForeignExtension() {
    assert FormatVerifier.validatePhoneNumber("+44923456789");
  }

  @Test
  public void ensurePhoneNumberWithoutExtension() {
    assert !FormatVerifier.validatePhoneNumber("123456789");
  }

  @Test
  public void ensurePhoneNumberWithoutPlusInExtension() {
    assert !FormatVerifier.validatePhoneNumber("351123456789");
  }

  @Test
  public void ensurePhoneNumberWithMoreDigits() {
    assert !FormatVerifier.validatePhoneNumber("+3511234567890");
  }

  @Test
  public void ensureFaxNumberIsValid() {
    assert FormatVerifier.validateFaxNumber("+351223456789");
  }

  @Test
  public void ensureFaxNumberIsValidWithForeignExtension() {
    assert FormatVerifier.validateFaxNumber("+44223456789");
  }

  @Test
  public void ensureFaxNumberWithoutValidFirstDigit() {
    assert !FormatVerifier.validateFaxNumber("+351723456789");
  }

  @Test
  public void ensureFaxNumberWithoutExtension() {
    assert !FormatVerifier.validateFaxNumber("723456789");
  }

  @Test
  public void ensureFaxNumberWithoutPlusInExtension() {
    assert !FormatVerifier.validateFaxNumber("351723456789");
  }

  @Test
  public void ensureFaxNumberWithoutWithMoreDigits() {
    assert !FormatVerifier.validateFaxNumber("+3517234567890");
  }

  @Test
  public void ensureWebsiteIsValid() {
    assert FormatVerifier.validateURL("http://www.google.com");
  }

  @Test
  public void ensureWebsiteIsValidWithSSL() {
    assert FormatVerifier.validateURL("https://www.google.com");
  }

  @Test
  public void ensureWebsiteWithInvalidExtension() {
    assert !FormatVerifier.validateURL("http://google.");
  }

  @Test
  public void ensureWebsiteWithoutExtension() {
    assert !FormatVerifier.validateURL("http://google");
  }

  @Test
  public void ensureWebsiteWithoutProtocol() {
    assert !FormatVerifier.validateURL("www.google.com");
  }

  @Test
  public void ensureWebsiteWithInvalidProtocol() {
    assert !FormatVerifier.validateURL("abc://www.google.com");
  }

  @Test
  public void ensureSNSNumberValid() {
    assert FormatVerifier.validateSNSNumber("123456789");
  }

  @Test
  public void ensureSNSNumberWithMoreDigits() {
    assert !FormatVerifier.validateSNSNumber("1234567890");
  }

  @Test
  public void ensureSNSNumberWithLessDigits() {
    assert !FormatVerifier.validateSNSNumber("12345678");
  }
}
