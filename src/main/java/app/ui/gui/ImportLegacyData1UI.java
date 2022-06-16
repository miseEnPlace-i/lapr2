package app.ui.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ImportLegacyDataController;
import app.domain.model.Company;
import app.dto.LegacyDataDTO;
import app.exception.NotFoundException;
import app.session.EmployeeSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class ImportLegacyData1UI extends ChildUI<CoordinatorUI> {
  private ImportLegacyDataController ctrl;
  private EmployeeSession employeeSession;
  private File selectedFile;

  @FXML
  private Label lblCenterName;

  @FXML
  private Label lblSelectedFile;

  @FXML
  private Button btnOpen;

  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);
    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new ImportLegacyDataController(company, this.employeeSession.getVaccinationCenter());

    this.lblCenterName.setText(this.getParentUI().getVaccinationCenterName());
  }

  @FXML
  void btnSelect(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

    fileChooser.setTitle("Select file to import");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));

    selectedFile = fileChooser.showOpenDialog(getParentUI().getMainApp().getStage());

    if (selectedFile != null) {
      this.lblSelectedFile.setText(selectedFile.getName());
      btnOpen.setDisable(false);
    }
  }

  @FXML
  void btnOpen(ActionEvent event) {
    try {
      List<String[]> fileData = this.ctrl.read(selectedFile.getAbsolutePath());
      List<LegacyDataDTO> legacyDtoList = this.ctrl.convert(fileData);
      this.ctrl.validate(legacyDtoList);
      this.ctrl.sort(legacyDtoList, true, false);

      ImportLegacyData2UI iUI = this.getParentUI().toImportLegacyDataScene2();
      iUI.setLegacyDtoList(legacyDtoList);
    } catch (FileNotFoundException e) {
      // the user may have deleted the file after selecting it
      displayFileNotFoundErrorAlert(e);
    } catch (ParseException e) {
      displayParseErrorAlert(e);
    } catch (NotFoundException e) {
      // there are SNS Users that do not exist in the system
      displayNotFoundErrorAlert(e);
    } catch (Exception e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private void displayFileNotFoundErrorAlert(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);

    alert.setTitle("Oops!");
    alert.setHeaderText("Something weird just happened.");
    alert.setContentText(String.format("Are you sure you selected a valid existing file?"));
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    });
  }

  private void displayParseErrorAlert(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Oops!");
    alert.setHeaderText("There was an error converting the data.");
    alert.setContentText(String.format("Are you sure you selected the correct file?"));
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    });
  }

  private void displayNotFoundErrorAlert(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Oops!");
    alert.setHeaderText("There's missing SNS Users in the system.");
    alert.setContentText(String.format("Please ask you administrator to load the missing SNS Users."));
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    });
  }
}
