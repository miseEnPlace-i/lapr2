package app.service.sender;

import app.dto.UserNotificationDTO;

/**
 * Prints the message to the console.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class ConsoleSender implements ISender {
    public ConsoleSender() {}

    public void send(UserNotificationDTO dto) throws Exception {
        System.out.println("\n--- New notification ---\n" + dto.getMessage() + "\n");
    }
}
