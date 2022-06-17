package app.domain.model;

import java.util.TimerTask;
import app.dto.UserNotificationDTO;
import app.service.sender.ISender;
import app.service.sender.SenderFactory;

public class RemoveRecoveryRoomTask extends TimerTask {

  private VaccineAdministration vacAdmin;
  private UserNotificationDTO not;

  public RemoveRecoveryRoomTask(VaccineAdministration vacAdmin,
      UserNotificationDTO not) {
    this.vacAdmin = vacAdmin;
    this.not = not;
  }

  @Override
  public void run() {
    this.vacAdmin.getVaccinationCenter().getRecoveryRoom()
        .removeVaccineAdministration(vacAdmin);
    sendNotification(not);
  }

  private void sendNotification(UserNotificationDTO notificationDto) {
    ISender sender = SenderFactory.getSender();

    try {
      sender.send(notificationDto);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
