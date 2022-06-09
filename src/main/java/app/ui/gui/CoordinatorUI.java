package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.Company;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

/**
 * CoordinatorUI
 */
public class CoordinatorUI extends RoleUI {
  private EmployeeSession employeeSession;
  private FindCoordinatorVaccinationCenterController ctrl;

  @FXML
  private Label lblCenterName;

  @Override
  void init() {
    App app = App.getInstance();
    Company company = app.getCompany();
    this.employeeSession = new EmployeeSession();
    this.ctrl = new FindCoordinatorVaccinationCenterController(company, employeeSession);

    this.ctrl.findCoordinatorCenter();

    if (!this.employeeSession.hasCenter()) {
      // Much better if I could just throw a NotAuthorizedException
      // and catch it on AuthUI but it is not possible.
      // Also mainApp.toMainScene() throws an exception because setMainApp()
      // is not called until after initialization.
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Login Failed");
      alert.setHeaderText("You are not assigned to any vaccination center.");
      alert.setContentText("Please contact your administrator.");
      alert.showAndWait();

      // exception is thrown so it doesn't move forward with this scene
      // aka: maneira rota de fazer isto funcionar
      this.mainApp.toMainScene();
    }

    this.lblCenterName.setText(this.ctrl.getVaccinationCenterName());
  }

  @FXML
  public void toImportLegacyDataScene1() {
    try {
      ImportLegacyData1UI importUI = (ImportLegacyData1UI) this.mainApp.replaceSceneContent("/fxml/ImportLegacyData_1.fxml");
      importUI.setParentUI(this);
      importUI.setMainApp(this.mainApp);

      // importUI.getNameTxtField.requestFocus() to make sure the text field you need focused is, in fact, focused
    } catch (Exception ex) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  public void toExportCenterStatistics() {
    try {
      ExportCenterStatisticsUI importUI = (ExportCenterStatisticsUI) this.mainApp.replaceSceneContent("/fxml/ExportStatisticsInitialPage.fxml");
      importUI.setParentUI(this);
      importUI.setMainApp(this.mainApp);

      // importUI.getNameTxtField.requestFocus() to make sure the text field you need focused is, in fact, focused
    } catch (Exception ex) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @Override
  public String getUIRoleName() {
    return "COORDINATOR";
  }
}
