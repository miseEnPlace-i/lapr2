package app.ui.gui;

import java.util.logging.Logger;
import java.util.logging.Level;
import app.controller.App;
import app.session.EmployeeSession;
import app.ui.console.ListUsersInWaitingRoomUI;
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
  void registerVaccineAdministration(ActionEvent event) {
    try {
      VaccineAdministrationUI vaccineAdministrationUI = (VaccineAdministrationUI) this.mainApp.replaceSceneContent("/fxml/RegVacAdminSelectUser.fxml");

      vaccineAdministrationUI.init(this);
    } catch (Exception e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }

  }

  @FXML
  void handleGetUsersInWaitingRoomPress(ActionEvent event) {
    new ListUsersInWaitingRoomUI(employeeSession).run();
  }

  public EmployeeSession getEmployeeSession() {
    return employeeSession;
  }

  public String getVaccinationCenterName() {
    return employeeSession.getVaccinationCenter().getName();
  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
