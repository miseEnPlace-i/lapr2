package app.controller;

import java.util.List;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.dto.ArrivalDTO;
import app.exception.NotAuthorizedException;
import app.mapper.WaitingRoomMapper;
import app.session.EmployeeSession;

public class ListUsersInWaitingRoomController {
  private EmployeeSession nurseSession;

  /**
   * Constructor for ListUsersWaitingRoomController.
   */
  public ListUsersInWaitingRoomController(EmployeeSession nurseSession) throws NotAuthorizedException {
    if (!nurseSession.hasCenter()) throw new NotAuthorizedException("Nurse is not logged in");
    this.nurseSession = nurseSession;
  }

  public List<ArrivalDTO> getWaitingRoomListFromNurseCenter() {
    VaccinationCenter vaccinationCenter = nurseSession.getVaccinationCenter();

    WaitingRoom waitingRoom = vaccinationCenter.getWaitingRoom();
    List<ArrivalDTO> waitingRoomDto = WaitingRoomMapper.toDto(waitingRoom);

    return waitingRoomDto;
  }
}
