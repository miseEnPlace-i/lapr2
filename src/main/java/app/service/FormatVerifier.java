package app.service;

/**
 * Format Verifier with all the rules for the application.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public final class FormatVerifier {
  /**
   * Private constructor to avoid instantiation.
   */
  private FormatVerifier() {}

  /**
   * Validates if the given string is a citizen card number. Follows the rules for portuguese citizens cards.
   * 
   * For reference:
   * https://www.autenticacao.gov.pt/documents/20126/115760/Valida%C3%A7%C3%A3o+de+N%C3%BAmero+de+Documento+do+Cart%C3%A3o+de+Cidad%C3%A3o.pdf/bdc4eb37-7316-3ff4-164a-f869382b7053
   * 
   * @param cc The citizen card number to be verified.
   * @return True if the cc is valid, false otherwise.
   */
  public static boolean validateCC(String cc) {
    CCFormatVerifier ccVerifier = new CCFormatVerifier();

    return ccVerifier.validate(cc);
  }

  /**
   * Validates if the given string is a email.
   * 
   * For reference: https://www.emailregex.com
   * 
   * @param expression The expression to be validated.
   * @return True if the expression is a valid email, false otherwise.
   */
  public static boolean validateEmail(String expression) {
    return expression.matches(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
  }

  /**
   * Validates if the given string is a phone number. Follows the pattern +{extension}{number} Example: +351223456789.
   * Between the extension and the number there is no space. The first digit after the extension must be a 2 or a 9.
   * 
   * @param expression The expression to be validated.
   * @return True if the expression is a valid phone number, false otherwise.
   */
  public static boolean validatePhoneNumber(String expression) {
    return expression.matches("\\+[0-9]{1,3}[2|3|9][0-9]{8}");
  }

  /**
   * Follows the same rules as the phone number.
   *
   * +{extension}{number} Example: +351223456789 The first digit after the extension must be a 2 or a 9.
   * 
   * @param expression The expression to be validated.
   * @return True if the expression is a valid fax number, false otherwise.
   */
  public static boolean validateFaxNumber(String expression) {
    return validatePhoneNumber(expression);
  }

  /**
   * Validates if the given string is a valid URL.
   * 
   * For reference: https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
   * 
   * @param expression The expression to be validated.
   * @return True if the expression is a valid URL, false otherwise.
   */
  public static boolean validateURL(String expression) {
    return expression.matches(
        "http[s]?://(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");
  }

  /**
   * Validates if the given string is a valid SNS Number.
   * 
   * The only rule is that the expression must be composed by 9 digits
   * 
   * @param expression The expression to be validated.
   * @return True if the expression is a valid SNS Number, false otherwise.
   */
  public static boolean validateSNSNumber(String expression) {
    return expression.matches("[0-9]{9}");
  }
}
