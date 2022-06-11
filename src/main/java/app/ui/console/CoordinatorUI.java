package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.controller.App;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.Company;
import app.session.EmployeeSession;
import app.ui.console.utils.Utils;

/**
 * Coordinator UI.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class CoordinatorUI implements Runnable {
  private EmployeeSession employeeSession;
  private FindCoordinatorVaccinationCenterController ctrl;

  public CoordinatorUI() {
    App app = App.getInstance();
    Company company = app.getCompany();
    this.employeeSession = new EmployeeSession();
    this.ctrl = new FindCoordinatorVaccinationCenterController(company, employeeSession);

    this.ctrl.findCoordinatorCenter();
  }

  @Override
  public void run() {
    if (!this.employeeSession.hasCenter()) {
      System.out.println("You are not assigned to a vaccination center. Please contact your administrator.");
      return;
    }

    List<MenuItem> options = new ArrayList<MenuItem>();

    options.add(new MenuItem("Import Legacy Data", new ImportLegacyDataUI(this.employeeSession.getVaccinationCenter())));

    int option = 0;
    do {
      System.out.printf("%nCoordinator Vaccination Center: %s", this.ctrl.getVaccinationCenterName());
      option = Utils.showAndSelectIndex(options, "\n\nCoordinator Menu:");

      if ((option >= 0) && (option < options.size())) options.get(option).run();
    } while (option != -1);
  }

}
