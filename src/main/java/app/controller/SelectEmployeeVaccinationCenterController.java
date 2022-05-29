package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.VaccinationCenterListDTO;
import app.session.EmployeeSession;

public class SelectEmployeeVaccinationCenterController {
  private VaccinationCenterStore vaccinationCenterStore;
  private EmployeeSession session;

  public SelectEmployeeVaccinationCenterController(Company company, EmployeeSession session) {
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
    this.session = session;
  }

  public List<VaccinationCenterListDTO> getVaccinationCentersList() {
    return vaccinationCenterStore.getVaccinationCenters();
  }

  public boolean validateVaccinationCenter(VaccinationCenterListDTO vaccinationCenter) {
    return vaccinationCenterStore.exists(vaccinationCenter.getPhone());
  }

  public void selectVaccinationCenter(VaccinationCenterListDTO vaccinationCenter, EmployeeSession employeeSession) {
    VaccinationCenter center = vaccinationCenterStore.getVaccinationCenterWithEmail(vaccinationCenter.getEmail());
    employeeSession.setVaccinationCenter(center);
  }

  public boolean employeeHasCenter() {
    return session.hasCenter();
  }
}
