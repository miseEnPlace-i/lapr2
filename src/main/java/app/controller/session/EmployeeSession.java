package app.controller.session;

import app.domain.model.dto.VaccinationCenterListDTO;

public class EmployeeSession {
  private VaccinationCenterListDTO vaccinationCenter;

  public EmployeeSession() {}

  public EmployeeSession(String username, VaccinationCenterListDTO vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public VaccinationCenterListDTO getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenterListDTO vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public boolean isLoggedIn() {
    return vaccinationCenter == null ? false : true;
  }
}
