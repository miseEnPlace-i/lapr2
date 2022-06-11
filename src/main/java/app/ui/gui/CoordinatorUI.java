package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.Company;
import app.session.EmployeeSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

public class CoordinatorUI extends RoleUI {
  private EmployeeSession employeeSession;
  private FindCoordinatorVaccinationCenterController ctrl;

  @FXML
  private Label lblCenterName;

  @Override
  public void init(ApplicationUI mainApp) {
    super.setMainApp(mainApp);
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

      // Error is needed to bypass Initializable interface not throwing an exception
      throw new Error();
    }

    this.lblCenterName.setText(this.ctrl.getVaccinationCenterName());
  }
  
  public EmployeeSession getEmployeeSession() {
    return this.employeeSession;
  }

  @FXML
  public void toImportLegacyDataScene1() {
    try {
      ImportLegacyData1UI importUI = (ImportLegacyData1UI) this.mainApp.replaceSceneContent("/fxml/ImportLegacyData_1.fxml");
      importUI.init(this);

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
