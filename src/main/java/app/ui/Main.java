package app.ui;

import java.util.Properties;
import app.controller.App;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;
import app.ui.console.MainMenuUI;
import app.ui.gui.ApplicationUI;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
// Teste
public class Main {
  public static void main(String[] args) {
    try {
      if (isConsole()) {
        MainMenuUI menu = new MainMenuUI();
        menu.run();
      } else ApplicationUI.launch(ApplicationUI.class, args);

      // Save company when program exits
      App.getInstance().saveCurrentCompany();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @return true if the application is running in console mode, false otherwise
   */
  private static boolean isConsole() {
    Properties props = PropertiesUtils.getProperties();
    String ui = props.getProperty(Constants.PARAMS_UI_ENVIRONMENT);

    if (ui.equals("console")) return true;
    else if (ui.equals("gui")) return false;
    else throw new IllegalArgumentException("Unknown UI: " + ui);
  }
}
