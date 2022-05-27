package app.session;

import app.domain.model.VaccinationCenter;
import app.dto.VaccinationCenterListDTO;
import app.mapper.VaccinationCenterMapper;

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
