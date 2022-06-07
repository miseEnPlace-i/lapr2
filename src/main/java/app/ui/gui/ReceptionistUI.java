package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class ReceptionistUI implements Initializable {
  private ApplicationUI mainApp;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }
}
