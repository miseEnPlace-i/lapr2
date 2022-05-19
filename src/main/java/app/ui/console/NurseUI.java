package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.session.NurseSession;
import app.ui.console.utils.Utils;

public class NurseUI implements Runnable {
  private NurseSession nurseSession = new NurseSession();

  public NurseUI() {}

  public void run() {
    List<MenuItem> options = new ArrayList<MenuItem>();

    new SelectNurseVaccinationCenterUI(nurseSession).run();

    if (!nurseSession.isLoggedIn()) return;

    options
        .add(new MenuItem("Get users in Waiting Room", new ListUsersInWaitingRoomUI(nurseSession)));

    int option = 0;
    do {
      System.out.printf("%nNurse Vaccination Center: %s",
          nurseSession.getVaccinationCenter().getName());
      option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

      if ((option >= 0) && (option < options.size())) options.get(option).run();
    } while (option != -1);
  }
}
