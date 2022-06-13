package app.ui.gui;

import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import app.controller.AnalyseCenterPerformanceController;
import app.controller.App;
import app.domain.model.CenterPerformance;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnalyseCenterPerformanceUI extends ChildUI<CoordinatorUI> {
  private AnalyseCenterPerformanceController ctrl;
  private EmployeeSession employeeSession;

  @FXML
  private Button btnAnalyse;

  @FXML
  private TextField txtInterval;

  @FXML
  private DatePicker dtpDate;

  @Override
  void init(CoordinatorUI parentUI) {
    setParentUI(parentUI);
  }

  public void setEmployeeSession(EmployeeSession session) {
    this.employeeSession = session;
    this.ctrl = new AnalyseCenterPerformanceController(App.getInstance().getCompany(), employeeSession);
  }

  @FXML
  void checkAnalyseButtonEnable() {
    if (dtpDate.getValue() != null && !StringUtils.isEmpty(txtInterval.getText())) btnAnalyse.setDisable(false);
    else btnAnalyse.setDisable(true);
  }

  @FXML
  void handleAnalyse() {
    Calendar day = Calendar.getInstance();
    day.setTime(Date.valueOf(dtpDate.getValue()));

    int interval = Integer.parseInt(txtInterval.getText());

    try {
      CenterPerformance performance = ctrl.analyseCenterPerformance(day, interval);

      if (performance == null) {
        Utils.showError("No performance data found", "No performance data found for the selected date.");
        resetFields();
        return;
      }

      loadDialog(performance);
    } catch (IllegalArgumentException e) {
      Utils.showError("Invalid algorithm", e.getMessage());
    }
  }

  private void resetFields() {
    dtpDate.setValue(null);
    txtInterval.setText("");
  }

  private FlowPane generatePaneWithData(String title, String data) {
    Label lblInputList = new Label(title);
    Label lblList = new Label(data);
    lblList.setAlignment(Pos.CENTER_LEFT);

    ScrollPane container = new ScrollPane(lblList);
    container.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    container.setVbarPolicy(ScrollBarPolicy.NEVER);
    container.setPadding(new Insets(8, 8, 8, 8));
    container.setPrefWidth(560);
    container.setPrefHeight(48);
    container.setMaxWidth(Double.MAX_VALUE);

    FlowPane inputListContainer = new FlowPane(lblInputList, container);
    inputListContainer.setOrientation(Orientation.HORIZONTAL);
    inputListContainer.setHgap(8);
    inputListContainer.setVgap(8);
    inputListContainer.setMaxWidth(Double.MAX_VALUE);
    inputListContainer.setAlignment(Pos.CENTER);
    inputListContainer.setPadding(new Insets(8, 8, 8, 8));

    return inputListContainer;
  }

  private void loadDialog(CenterPerformance performance) {
    final double SCENE_WIDTH = 640.0;
    final double SCENE_HEIGHT = 400.0;

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(getParentUI().mainApp.getStage());

    dialog.setTitle("Analyse Center Results");
    dialog.setWidth(SCENE_WIDTH);
    dialog.setHeight(SCENE_HEIGHT);

    Properties props = PropertiesUtils.getProperties();
    String algorithmUsed = props.getProperty(Constants.PARAMS_PERFORMANCE_ALGORITHM);

    FlowPane inputListContainer = generatePaneWithData("Input List", performance.stringifyDifferencesList());
    FlowPane maxSubListContainer = generatePaneWithData("Max Sum Sublist", performance.stringifyMaxSumSublist());
    FlowPane sumContainer = generatePaneWithData("Max Sum", "" + performance.getMaxSum());
    FlowPane begginingIntervalContainer = generatePaneWithData("Beggining Time of Interval", performance.getStartingInterval() + "h");
    FlowPane endIntervalContainer = generatePaneWithData("End Time of Interval", performance.getEndingInterval() + "h");
    FlowPane algorithmUsedContainer = generatePaneWithData("Algorithm Used", algorithmUsed);
    FlowPane timeElapsedContainer = generatePaneWithData("Time Elapsed", performance.getTimeElapsed() + " ms");

    VBox pane = new VBox(15);

    // Setting the space between the nodes of a VBox pane
    pane.setPadding(new Insets(40, 40, 40, 40));
    pane.setAlignment(Pos.CENTER);
    pane.getChildren().addAll(inputListContainer, maxSubListContainer, sumContainer, begginingIntervalContainer, endIntervalContainer, algorithmUsedContainer,
        timeElapsedContainer);

    ScrollPane container = new ScrollPane(pane);

    container.setHbarPolicy(ScrollBarPolicy.NEVER);

    Scene scene = new Scene(container, SCENE_WIDTH, SCENE_HEIGHT);
    dialog.setResizable(false);
    dialog.setScene(scene);
    dialog.show();
  }
}
