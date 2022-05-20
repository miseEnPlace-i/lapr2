package app.ui.console;

import app.controller.App;
import app.controller.ListUsersWaitingRoomController;
import app.domain.model.Arrive;
import app.domain.model.WaitingRoom;
import app.exception.NotAuthorizedException;
import app.session.NurseSession;

public class ListUsersInWaitingRoomUI implements Runnable {
  ListUsersWaitingRoomController controller;

  public ListUsersInWaitingRoomUI(NurseSession nurseSession) {
    try {
      this.controller =
          new ListUsersWaitingRoomController(App.getInstance().getCompany(), nurseSession);
    } catch (NotAuthorizedException e) {
      System.out.println("Nurse is not logged in");
      return;
    }
  }

  public void run() {
    String data = getWaitingRoomDataForNurseCenter();

    System.out.println(data);
  }

  private String getWaitingRoomDataForNurseCenter() {
    // TODO implement Waiting Room DTO
    WaitingRoom waitingRoom = controller.getWaitingRoomListFromNurseCenter();

    StringBuilder sb = new StringBuilder();
    sb.append("\n\nWaiting Room:");
    sb.append("\n\n");

    for (Arrive arrive : waitingRoom) {
      sb.append(arrive.getTime());
      sb.append("\n");
    }

    return sb.toString();
  }
}
