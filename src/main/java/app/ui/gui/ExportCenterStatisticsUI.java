package app.ui.gui;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ExportCenterStatisticsController;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.shared.HelpText;
import app.exception.NotAuthorizedException;
import app.service.FileUtils;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
  private String fileName;

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
  private TextField txtFileName;

  @Override
  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);
    this.employeeSession = parentUI.getEmployeeSession();
    this.ctrlCenter = new FindCoordinatorVaccinationCenterController(App.getInstance().getCompany(), employeeSession);

    this.ctrlCenter.findCoordinatorCenter();

    this.lblCenterName.setText(this.ctrlCenter.getVaccinationCenterName());

    try {
      this.ctrl = new ExportCenterStatisticsController(App.getInstance().getCompany(), employeeSession);
    } catch (NotAuthorizedException e) {
      Logger.getLogger(ExportCenterStatisticsUI.class.getName()).log(Level.SEVERE, null, e);
    }
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
      boolean flag = Utils.showConfirmation("Please confirm the following data", toString());
      if (flag && validateDates() && validateFilePath()) {
        fileName = FileUtils.sanitizeFileName(txtFileName.getText());

        ctrl.createFullyVaccinatedData(fileName, getStartDate(), getEndDate());
        ctrl.generateFullyVaccinatedUsersData();

        if (ctrl.saveData(fileName)) {
          checkData();
          success();
        } else {
          displayErrorAlert();
        }

      } else if (!validateDates() || !validateFilePath()) {
        displayErrorAlert();
      } else if (!flag) {
        Utils.cancel();
      }
    } catch (NullPointerException e) {
      displayErrorAlertOperation();
    }
  }

  /**
   * Generates a LineChart
   * 
   * @param data the linkedHashMap with all the data
   * @return the generated chart
   */
  private LineChart<String, Number> generateChart(LinkedHashMap<Calendar, Integer> data) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    yAxis.setMinorTickVisible(false);
    yAxis.setTickUnit(1);

    xAxis.setLabel("Days");
    yAxis.setLabel("Number of Fully Vaccinated Users");

    XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

    for (Map.Entry<Calendar, Integer> entry : data.entrySet()) {
      System.out.println("Key: " + format.format(entry.getKey().getTime()) + " Value: " + entry.getValue());
      series.getData().add(new XYChart.Data<String, Number>(format.format(entry.getKey().getTime()), entry.getValue()));
    }

    LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
    lineChart.setTitle("Center Statistics");
    series.setName("Fully Vaccinated Users");

    lineChart.getData().addAll(series);

    return lineChart;
  }

  /**
   * Creates a new stage to show the user the data
   */
  private void checkData() {
    try {
      final double SCENE_WIDTH = 640.0;
      final double SCENE_HEIGHT = 600.0;

      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(getParentUI().mainApp.getStage());

      dialog.setTitle("Center Export Statistics");
      dialog.setWidth(SCENE_WIDTH);
      dialog.setHeight(SCENE_HEIGHT);

      VBox vbox = new VBox(30);

      LineChart<String, Number> chart = generateChart(ctrl.getData());

      Button close = new Button("Close");
      // Setting the space between the nodes of a VBox pane
      vbox.setPadding(new Insets(40, 40, 40, 40));
      vbox.setAlignment(Pos.CENTER);
      vbox.getChildren().addAll(chart);
      vbox.setMinHeight(SCENE_HEIGHT);
      vbox.setMinWidth(SCENE_WIDTH);
      vbox.setMaxWidth(Double.MAX_VALUE);

      ScrollPane container = new ScrollPane(vbox);
      container.setHbarPolicy(ScrollBarPolicy.NEVER);
      container.setMaxWidth(Double.MAX_VALUE);
      container.setFitToWidth(true);
      container.setMinWidth(SCENE_WIDTH);

      Scene scene = new Scene(container, SCENE_WIDTH, SCENE_HEIGHT);
      dialog.setScene(scene);
      dialog.show();
      dialog.setMinWidth(SCENE_WIDTH);
      dialog.setMinHeight(SCENE_HEIGHT);
      dialog.setMaxHeight(SCENE_HEIGHT);

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
    sb.append(String.format("\nFile Name: %s ", FileUtils.sanitizeFileName(txtFileName.getText())));
    sb.append(String.format("\nCenter statistics from: %s", format.format(getStartDate().getTime())));
    sb.append(String.format("\nTo: %s", format.format(getEndDate().getTime())));

    return sb.toString();
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
    alert.setContentText(String.format("Please try again. Check if every field is correctly filled."));
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
    if (txtFileName.getText().isEmpty() || txtFileName == null) return false;
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
    txtFileName.setText("");
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Coordinator Help", HelpText.CENTER_STATISTICS);
  }
}


