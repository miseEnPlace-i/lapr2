package app.service.sender;

import app.domain.shared.Constants;
import app.dto.UserNotificationDTO;
import app.service.FileUtils;

/**
 * SMS Sender class used, for example, to send SMSs notifying the vaccination schedule.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SMSSender implements ISender {
  public SMSSender() {}

  /**
   * Method that writes to a file the SMS to be sent.
   * 
   * @param dto the dto.
   * @throws Exception if the file could not be written.
   */
  public void send(UserNotificationDTO dto) throws Exception {
    String phoneNumber = dto.getPhoneNumber();
    String message = dto.getMessage();

    try {
      String content = String.format("To: %s\nMessage: %s\n\n", phoneNumber, message);
      FileUtils.writeToFile(Constants.SMS_FILE, content);
    } catch (Exception e) {
      throw new Exception("Could not send SMS.");
    }
  }
}
