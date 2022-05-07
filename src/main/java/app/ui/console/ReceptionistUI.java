package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.ui.console.utils.Utils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class ReceptionistUI implements Runnable {
  public ReceptionistUI() {}

  public void run() {
    List<MenuItem> options = new ArrayList<MenuItem>();
    options.add(new MenuItem("Option A ", new ShowTextUI("You have chosen Option A.")));
    options.add(new MenuItem("Option B ", new ShowTextUI("You have chosen Option B.")));
    options.add(new MenuItem("Option C ", new ShowTextUI("You have chosen Option C.")));
    options.add(new MenuItem("Register a SNS User", new RegisterSNSUserUI()));

    int option = 0;
    do {
      option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

      if ((option >= 0) && (option < options.size())) {
        options.get(option).run();
      }
    } while (option != -1);
  }
}
