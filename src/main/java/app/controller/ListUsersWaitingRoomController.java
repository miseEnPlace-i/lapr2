package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;
import app.session.NurseSession;

public class ListUsersWaitingRoomController {
  private Company company;
  private VaccinationCenterStore vaccinationCenterStore;
  private NurseSession nurseSession;

  /**
   * Constructor for ListUsersWaitingRoomController.
   */
  public ListUsersWaitingRoomController(Company company, NurseSession nurseSession) {
    this.company = company;
    this.nurseSession = nurseSession;
  }

  public List<SNSUser> getWaitingRoomListFromNurseCenter() {
    VaccinationCenterListDTO nurseVaccinationCenter = nurseSession.getVaccinationCenter();

    return null;
  }
}
