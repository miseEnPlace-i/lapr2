package app.ui.gui;

import app.domain.shared.HelpText;
import app.ui.console.ListUsersInWaitingRoomUI;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NurseUI extends EmployeeRoleUI {
  @FXML
  private Label lblCenter;

  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @FXML
  void handleGetUsersInWaitingRoomPress(ActionEvent event) {
    new ListUsersInWaitingRoomUI(employeeSession).run();
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Nurse Help", HelpText.NURSE);
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
