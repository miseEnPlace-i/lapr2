package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.AuthController;
import app.domain.shared.Constants;
import app.domain.shared.MenuFXMLPath;
import app.exception.NotAuthorizedException;
import app.service.FormatVerifier;
import app.ui.gui.utils.Utils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

public class AuthUI implements Initializable, IGui {
  private ApplicationUI mainApp;
  private AuthController ctrl;
  private int maxAttempts = Constants.MAX_OF_PASSWORD_TRIES;

  @FXML
  private TextField txtEmail;
  @FXML
  private PasswordField txtPwd;

  @FXML
  private Button btnLogin;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      ctrl = new AuthController();
    } catch (Exception e) {
      Logger.getLogger(AuthUI.class.getName()).log(Level.SEVERE, null, e);

      Utils.showError("Error in application initialization",
          "Error while initializing the application,\nplease contact an administrator to solve\nthe problem.");

      App.getInstance().restoreCompany();
      Platform.exit();
      System.exit(0);
    }
  }

  public void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
  }

  @Override
  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @FXML
  void btnBack(ActionEvent event) {
    this.mainApp.toMainScene();
  }

  private void exitApplication() {
    mainApp.getStage().close();
  }

  @FXML
  void btnLogin(ActionEvent event) {
    login();
  }

  private void displayInvalidCredentialsAlert() {
    Utils.showError("Please insert your email and password again.", String.format("You have %d attempts left.", maxAttempts));

    resetTextFields();
  }

  @FXML
  void checkEnableLoginButton() {
    boolean isEmailValid = FormatVerifier.validateEmail(txtEmail.getText());

    if (isEmailValid && !txtPwd.getText().isEmpty()) btnLogin.setDisable(false);
    else btnLogin.setDisable(true);
  }

  private void displayMaxAttemptsReachedAlert() {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Login Failed");
    alert.setHeaderText("Maximum number of attempts reached.");
    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) Logger.getLogger(getClass().getName()).log(Level.INFO, "Login failed");
    });
  }

  private void displayNotAuthorizedAlert(NotAuthorizedException e) {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Login Failed");
    alert.setHeaderText(e.getMessage());
    alert.setContentText("Please contact your administrator.");
    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) Logger.getLogger(getClass().getName()).log(Level.INFO, e.getMessage());
    });
  }

  @FXML
  void onKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) login();
  }

  private void login() {
    String email = txtEmail.getText();
    String pwd = txtPwd.getText();

    if (!ctrl.doLogin(email, pwd)) {
      if (maxAttempts == 0) {
        displayMaxAttemptsReachedAlert();
        exitApplication();
        return;
      }

      displayInvalidCredentialsAlert();
      maxAttempts--;
      return;
    }

    UserRoleDTO role = ctrl.getUserRoles().get(0);
    String menuFXML = getMenuWithRoleFXML(role);

    if (menuFXML == null) {
      Logger.getLogger(AuthUI.class.getName()).log(Level.SEVERE, "No menu found for role: " + role.getDescription());
      return;
    }

    try {
      RoleUI gui = (RoleUI) mainApp.replaceSceneContent(menuFXML);
      gui.init(mainApp);
      resetTextFields();
    } catch (NotAuthorizedException e) {
      // go back to auth ui
      try {
        ((AuthUI) this.mainApp.replaceSceneContent("/fxml/Auth.fxml")).setMainApp(this.mainApp);
      } catch (Exception e1) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, null, e);
      }

      // displays the alert
      displayNotAuthorizedAlert(e);
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private void resetTextFields() {
    txtEmail.setText("");
    txtPwd.setText("");
    txtEmail.requestFocus();
  }

  private String getMenuWithRoleFXML(UserRoleDTO role) {
    if (role.getId().equals(MenuFXMLPath.RECEPTIONIST.name())) return MenuFXMLPath.SELECT_CENTER.toString();
    if (role.getId().equals(MenuFXMLPath.NURSE.name())) return MenuFXMLPath.SELECT_CENTER.toString();

    for (MenuFXMLPath path : MenuFXMLPath.values())
      if (path.name().equals(role.getId())) return path.toString();

    return null;
  }
}
