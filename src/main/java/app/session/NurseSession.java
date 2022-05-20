package app.session;

import app.domain.model.dto.VaccinationCenterListDTO;

public class NurseSession {
  private VaccinationCenterListDTO vaccinationCenter;

  public NurseSession() {}

  public NurseSession(String username, VaccinationCenterListDTO vaccinationCenter) {
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
