package app.controller;

import java.util.List;
import app.controller.session.NurseSession;
import app.domain.model.Company;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;

public class SelectNurseVaccinationCenterController {
  private VaccinationCenterStore vaccinationCenterStore;

  public SelectNurseVaccinationCenterController(Company company) {
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
  }

  public List<VaccinationCenterListDTO> getVaccinationCentersList() {
    return vaccinationCenterStore.getVaccinationCenters();
  }

  public void selectVaccinationCenter(VaccinationCenterListDTO vaccinationCenter,
      NurseSession nurseSession) {
    if (vaccinationCenterStore.exists(vaccinationCenter.getPhone()))
      nurseSession.setVaccinationCenter(vaccinationCenter);
  }
}
