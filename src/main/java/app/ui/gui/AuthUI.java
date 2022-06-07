package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.AuthController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthUI implements Initializable {
  private ApplicationUI mainApp;
  private AuthController ctrl;

  @FXML
  private TextField txtEmail;
  @FXML
  private PasswordField txtPwd;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ctrl = new AuthController();
  }

  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @FXML
  void btnExit(ActionEvent event) {
    mainApp.getStage().close();
  }

  @FXML
  void btnLogin(ActionEvent event) {
    // test
    try {

      String email = txtEmail.getText();
      String pwd = txtPwd.getText();

      if (ctrl.doLogin(email, pwd)) {
        BaseScreenUI analyseCenterUI = (BaseScreenUI) this.mainApp.replaceSceneContent("/fxml/BaseScreen.fxml");
        analyseCenterUI.setMainApp(this.mainApp);
      } else {
        // TODO Alert for failed login
      }
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  @FXML
  void onKeyPressed(ActionEvent event) {
    // check enter
  }
}
