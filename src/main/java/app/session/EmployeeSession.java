package app.session;

import app.domain.model.VaccinationCenter;

public class EmployeeSession {
  private VaccinationCenter vaccinationCenter;
  private String email;

  public EmployeeSession() {}

  public EmployeeSession(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
    this.email = null;
  }

  public EmployeeSession(VaccinationCenter vaccinationCenter, String email) {
    this.vaccinationCenter = vaccinationCenter;
    this.email = email;
  }

  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public boolean hasCenter() {
    return vaccinationCenter == null ? false : true;
  }

  public String getEmail() {
    return email;
  }
}
