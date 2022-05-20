package app.service;

/**
 * SMS Sender class used, for example, to send SMSs notifying the vaccination schedule.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SMSSender {
    private final static String SMS_FILE = "SMS.txt";

    /**
     * Static method that writes to a file the SMS to be sent.
     * 
     * @param phoneNumber the phone number to send the SMS to.
     * @param message the message to send.
     * @return true if the SMS was sent successfully, false otherwise.
     * @throws Exception if the file could not be written.
     */
    public static boolean send(String phoneNumber, String message) throws Exception {
        try {
            String content = String.format("To: %s\nMessage: %s\n\n", phoneNumber, message);
            FileUtils.writeToFile(SMS_FILE, content);
            return true;
        } catch (Exception e) {
            throw new Exception("Could not send SMS.");
        }
    }
}
