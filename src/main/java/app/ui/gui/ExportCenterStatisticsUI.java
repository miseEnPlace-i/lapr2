package app.ui.gui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ExportCenterStatisticsController;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.CsvExporter;
import app.exception.NotAuthorizedException;
import app.service.FullyVaccinatedData;
import app.session.EmployeeSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Group;

/**
 * ExportCenterStatisticsUI
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsUI extends ChildUI<CoordinatorUI> {
  private ExportCenterStatisticsController ctrl;
  private EmployeeSession employeeSession;
  private FindCoordinatorVaccinationCenterController ctrlCenter;
  private LinkedHashMap<Calendar, Integer> dataMap = new LinkedHashMap<>();
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

  @FXML
  private ListView<?> statsViewLV;

  @FXML
  private Button continueBtn;

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
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation running!");
      } else {
        fileDestination.clear();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation canceled!");
        cancel();
      }
    });
  }

  /**
   * Button to export the statistics
   * 
   * @param event
   * @throws Exception
   */
  @FXML
  void export(ActionEvent event) throws Exception {
    try {
      displayExportInformation();
      if (validateDates() && validateFilePath()) {
        fullyVaccinatedData = ctrl.createFullyVaccinatedData(fileDestination.getText(), getStartDate(), getEndDate());
        // dataMap = ctrl.generateFullyVaccinatedUsersInterval(fullyVaccinatedData);

        // FOR TESTING
        dataMap.put(getStartDate(), 100);
        dataMap.put(getEndDate(), 200);
        checkData(dataMap);
        ctrl.createCsvExporter(fileDestination.getText());
        if (!ctrl.saveData(dataMap)) {
          displayErrorAlert();
        }
      } else if (!validateDates() || !validateFilePath()) {
        displayErrorAlert();
      } else {
        displayErrorAlertOperation();
      }
    } catch (NullPointerException e) {
      displayErrorAlertOperation();
    }
  }

  private FlowPane generatePaneWithData(String title, String data) {
    Label titleLbl = new Label(title);
    titleLbl.setMinHeight(24);
    Label statistics = new Label(data);

    ScrollPane container = new ScrollPane(statistics);
    container.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    container.setPadding(new Insets(8, 8, 8, 8));
    container.setPrefWidth(525);
    container.setMaxWidth(Double.MAX_VALUE);

    FlowPane inputListContainer = new FlowPane(titleLbl, container);
    inputListContainer.setOrientation(Orientation.HORIZONTAL);
    inputListContainer.setHgap(8);
    inputListContainer.setVgap(8);
    inputListContainer.setMaxWidth(Double.MAX_VALUE);
    inputListContainer.setAlignment(Pos.CENTER);
    inputListContainer.setPadding(new Insets(8, 8, 8, 8));

    return inputListContainer;
  }

  private void checkData(Map<Calendar, Integer> data) {
    try {
      final double SCENE_WIDTH = 640.0;
      final double SCENE_HEIGHT = 400.0;

      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(getParentUI().mainApp.getStage());

      dialog.setTitle(lblCenterName.getText());
      dialog.setWidth(SCENE_WIDTH);
      dialog.setHeight(SCENE_HEIGHT);

      FlowPane inputListContainer = generatePaneWithData("Data from: " + lblCenterName.getText(), ctrl.dataToString(data));

      VBox pane = new VBox(30);

      Button close = new Button("Close");
      // Setting the space between the nodes of a VBox pane
      pane.setPadding(new Insets(40, 40, 40, 40));
      pane.setAlignment(Pos.CENTER);
      pane.getChildren().addAll(inputListContainer, close);


      ScrollPane container = new ScrollPane(pane);
      container.setHbarPolicy(ScrollBarPolicy.NEVER);

      Scene scene = new Scene(container, SCENE_WIDTH, SCENE_HEIGHT);
      dialog.setResizable(false);
      dialog.setScene(scene);
      dialog.show();

      close.setOnAction(response -> {
        dialog.close();
        success();
      });

    } catch (Exception e) {
      Alert alert = new Alert(AlertType.WARNING);
      alert.setTitle("Warning");
      alert.setHeaderText("Error found.");
      alert.setContentText("Can't load the new window.");
      clearFields();
      alert.showAndWait();
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
        "File destination: you can select where to save the file by clicking on the button 'Select File Destination'.\n\nDates: select days from the past and not in the future. You select the pretended interval on the calendars.");
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

    Stage stage = (Stage) getParentUI().getMainApp().getStage();

    File file = directoryChooser.showDialog(stage);

    if (file != null) {
      fileDestination.setText(file.getAbsolutePath());
    }
  }

  /**
   * Alert when user cancels the operation
   */
  private void cancel() {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Cancel");
    alert.setHeaderText("Canceled the operation");
    alert.setContentText("The file will not be exported.");
    clearFields();
    alert.showAndWait();
  }

  /**
   * Alert when the operation was a success
   */
  private void success() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Success!");
    alert.setHeaderText("The data was exported successfully.");
    alert.showAndWait().ifPresent(response -> {
      super.toRoleScene();
    });;
  }

  /**
   * Alert when was found a error when trying to export due to invalid fields
   */
  private void displayErrorAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Oops!");
    alert.setHeaderText("Found an error!");
    alert.setContentText(
        String.format("Please try again. Check if every field is correctly filled. If having trouble, check on the menu bar 'File' the option 'Help'."));
    clearFields();
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, "Operation failed.");
    });
  }

  /**
   * 
   * @return "true" if valid dates, "false" otherwise
   */
  private boolean validateDates() {

    if (initialDate == null || endDate == null) {
      return false;
    }
    if (getEndDate().before(getStartDate())) {
      return false;
    }
    if (getEndDate().after(Calendar.getInstance())) {
      return false;
    }
    return true;
  }

  /**
   * Validates the file path
   * 
   * @return "true" if valid, "false" otherwise
   */
  private boolean validateFilePath() {
    if (fileDestination.getText().isEmpty() || fileDestination == null) return false;
    return true;
  }

  /**
   * Alert when trying to do the operation and there is no data or all fields blank.
   */
  private void displayErrorAlertOperation() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Oops!");
    alert.setHeaderText("Found an error!");
    alert.setContentText(
        "When trying to execute the operation it failed. Please check if it registered in the system the necessary data or you filled correctly the fields.");
    clearFields();
    alert.showAndWait().ifPresent(response -> {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, "Operation failed.");
    });
  }

  private void clearFields() {
    initialDate.setValue(null);
    endDate.setValue(null);
    fileDestination.setText("");
  }
}


