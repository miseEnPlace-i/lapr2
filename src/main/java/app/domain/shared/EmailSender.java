package app.domain.shared;

/**
 * Email Sender class used to send emails with the password to the user.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class EmailSender {

    public EmailSender() {}

    /**
     * Sends an email with the password to the user.
     * 
     * @param email the email to send the password to.
     * @param name the user name.
     * @param password the password to send.
     */
    public static void sendPasswordEmail(String email, String name, String password) {
        // TODO
    }

    /**
     * Sends an email notifying the user about his vaccination schedule.
     * 
     * @param
     */
    public static void sendVaccinationScheduleEmail() {
        // TODO
    }

}
