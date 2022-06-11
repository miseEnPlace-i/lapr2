package app.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NurseUI extends RoleUI {
  // select employee ctrl

  @FXML
  private Label lblCenter;

  @Override
  void init() {
    if (employeeSession.getVaccinationCenter() != null) lblCenter.setText(employeeSession.getVaccinationCenter().getName());
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
