package app.session;

import app.domain.model.VaccinationCenter;

public class EmployeeSession {
  private VaccinationCenter vaccinationCenter;

  public EmployeeSession() {}

  public EmployeeSession(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
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
}
