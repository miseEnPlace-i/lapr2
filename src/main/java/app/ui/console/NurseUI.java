package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.session.NurseSession;
import app.ui.console.utils.Utils;

public class NurseUI implements Runnable {
  private NurseSession nurseSession = null;

  public NurseUI() {}

  public void run() {
    List<MenuItem> options = new ArrayList<MenuItem>();

    new SelectNurseVaccinationCenterUI(nurseSession).run();

    // If no nurse session was created, don't proceed
    if (nurseSession == null) return;

    options.add(
        new MenuItem("Get users in Waiting Room", new ShowTextUI("\n\nGet users in Waiting Room")));

    int option = 0;
    do {
      option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

      if ((option >= 0) && (option < options.size())) options.get(option).run();
    } while (option != -1);
  }
}
