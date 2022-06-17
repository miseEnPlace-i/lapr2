package app.ui.gui;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import app.controller.AnalyseCenterPerformanceController;
import app.controller.App;
import app.domain.model.CenterPerformance;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import app.utils.Time;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnalyseCenterPerformanceUI extends ChildUI<CoordinatorUI> {
  private AnalyseCenterPerformanceController ctrl;
  private EmployeeSession employeeSession;

  private int interval;

  @FXML
  private Button btnAnalyse;

  @FXML
  private TextField txtInterval;

  @FXML
  private DatePicker dtpDate;

  @FXML
  private Label lblCenterName;

  @Override
  void init(CoordinatorUI parentUI) {
    this.setParentUI(parentUI);

    this.lblCenterName.setText(this.getParentUI().getVaccinationCenterName());
  }

  public void setEmployeeSession(EmployeeSession session) {
    this.employeeSession = session;
    this.ctrl = new AnalyseCenterPerformanceController(
        App.getInstance().getCompany(), employeeSession);
  }

  @FXML
  void checkAnalyseButtonEnable() {
    if (isValidInput()) btnAnalyse.setDisable(false);
    else btnAnalyse.setDisable(true);
  }

  private boolean isValidInput() {
    if (dtpDate.getValue() != null
        && !StringUtils.isEmpty(txtInterval.getText()))
      return true;
    else
      return false;
  }

  @FXML
  void onKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) analyse();
  }

  @FXML
  void handleAnalyse() {
    if (isValidInput()) analyse();
    else Utils.showError("Non valid input!", "Please fill all inputs.");
  }

  private void analyse() {
    Calendar day = Calendar.getInstance();
    day.setTime(Date.valueOf(dtpDate.getValue()));

    interval = Integer.parseInt(txtInterval.getText());

    try {
      CenterPerformance performance =
          ctrl.analyseCenterPerformance(day, interval);

      if (performance == null) {
        Utils.showError("No performance data found",
            "No performance data found for the selected date.");
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

  private void loadDialog(CenterPerformance performance) {
    final double SCENE_WIDTH = 720.0;
    final double SCENE_HEIGHT = 480.0;

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(getParentUI().mainApp.getStage());

    dialog.setTitle("Analyse Center Results");
    dialog.setWidth(SCENE_WIDTH);
    dialog.setHeight(SCENE_HEIGHT);

    Properties props = PropertiesUtils.getProperties();
    String algorithmUsed =
        props.getProperty(Constants.PARAMS_PERFORMANCE_ALGORITHM);

    FlowPane inputListContainer = generatePaneWithData("Input List",
        performance.stringifyDifferencesList(), SCENE_WIDTH);
    FlowPane maxSubListContainer = generatePaneWithData("Max Sum Sublist",
        performance.stringifyMaxSumSublist(), SCENE_WIDTH);
    FlowPane sumContainer = generatePaneWithData("Max Sum",
        "" + performance.getMaxSum(), SCENE_WIDTH);
    FlowPane beginningIntervalContainer =
        generatePaneWithData("Beginning Time of Interval",
            performance.getStartingInterval() + "h", SCENE_WIDTH);
    FlowPane endIntervalContainer = generatePaneWithData("End Time of Interval",
        performance.getEndingInterval() + "h", SCENE_WIDTH);
    FlowPane algorithmUsedContainer =
        generatePaneWithData("Algorithm Used", algorithmUsed, SCENE_WIDTH);
    FlowPane timeElapsedContainer = generatePaneWithData("Time Elapsed",
        performance.getTimeElapsed() + " ms", SCENE_WIDTH);

    StackedBarChart<String, Number> chart = generateChart(performance);
    chart.setPrefWidth(SCENE_WIDTH - 80);


    VBox pane = new VBox(16);

    pane.setAlignment(Pos.CENTER);
    pane.getChildren().addAll(inputListContainer, maxSubListContainer,
        sumContainer, beginningIntervalContainer, endIntervalContainer,
        algorithmUsedContainer, timeElapsedContainer, chart);

    ScrollPane container = new ScrollPane(pane);
    container.setPadding(new Insets(16, 0, 32, 0));

    container.setHbarPolicy(ScrollBarPolicy.NEVER);

    Scene scene = new Scene(container, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add(getClass()
        .getResource("/styles/AnalyseCenterResults.css").toExternalForm());
    dialog.setResizable(false);
    dialog.setScene(scene);
    dialog.show();
  }

  private StackedBarChart<String, Number> generateChart(
      CenterPerformance performance) {
    CategoryAxis xAxis = new CategoryAxis();

    NumberAxis yAxis = new NumberAxis();

    XYChart.Series<String, Number> series = new XYChart.Series<>();
    XYChart.Series<String, Number> worstSeries = new XYChart.Series<>();

    List<Integer> differences = performance.getDifferencesList();
    Time startingInterval = performance.getStartingInterval();
    Time endingInterval = performance.getEndingInterval();
    Time tempInterval =
        employeeSession.getVaccinationCenter().getOpeningHours().clone();

    for (Integer value : differences) {
      if (tempInterval.isBetweenExcludeRight(startingInterval, endingInterval))
        worstSeries.getData().add(
            new XYChart.Data<String, Number>(tempInterval.toString(), value));
      else
        series.getData().add(
            new XYChart.Data<String, Number>(tempInterval.toString(), value));

      tempInterval.addMinutes(interval);
    }

    StackedBarChart<String, Number> chart =
        new StackedBarChart<>(xAxis, yAxis) {
          @Override
          protected void dataItemAdded(Series<String, Number> series,
              int itemIndex, Data<String, Number> item) {
            super.dataItemAdded(series, itemIndex, item);

            Number val =
                (Number) (item.getYValue() instanceof Number ? item.getYValue()
                    : item.getXValue());
            if (val.doubleValue() < 0)
              item.getNode().getStyleClass().add("negative");
          }

          /**
           * Override the method that breaks the graph, patch so it doesn't override styles.
           */
          @Override
          protected void seriesChanged(
              ListChangeListener.Change<? extends Series> c) {
            for (int i = 0; i < getData().size(); i++) {
              List<Data<String, Number>> items = getData().get(i).getData();
              for (int j = 0; j < items.size(); j++) {
                Node bar = items.get(j).getNode();
                bar.getStyleClass()
                    .removeIf(s -> s.matches("chart-bar|(series|data)\\d+"));
                bar.getStyleClass().addAll("chart-bar", "series" + i,
                    "data" + j);
              }
            }
          }
        };

    chart.setTranslateX(32);

    chart.getData().addAll(worstSeries, series);

    series.setName("Better Performance");
    worstSeries.setName("Worst Performance");

    chart.setTitle("Center Performance");

    return chart;
  }

  private FlowPane generatePaneWithData(String title, String data,
      double width) {
    final double padding = 80;

    Label lblInputList = new Label(title);
    lblInputList.setTranslateX(padding + 2);

    TextArea container = new TextArea(data);
    container.setFont(new Font("System", 17));
    container.setEditable(false);
    container.setWrapText(true);
    container.setPrefWidth(width - padding * 2);
    container.setTranslateX(padding);
    container.setMaxWidth(Double.MAX_VALUE);

    FlowPane inputListContainer = new FlowPane(lblInputList, container);
    inputListContainer.setOrientation(Orientation.HORIZONTAL);
    inputListContainer.setHgap(8);
    inputListContainer.setVgap(8);
    inputListContainer.setAlignment(Pos.CENTER_LEFT);
    inputListContainer.setMaxWidth(Double.MAX_VALUE);
    inputListContainer.setPadding(new Insets(8, 8, 8, 8));

    return inputListContainer;
  }
}
