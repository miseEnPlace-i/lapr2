package app.session;

import app.domain.model.dto.VaccinationCenterListDTO;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
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

  public boolean hasCenter() {
    return vaccinationCenter == null ? false : true;
  }
}
