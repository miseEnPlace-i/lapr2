package app.service.sender;

import app.dto.UserNotificationDTO;

public interface ISender {
    public void send(UserNotificationDTO dto) throws Exception;
}
