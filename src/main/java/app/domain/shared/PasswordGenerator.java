package app.domain.shared;

import java.util.Random;

/**
 * Password generator class used to generate passwords.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class PasswordGenerator {
    private final static String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private final static String DIGITS = "0123456789";
    private final static int UPPER_CASE_LETTER_COUNT = 3;
    private final static int LOWER_CASE_LETTER_COUNT = 2;
    private final static int DIGIT_COUNT = 2;

    /**
     * Generates a random password.
     * 
     * @return the generated password.
     */
    public static String generatePwd() {
        // Generate a random string
        // It should have 7 alphanumeric characters, 3 of them being upper case and 2 of
        // them must be digits.

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < UPPER_CASE_LETTER_COUNT; i++) {
            sb.append(UPPER_CASE_LETTERS.charAt(random.nextInt(UPPER_CASE_LETTERS.length())));
        }

        for (int i = 0; i < LOWER_CASE_LETTER_COUNT; i++) {
            sb.append(LOWER_CASE_LETTERS.charAt(random.nextInt(LOWER_CASE_LETTERS.length())));
        }

        for (int i = 0; i < DIGIT_COUNT; i++) {
            sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }

        // shuffle the string
        for (int i = 0; i < sb.length(); i++) {
            int randomIndex = random.nextInt(sb.length());
            char temp = sb.charAt(randomIndex);
            sb.setCharAt(randomIndex, sb.charAt(i));
            sb.setCharAt(i, temp);
        }

        return sb.toString();
    }
}
