package app.ui.gui;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import javafx.fxml.Initializable;

public class MenuItem<T extends Initializable> implements IGui {
  private ApplicationUI mainApp;

  private String description;
  private T init;
  private String fxml;

  public MenuItem(String description, T init, String fxml) {
    if (StringUtils.isBlank(description)) throw new IllegalArgumentException("MenuItem description cannot be null or empty.");
    if (Objects.isNull(init)) throw new IllegalArgumentException("MenuItem does not support a null UI.");
    if (StringUtils.isBlank(fxml)) throw new IllegalArgumentException("MenuItem description cannot be null or empty.");

    this.description = description;
    this.init = init;
    this.fxml = fxml;
  }

  public void run() {
    try {
      IGui ui = (IGui) this.mainApp.replaceSceneContent(fxml);
      ui.setMainApp(mainApp);
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @Override
  public String toString() {
    return this.description;
  }
}
