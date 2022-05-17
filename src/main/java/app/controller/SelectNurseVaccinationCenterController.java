package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;
import app.session.NurseSession;

public class SelectNurseVaccinationCenterController {
  private VaccinationCenterStore vaccinationCenterStore;
  private NurseSession nurseSession;

  public SelectNurseVaccinationCenterController(Company company, NurseSession nurseSession) {
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
    this.nurseSession = nurseSession;
  }

  public List<VaccinationCenterListDTO> listVaccinationCenters() {
    return vaccinationCenterStore.getVaccinationCenters();
  }

  public NurseSession selectVaccinationCenter(VaccinationCenterListDTO vaccinationCenter) {
    if (vaccinationCenterStore.exists(vaccinationCenter.getPhone())) {
      String nurseName = App.getInstance().getCurrentUserSession().getUserName();

      return new NurseSession(nurseName, vaccinationCenter);
    }

    return null;
  }
}
