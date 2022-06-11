package app.ui;

import java.util.Properties;
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * @return true if the application is running in console mode, false otherwise
   */
  private static boolean isConsole() {
    Properties props = PropertiesUtils.getProperties();
    String ui = props.getProperty("Environment.UI");

    return ui.equals("console");
  }
}
