package app.ui;

import app.ui.gui.ApplicationUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
// Teste
public class Main {
  public static void main(String[] args) {
    try {
      ApplicationUI.launch(ApplicationUI.class, args);

      /*
       * MainMenuUI menu = new MainMenuUI();
       * 
       * menu.run();
       */
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
