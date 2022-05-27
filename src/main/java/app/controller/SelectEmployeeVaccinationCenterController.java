package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.VaccinationCenterListDTO;
import app.session.EmployeeSession;

public class SelectEmployeeVaccinationCenterController {
  private VaccinationCenterStore vaccinationCenterStore;

  public SelectEmployeeVaccinationCenterController(Company company) {
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
  }

  public List<VaccinationCenterListDTO> getVaccinationCentersList() {
    return vaccinationCenterStore.getVaccinationCenters();
  }

  public boolean validateVaccinationCenter(VaccinationCenterListDTO vaccinationCenter) {
    return vaccinationCenterStore.exists(vaccinationCenter.getPhone());
  }

  public void selectVaccinationCenter(VaccinationCenterListDTO vaccinationCenter,
      EmployeeSession employeeSession) {
    employeeSession.setVaccinationCenter(vaccinationCenter);
  }
}
