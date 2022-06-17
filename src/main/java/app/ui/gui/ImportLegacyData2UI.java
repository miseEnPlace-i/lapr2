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
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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

  @FXML
  private ChoiceBox<String> selParam;

  @FXML
  private ChoiceBox<String> selOrder;

  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);
    Company company = App.getInstance().getCompany();
    this.employeeSession = parentUI.getEmployeeSession();
    this.ctrl = new ImportLegacyDataController(company, this.employeeSession.getVaccinationCenter());

    this.lblCenterName.setText(parentUI.getVaccinationCenterName());

    selParam.getItems().add("Arrival");
    selParam.getItems().add("Departure");
    selParam.valueProperty().set(selParam.getItems().get(0));
    selOrder.getItems().add("Ascending");
    selOrder.getItems().add("Descending");
    selOrder.valueProperty().set(selOrder.getItems().get(0));

    ChangeListener<String> listener = (observable, oldValue, newValue) -> {
      boolean isArrival = selParam.valueProperty().get().equals("Arrival");
      boolean isAsc = selOrder.valueProperty().get().equals("Ascending");

      this.ctrl.sort(legacyDtoList, isArrival, isAsc);
      showData();
    };

    selParam.valueProperty().addListener(listener);
    selOrder.valueProperty().addListener(listener);
  }

  public void setLegacyDtoList(List<LegacyDataDTO> legacyDtoList) {
    this.legacyDtoList = legacyDtoList;
    showData();
  }

  private void showData() {
    this.lstData.getItems().clear();
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
}