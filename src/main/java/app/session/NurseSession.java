package app.session;

import app.domain.model.dto.VaccinationCenterListDTO;

public class NurseSession {
  private String username;
  private VaccinationCenterListDTO vaccinationCenter;

  public NurseSession(String username, VaccinationCenterListDTO vaccinationCenter) {
    this.username = username;
    this.vaccinationCenter = vaccinationCenter;
  }

  public String getNurseName() {
    return username;
  }

  public VaccinationCenterListDTO getVaccinationCenter() {
    return vaccinationCenter;
  }
}
