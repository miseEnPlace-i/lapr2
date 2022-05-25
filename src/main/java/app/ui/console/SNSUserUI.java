package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.ui.console.utils.Utils;

public class SNSUserUI implements Runnable {
  @Override
  public void run() {
    List<MenuItem> options = new ArrayList<MenuItem>();
    options.add(new MenuItem("Schedule a vaccine", new ScheduleVaccineUI()));

    int option = 0;

    do {
      option = Utils.showAndSelectIndex(options, "\n\nSNS User Menu:");

      if ((option >= 0) && (option < options.size())) options.get(option).run();
    } while (option != -1);
  }
}
