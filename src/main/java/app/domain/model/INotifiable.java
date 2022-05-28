package app.domain.model;

import app.dto.UserNotificationDTO;

public interface INotifiable {
  private void sendNotification(UserNotificationDTO notificationDto);
}
