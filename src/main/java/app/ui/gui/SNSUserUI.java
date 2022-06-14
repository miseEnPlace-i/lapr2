package app.ui.gui;

import app.ui.console.ScheduleVaccineUI;
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
  public String getUIRoleName() {
    return "SNS_USER";
  }
}
