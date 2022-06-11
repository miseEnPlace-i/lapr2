package app.ui.gui;

import app.ui.console.ListUsersInWaitingRoomUI;
import javafx.event.ActionEvent;
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

  @FXML
  void handleGetUsersInWaitingRoomPress(ActionEvent event) {
    new ListUsersInWaitingRoomUI(employeeSession).run();
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
