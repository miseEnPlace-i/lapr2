package app.service.sender;

import org.apache.commons.lang3.NotImplementedException;
import app.dto.UserNotificationDTO;

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
    // TODO
    throw new NotImplementedException("Not implemented yet");
  }
}
