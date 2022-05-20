package app.ui.console;

import app.controller.App;
import app.controller.ListUsersWaitingRoomController;
import app.domain.model.Arrival;
import app.controller.session.NurseSession;
import app.domain.model.WaitingRoom;
import app.exception.NotAuthorizedException;

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

    if (data == null) System.out.println("\nThere are no users waiting in the waiting Room");
    else System.out.println(data);
  }

  private String getWaitingRoomDataForNurseCenter() {
    // TODO implement Waiting Room DTO
    WaitingRoom waitingRoom = controller.getWaitingRoomListFromNurseCenter();

    if (waitingRoom.size() == 0) return null;

    StringBuilder sb = new StringBuilder();
    sb.append("\n\nWaiting Room:");
    sb.append("\n\n");

    for (Arrival arrive : waitingRoom) {
      sb.append(arrive.getTime());
      sb.append("\n");
    }

    return sb.toString();

  }
}
