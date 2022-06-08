package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainUI implements Initializable {
  private ApplicationUI mainApp;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @FXML
  void btnExit(ActionEvent event) {
    mainApp.getStage().close();
  }

  @FXML
  void btnDevTeam(ActionEvent event) {
    try {
      DevTeamUI devTeamUI = (DevTeamUI) this.mainApp.replaceSceneContent("/fxml/DevTeam.fxml");
      devTeamUI.setMainApp(this.mainApp);
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  void btnLogin(ActionEvent event) {
    try {
      AuthUI analyseCenterUI = (AuthUI) this.mainApp.replaceSceneContent("/fxml/Auth.fxml");
      analyseCenterUI.setMainApp(this.mainApp);
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }
}
