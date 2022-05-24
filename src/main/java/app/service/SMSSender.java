package app.service;

import app.domain.shared.Constants;

/**
 * SMS Sender class used, for example, to send SMSs notifying the vaccination schedule.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SMSSender {
  /**
   * Static method that writes to a file the SMS to be sent.
   * 
   * @param phoneNumber the phone number to send the SMS to.
   * @param message the message to send.
   * @throws Exception if the file could not be written.
   */
  public static void send(String phoneNumber, String message) throws Exception {
    try {
      String content = String.format("To: %s%nMessage: %s%n%n", phoneNumber, message);
      FileUtils.writeToFile(Constants.SMS_FILE, content);
    } catch (Exception e) {
      throw new Exception("Could not send SMS.");
    }
  }
}
