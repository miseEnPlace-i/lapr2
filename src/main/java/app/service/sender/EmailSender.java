package app.service.sender;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang3.NotImplementedException;
import app.domain.shared.Constants;
import app.dto.UserNotificationDTO;
import app.service.FileUtils;

/**
 * Email Sender class used to send emails with the password to the user.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class EmailSender implements ISender {

  /**
   * Constructor for EmailSender.
   */
  public EmailSender() {}

  /**
   * Sends an email with the password to the user.
   * 
   * @param dto the dto.
   * @throws Exception if the email could not be sent.
   */
  public void send(UserNotificationDTO dto) throws Exception {
    String email = dto.getEmail();
    String message = dto.getMessage();

    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      String now = sdf.format(new Date());
      String content = String.format("On %s\nTo: %s\nMessage: %s\n\n--------------------\n\n", now, email, message);
      FileUtils.writeToFile(Constants.EMAIL_FILE, content);
    } catch (Exception e) {
      throw new Exception("Could not send Email.");
    }
  }
}
