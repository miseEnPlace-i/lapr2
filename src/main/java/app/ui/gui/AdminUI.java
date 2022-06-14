package app.ui.gui;

import app.domain.shared.HelpText;
import app.ui.console.ListEmployeesByRoleUI;
import app.ui.console.RegisterEmployeeUI;
import app.ui.console.RegisterNewVaccineTypeUI;
import app.ui.console.RegisterVaccinationCenterUI;
import app.ui.console.RegisterVaccineUI;
import app.ui.console.UploadUsersFromFileUI;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminUI extends RoleUI {
  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @FXML
  void handleRegisterEmployee(ActionEvent event) {
    new RegisterEmployeeUI().run();
  }

  @FXML
  void handleListEmployeesByRole(ActionEvent event) {
    new ListEmployeesByRoleUI().run();
  }

  @FXML
  void handleRegisterCenter(ActionEvent event) {
    new RegisterVaccinationCenterUI().run();
  }

  @FXML
  void handleRegisterVaccineType(ActionEvent event) {
    new RegisterNewVaccineTypeUI().run();
  }

  @FXML
  void handleRegisterVaccine(ActionEvent event) {
    new RegisterVaccineUI().run();
  }

  @FXML
  void handleImportSNSUsers(ActionEvent event) {
    new UploadUsersFromFileUI().run();
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Admin Help", HelpText.ADMINISTRATOR);
  }

  @Override
  public String getUIRoleName() {
    return "ADMINISTRATOR";
  }
}
