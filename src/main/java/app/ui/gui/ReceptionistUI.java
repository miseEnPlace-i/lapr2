package app.ui.gui;

import app.ui.console.RegisterSNSUserArrivalUI;
import app.ui.console.RegisterSNSUserUI;
import app.ui.console.ScheduleVaccineReceptionistUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceptionistUI extends EmployeeRoleUI {
  @FXML
  private Label lblCenter;

  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @FXML
  void handleRegisterSNSUser(ActionEvent event) {
    new RegisterSNSUserUI().run();
  }

  @FXML
  void handleScheduleVaccination(ActionEvent event) {
    new ScheduleVaccineReceptionistUI().run();
  }

  @FXML
  void handleRegisterArrival(ActionEvent event) {
    (new RegisterSNSUserArrivalUI(employeeSession.getVaccinationCenter())).run();
  }

  @Override
  public String getUIRoleName() {
    return "RECEPTIONIST";
  }
}
