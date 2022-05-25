package app.session;

import app.controller.App;
import app.domain.model.VaccinationCenter;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.store.VaccinationCenterStore;
import app.mappers.VaccinationCenterMapper;

public class EmployeeSession {
  private VaccinationCenter vaccinationCenter;

  public EmployeeSession() {}

  public EmployeeSession(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenterListDTO vaccinationCenterDTO) {
    this.vaccinationCenter = VaccinationCenterMapper.toModel(vaccinationCenterDTO);
  }

  public boolean hasCenter() {
    return vaccinationCenter == null ? false : true;
  }
}
