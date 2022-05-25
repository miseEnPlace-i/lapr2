package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.ui.console.utils.Utils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class ReceptionistUI extends EmployeeWithCenterUI {
  public ReceptionistUI() {
    super();
  }

  public void callback() {
    List<MenuItem> options = new ArrayList<MenuItem>();

    options.add(new MenuItem("Register a SNS User", new RegisterSNSUserUI()));
    options.add(new MenuItem("Schedule a vaccine", new ScheduleVaccineReceptionistUI()));
    options.add(new MenuItem("Register an Arrival", new RegisterSNSUserArrivalUI(super.getEmployeeSession().getVaccinationCenter())));

    int option = 0;
    do {
      System.out.printf("%nReceptionist Vaccination Center: %s",
          super.getCurrentVaccinationCenter());
      option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

      if ((option >= 0) && (option < options.size())) options.get(option).run();
    } while (option != -1);
  }
}
