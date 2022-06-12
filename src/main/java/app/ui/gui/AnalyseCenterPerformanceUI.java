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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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

  @FXML
  private Label lblInputList;

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

    loadDialog();

    lblInputList.setText(performance.getDifferencesList().toString());
  }

  private void resetFields() {
    dtpDate.setValue(null);
    txtInterval.setText("");
  }

  private void loadDialog() {
    String fxml = "/fxml/CenterPerformanceResults.fxml";
    final double MINIMUM_WINDOW_WIDTH = 320.0;
    final double MINIMUM_WINDOW_HEIGHT = 240.0;
    final double SCENE_WIDTH = 400.0;
    final double SCENE_HEIGHT = 320.0;

    final Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(getParentUI().mainApp.getStage());

    dialog.setTitle("Analyse Center Results");
    dialog.setMinWidth(MINIMUM_WINDOW_WIDTH);
    dialog.setMinHeight(MINIMUM_WINDOW_HEIGHT);

    FXMLLoader loader = new FXMLLoader();
    InputStream in = ApplicationUI.class.getResourceAsStream(fxml);
    loader.setBuilderFactory(new JavaFXBuilderFactory());
    loader.setLocation(ApplicationUI.class.getResource(fxml));
    Pane page;

    try {
      try {
        page = (Pane) loader.load(in);
      }
      finally {
        in.close();
      }
    } catch (IOException e) {
      throw new RuntimeException("Unable to load FXML: " + fxml);
    }

    Scene scene = new Scene(page, SCENE_WIDTH, SCENE_HEIGHT);

    dialog.setScene(scene);
    dialog.show();
  }
}
