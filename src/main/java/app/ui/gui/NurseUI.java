package app.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NurseUI extends RoleUI {
  @FXML
  private Label lblCenter;

  @Override
  void init(ApplicationUI mainApp) {
    super.setMainApp(mainApp);
    // if (employeeSession.getVaccinationCenter() != null) lblCenter.setText(employeeSession.getVaccinationCenter().getName());
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
