package app.ui.console;

import app.controller.App;
import app.controller.SelectNurseVaccinationCenterController;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.session.NurseSession;
import app.ui.console.utils.Utils;

public class SelectNurseVaccinationCenterUI implements Runnable {
  private SelectNurseVaccinationCenterController controller;
  private NurseSession nurseSession;

  public SelectNurseVaccinationCenterUI(NurseSession nurseSession) {
    this.nurseSession = nurseSession;
    this.controller =
        new SelectNurseVaccinationCenterController(App.getInstance().getCompany(), nurseSession);
  }

  public void run() {
    if (controller.listVaccinationCenters().size() == 0) {
      System.out.println("\n\nThere are no vaccination centers in the system.");
      return;
    }

    while (nurseSession == null) {
      Object center =
          Utils.showAndSelectOne(controller.listVaccinationCenters(), "\n\nVaccination Centers\n");

      try {
        VaccinationCenterListDTO centerDTO = (VaccinationCenterListDTO) center;
        nurseSession = controller.selectVaccinationCenter(centerDTO);
      } catch (ClassCastException e) {
        System.out.println("\n\nInvalid selection.");
      }
    }
  }
}
