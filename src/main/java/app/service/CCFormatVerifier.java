package app.service;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class CCFormatVerifier {

  public CCFormatVerifier() {
    // For now, only portuguese format is used but this constructor might be used in the future to specify the pretended
    // format.
  }

  public boolean validate(String cc) {
    int sum = 0;
    boolean secondDigit = false;

    if (cc.length() != 12) throw new IllegalArgumentException("Citizen Card length not valid.");

    for (int i = cc.length() - 1; i >= 0; --i) {
      int value = getNumberFromChar(cc.charAt(i));

      if (secondDigit) {
        value *= 2;
        if (value > 9) value -= 9;
      }

      sum += value;
      secondDigit = !secondDigit;
    }

    return (sum % 10) == 0;
  }

  /**
   * Gets the number from the char. Using ASCII table.
   * 
   * @param character the character to convert to a number
   * @return the number represented by the character
   */
  public int getNumberFromChar(char character) {
    if (character >= '0' && character <= '9') return character - '0';
    else if (character >= 'A' && character <= 'Z') return character - 'A' + 10;

    throw new IllegalArgumentException("Invalid Citizen Card number.");
  }
}
