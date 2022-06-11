package app.ui.gui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ExportCenterStatisticsController;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.exception.NotAuthorizedException;
import app.service.FullyVaccinatedData;
import app.session.EmployeeSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * ExportCenterStatisticsUI
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsUI extends ChildUI<CoordinatorUI> {
  private ExportCenterStatisticsController ctrl;
  private EmployeeSession employeeSession;
  private FindCoordinatorVaccinationCenterController ctrlCenter;
  private Map<Calendar, Integer> dataMap;
  private FullyVaccinatedData fullyVaccinatedData;

  @FXML
  private MenuItem help;

  @FXML
  private MenuItem devTeam;

  @FXML
  private Label lblCenterName;

  @FXML
  private DatePicker initialDate;

  @FXML
  private DatePicker endDate;

  @FXML
  private Button exportStatistics;

  @FXML
  private TextField fileDestination;

  @FXML
  private Button selectDestination;

  @Override
  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);
    this.employeeSession = new EmployeeSession();
    this.ctrlCenter = new FindCoordinatorVaccinationCenterController(App.getInstance().getCompany(), employeeSession);

    this.ctrlCenter.findCoordinatorCenter();

    this.lblCenterName.setText(this.ctrlCenter.getVaccinationCenterName());
    try {
      this.ctrl = new ExportCenterStatisticsController(App.getInstance().getCompany(), employeeSession);
    } catch (NotAuthorizedException e) {
    }
  }

  /**
   * Displays the export information selected by the user
   */
  private void displayExportInformation() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Please confirm the data");
    alert.setHeaderText("Confirm the data below:");
    alert.setContentText(toString());

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation succeeded!");
        success(response);
      } else {
        fileDestination.clear();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation canceled!");
        cancel(response);
      }
    });
  }

  /**
   * Button to export the statistics
   * 
   * @param event
   */
  @FXML
  void export(ActionEvent event) {
    try {
      displayExportInformation();
      fullyVaccinatedData = ctrl.createFullyVaccinatedData(fileDestination.getText(), getStartDate(), getEndDate());
      dataMap = ctrl.generateFullyVaccinatedUsersInterval(fullyVaccinatedData);
      ctrl.createCsvExporter(fileDestination.getText());
      ctrl.saveData(dataMap);
      super.btnBack(event);
    } catch (NullPointerException e) {
      displayErrorAlert(e);
    }
  }

  /**
   * Converts localDate to Calendar
   * 
   * @return the startDate converted in object Calendar
   */
  private Calendar getStartDate() {
    LocalDate localDate = initialDate.getValue();
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  /**
   * Converts localDate to Calendar
   * 
   * @return the endDate converted in object Calendar
   */
  private Calendar getEndDate() {
    LocalDate localDate = endDate.getValue();
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar;
  }

  public String toString() {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Data from the Center: %s ", lblCenterName.getText()));
    sb.append(String.format("\nFile Path Name: %s ", fileDestination.getText()));
    sb.append(String.format("\nExport center statistics from: %s", format.format(getStartDate().getTime())));
    sb.append(String.format("\nTo: %s", format.format(getEndDate().getTime())));

    return sb.toString();
  }

  /**
   * Button to help the user to know how to export statistics
   * 
   * @param event
   */
  @FXML
  void helpUser(ActionEvent event) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Help Exporting Center Statistics");
    alert.setHeaderText("How it works?");
    alert.setContentText(
        "File destination: you can select where to save the file by clicking on the button 'Select File Destination' and\n\nDates: select days from the past and not in the future. You can enter manually or on the calendar.");
    alert.showAndWait();
  }

  /**
   * Button to select the folder to save the file with the statistics
   * 
   * @param event
   */
  @FXML
  void btnSelectDestination(ActionEvent event) {
    DirectoryChooser directoryChooser = new DirectoryChooser();

    directoryChooser.setTitle("Select where to save the file");
    directoryChooser.getInitialDirectory();

    Stage stage = (Stage) getParentUI().getMainApp().getStage();

    File file = directoryChooser.showDialog(stage);

    if (file != null) {
      fileDestination.setText(file.getAbsolutePath());
    }
  }


  private void cancel(ButtonType event) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Cancel");
    alert.setHeaderText("Canceled the operation");
    alert.setContentText("The file will not be exported.");
    alert.showAndWait();
  }

  private void success(ButtonType event) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText("The data was exported successfully.");
    alert.showAndWait().ifPresent(response -> {
      super.toRoleScene();
    });;
  }

  private void displayErrorAlert(Exception e) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Oops!");
    alert.setHeaderText("Found an error!");
    alert.setContentText(String.format("Please try again and check if everything field is correctly filled."));
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    });
  }
}


