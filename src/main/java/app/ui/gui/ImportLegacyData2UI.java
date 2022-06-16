package app.ui.gui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ImportLegacyDataController;
import app.domain.model.Company;
import app.domain.shared.HelpText;
import app.dto.LegacyDataDTO;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;

public class ImportLegacyData2UI extends ChildUI<CoordinatorUI> {
  private ImportLegacyDataController ctrl;
  private EmployeeSession employeeSession;
  private List<LegacyDataDTO> legacyDtoList;

  @FXML
  private Label lblCenterName;

  @FXML
  private Label lblName;

  @FXML
  private Label lblRole;

  @FXML
  private VBox vboxData;

  @FXML
  private ListView<LegacyDataDTO> lstData;

  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);
    Company company = App.getInstance().getCompany();
    this.employeeSession = parentUI.getEmployeeSession();
    this.ctrl = new ImportLegacyDataController(company,
        this.employeeSession.getVaccinationCenter());

    this.lblCenterName.setText(parentUI.getVaccinationCenterName());
  }

  public void setLegacyDtoList(List<LegacyDataDTO> legacyDtoList) {
    this.legacyDtoList = legacyDtoList;
    showData();
  }

  private void showData() {
    this.lstData.getItems().addAll(legacyDtoList);
  }

  @FXML
  void btnImport(ActionEvent event) {
    this.ctrl.save(legacyDtoList);
    displaySuccessAlert();
  }

  @Override
  public void btnBack(ActionEvent event) {
    this.getParentUI().toImportLegacyDataScene1();
  }

  private void displaySuccessAlert() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Success!");
    alert.setHeaderText("The legacy data has been imported successfully.");
    alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
    alert.showAndWait().ifPresent(response -> {
      super.toRoleScene();
    });
  }

  @FXML
  void handleSaveSession(ActionEvent event) {
    if (App.getInstance().saveCurrentCompany())
      Utils.showInformation("Session saved!",
          "Your changes have been saved successfully!");
  }

  @FXML
  void handleRestoreSession(ActionEvent event) {
    App.getInstance().doLogout();
    App.getInstance().restoreCompany();
    getParentUI().mainApp.toMainScene();
  }

  @FXML
  void handleLogout(ActionEvent event) {
    App.getInstance().doLogout();
    getParentUI().mainApp.toMainScene();
  }

  @FXML
  void handleExit(ActionEvent event) {
    Utils.showExitConfirmation();
  }

  @FXML
  void handleDevelopmentTeam(ActionEvent event) {
    App.getInstance().doLogout();
    try {
      DevTeamUI ui = (DevTeamUI) getParentUI().mainApp
          .replaceSceneContent("/fxml/DevTeam.fxml");
      ui.setMainApp(getParentUI().mainApp);
    } catch (Exception ex) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

  @FXML
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Employee Help", HelpText.COORDINATOR);
  }
}
