package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ReceptionistUI implements Initializable, IGui {
  private ApplicationUI mainApp;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  @Override
  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }
}
