package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.model.dto.ArrivalDTO;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;
import app.exception.NotAuthorizedException;
import app.mappers.WaitingRoomMapper;
import app.session.EmployeeSession;

public class ListUsersWaitingRoomController {
  private VaccinationCenterStore vaccinationCenterStore;
  private EmployeeSession nurseSession;

  /**
   * Constructor for ListUsersWaitingRoomController.
   */
  public ListUsersWaitingRoomController(Company company, EmployeeSession nurseSession)
      throws NotAuthorizedException {
    if (!nurseSession.hasCenter()) throw new NotAuthorizedException("Nurse is not logged in");
    this.nurseSession = nurseSession;
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
  }

  public List<ArrivalDTO> getWaitingRoomListFromNurseCenter() {
    VaccinationCenter nurseVaccinationCenter = nurseSession.getVaccinationCenter();

    // TODO refactor using equals
    // TODO refactor using Waiting Room DTO
    WaitingRoom waitingRoom =
        vaccinationCenterStore.getWaitingRoom(nurseVaccinationCenter.getPhone());

    List<ArrivalDTO> waitingRoomDTO = WaitingRoomMapper.toDto(waitingRoom);

    return waitingRoomDTO;
  }
}
