package app.ui.gui;

import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.Company;
import app.session.EmployeeSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
      // create an alert
      System.out.println("You are not assigned to a vaccination center. Please contact your administrator.");
      
      this.mainApp.toMainScene();
    } else {
      this.lblCenterName.setText(this.ctrl.getVaccinationCenterName());
    }
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

  @Override
  public String getUIRoleName() {
    return "COORDINATOR";
  }
}
