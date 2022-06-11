package app.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NurseUI extends EmployeeRoleUI {
  @FXML
  private Label lblCenter;

  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
