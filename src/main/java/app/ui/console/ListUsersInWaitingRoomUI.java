package app.ui.console;

import java.util.List;
import app.controller.App;
import app.controller.ListUsersWaitingRoomController;
import app.domain.model.WaitingRoom;
import app.dto.ArrivalDTO;
import app.exception.NotAuthorizedException;
import app.mapper.WaitingRoomMapper;
import app.session.EmployeeSession;

public class ListUsersInWaitingRoomUI implements Runnable {
  ListUsersWaitingRoomController controller;

  public ListUsersInWaitingRoomUI(EmployeeSession nurseSession) {
    try {
      this.controller = new ListUsersWaitingRoomController(App.getInstance().getCompany(), nurseSession);
    } catch (NotAuthorizedException e) {
      System.out.println("Nurse is not logged in");
      return;
    }
  }

  public void run() {
    String data = getWaitingRoomDataForNurseCenter();

    if (data == null) System.out.println("\nThere are no users waiting in the waiting Room");
    else System.out.println(data);
  }

  private String getWaitingRoomDataForNurseCenter() {
    // TODO implement Waiting Room DTO
    WaitingRoom waitingRoom = controller.getWaitingRoomListFromNurseCenter();
    if (waitingRoom.size() == 0) return null;

    List<ArrivalDTO> waitingRoomDTO = WaitingRoomMapper.toDto(waitingRoom);

    StringBuilder sb = new StringBuilder();
    sb.append("\n\nWaiting Room:");
    sb.append("\n\n");

    for (ArrivalDTO arrive : waitingRoomDTO)
      sb.append(arrive + "\n");

    return sb.toString();
  }
}
