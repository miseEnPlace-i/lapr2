package app.ui.gui;

import app.domain.shared.HelpText;
import app.ui.console.ScheduleVaccineUI;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SNSUserUI extends RoleUI {
  void init(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @FXML
  void handleSchedule(ActionEvent event) {
    new ScheduleVaccineUI().run();
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("SNS User Help", HelpText.SNS_USER);
  }

  @Override
  public String getUIRoleName() {
    return "SNS_USER";
  }
}
