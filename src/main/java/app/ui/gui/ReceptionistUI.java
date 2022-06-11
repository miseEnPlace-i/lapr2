package app.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceptionistUI extends EmployeeRoleUI {
  @FXML
  private Label lblCenter;
  
  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @Override
  public String getUIRoleName() {
    return "RECEPTIONIST";
  }
}
