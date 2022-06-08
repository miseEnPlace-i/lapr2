package app.ui.console;

import app.controller.App;
import app.controller.SelectEmployeeVaccinationCenterController;
import app.dto.VaccinationCenterListDTO;
import app.session.EmployeeSession;
import app.ui.console.utils.Utils;

public class SelectEmployeeVaccinationCenterUI implements Runnable {
  private SelectEmployeeVaccinationCenterController controller;
  private EmployeeSession employeeSession;

  public SelectEmployeeVaccinationCenterUI(EmployeeSession employeeSession) {
    this.employeeSession = employeeSession;
    this.controller = new SelectEmployeeVaccinationCenterController(App.getInstance().getCompany(), employeeSession);
  }

  public void run() {
    if (controller.getVaccinationCentersList().size() == 0) {
      System.out.println("\n\nThere are no vaccination centers in the system.");
      return;
    }

    while (!controller.employeeHasCenter()) {
      Object center = Utils.showAndSelectOne(controller.getVaccinationCentersList(), "\n\nSelect the Vaccination Center you are working in:\n");

      if (center == null) return;

      try {
        VaccinationCenterListDTO centerDTO = (VaccinationCenterListDTO) center;
        controller.selectVaccinationCenter(centerDTO, employeeSession);
      } catch (ClassCastException e) {
        System.out.println("\n\nInvalid selection.");
      }
    }
  }
}
