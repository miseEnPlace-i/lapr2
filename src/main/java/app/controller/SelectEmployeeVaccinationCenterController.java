package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.VaccinationCenterListDTO;
import app.session.EmployeeSession;


/**
 * SelectEmployeeVaccinationCenterController
 * 
 * @author Ricardo Moreira <1211288@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class SelectEmployeeVaccinationCenterController {
  private VaccinationCenterStore vaccinationCenterStore;
  private EmployeeSession session;

  public SelectEmployeeVaccinationCenterController(Company company, EmployeeSession session) {
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
    this.session = session;
  }

  /**
   * @return a list of VaccinationCenterListDTOs representing the vaccination centers of the company
   */
  public List<VaccinationCenterListDTO> getVaccinationCentersList() {
    return vaccinationCenterStore.getVaccinationCenters();
  }

  /**
   * @param vaccinationCenter the selected vaccination center
   * @return true if the selected vaccination center is valid, false otherwise
   */
  public boolean validateVaccinationCenter(VaccinationCenterListDTO vaccinationCenter) {
    return vaccinationCenterStore.exists(vaccinationCenter.getPhone());
  }

  /**
   * Selects the vaccination center in the current session
   * 
   * @param vaccinationCenter the selected vaccination center
   * @param employeeSession the employee session
   */
  public void selectVaccinationCenter(VaccinationCenterListDTO vaccinationCenter, EmployeeSession employeeSession) {
    VaccinationCenter center = vaccinationCenterStore.getVaccinationCenterWithEmail(vaccinationCenter.getEmail());
    employeeSession.setVaccinationCenter(center);
  }

  /**
   * @return true if the vaccination center is selected, false otherwise
   */
  public boolean employeeHasCenter() {
    return session.hasCenter();
  }
}
