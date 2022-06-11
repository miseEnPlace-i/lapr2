package app.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceptionistUI extends RoleUI {
  private ApplicationUI mainApp;

  @FXML
  private Label lblCenter;
  
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
    if (employeeSession.getVaccinationCenter() != null) lblCenter.setText(employeeSession.getVaccinationCenter().getName());
  }

  @Override
  public String getUIRoleName() {
    return "RECEPTIONIST";
  }
}
