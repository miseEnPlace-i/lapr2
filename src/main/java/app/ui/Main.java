package app.ui;

import app.ui.console.MainMenuUI;
import app.ui.gui.App;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
// Teste
public class Main {
  public static void main(String[] args) {
    try {
      App.launch(App.class, args);

      MainMenuUI menu = new MainMenuUI();

      menu.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
