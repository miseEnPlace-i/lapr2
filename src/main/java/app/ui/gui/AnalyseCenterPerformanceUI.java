package app.ui.gui;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import org.apache.commons.lang3.StringUtils;
import app.controller.AnalyseCenterPerformanceController;
import app.controller.App;
import app.domain.model.CenterPerformance;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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

    CenterPerformance performance = ctrl.analyseCenterPerformance(day, interval);

    if (performance == null) {
      Utils.showError("No performance data found", "No performance data found for the selected date.");
      resetFields();
      return;
    }

    loadDialog(performance);
  }

  private void resetFields() {
    dtpDate.setValue(null);
    txtInterval.setText("");
  }

  private void loadDialog(CenterPerformance performance) {
    final double MINIMUM_WINDOW_WIDTH = 400.0;
    final double MINIMUM_WINDOW_HEIGHT = 320.0;
    final double SCENE_WIDTH = 560.0;
    final double SCENE_HEIGHT = 400.0;

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(getParentUI().mainApp.getStage());

    dialog.setTitle("Analyse Center Results");
    dialog.setMinWidth(MINIMUM_WINDOW_WIDTH);
    dialog.setMinHeight(MINIMUM_WINDOW_HEIGHT);

    Label lblInputList = new Label("Input List");
    lblInputList.setMinHeight(24);
    Label lblList = new Label(performance.stringifyDifferencesList());

    ScrollPane container = new ScrollPane(lblList);
    container.setPrefWidth(SCENE_WIDTH);
    container.setMaxWidth(Double.MAX_VALUE);
    container.setHbarPolicy(ScrollBarPolicy.ALWAYS);
    container.setVbarPolicy(ScrollBarPolicy.NEVER);
    container.setMinHeight(24);

    FlowPane inputListContainer = new FlowPane(lblInputList, container);
    inputListContainer.setOrientation(Orientation.HORIZONTAL);
    inputListContainer.setHgap(8);
    inputListContainer.setPrefWidth(SCENE_WIDTH);
    inputListContainer.setMaxWidth(Double.MAX_VALUE);
    inputListContainer.setRowValignment(VPos.CENTER);

    HBox pane = new HBox(15);

    // Setting the space between the nodes of a HBox pane
    pane.setPadding(new Insets(40, 40, 40, 40));
    pane.setAlignment(Pos.CENTER);
    pane.getChildren().addAll(inputListContainer);

    Scene scene = new Scene(new Group(pane), SCENE_WIDTH, SCENE_HEIGHT);
    dialog.setScene(scene);
    dialog.show();
  }
}
