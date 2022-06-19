package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.MenuFXMLPath;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public abstract class ChildUI<T extends RoleUI> implements Initializable {
  private T parentUI;

  ChildUI() {}

  public void setParentUI(T parentUI) {
    this.parentUI = parentUI;
  }

  public T getParentUI() {
    return this.parentUI;
  }

  @FXML
  private Label lblName;

  @FXML
  private Label lblRole;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
    String role = App.getInstance().getCurrentUserSession().getUserRoles().get(0).getDescription();

    lblName.setText(email);
    lblRole.setText(role);
  }

  abstract void init(T parentUI);

  protected void toRoleScene() {
    for (MenuFXMLPath path : MenuFXMLPath.values())
      if (path.name().equals(this.parentUI.getUIRoleName())) {
        try {
          ApplicationUI mainApp = this.parentUI.getMainApp();
          RoleUI gui = (RoleUI) mainApp.replaceSceneContent(path.toString());
          gui.init(mainApp);
        } catch (Exception e) {
          Logger.getLogger(AuthUI.class.getName()).log(Level.SEVERE, "No menu found for role: " + this.parentUI.getUIRoleName());
        }
      }
  }

  @FXML
  public void btnBack(ActionEvent event) {
    this.toRoleScene();
  }

  @FXML
  void handleGoToMenu(ActionEvent event) {
    App.getInstance().doLogout();
    this.parentUI.getMainApp().toMainScene();
  }

  @FXML
  void handleDevelopmentTeam(ActionEvent event) {
    App.getInstance().doLogout();
    try {
      DevTeamUI ui = (DevTeamUI) this.parentUI.getMainApp().replaceSceneContent("/fxml/DevTeam.fxml");
      ui.setMainApp(this.parentUI.getMainApp());
    } catch (Exception ex) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  void handleSaveSession(ActionEvent event) {
    if (App.getInstance().saveCurrentCompany()) Utils.showInformation("Session saved!", "Your changes have been saved successfully!");
  }

  @FXML
  abstract void handleHelp(ActionEvent event);

  @FXML
  void handleLogout(ActionEvent event) {
    if (Utils.showConfirmation("Logout?", "Are you sure you want to logout?")) {
      App.getInstance().doLogout();
      this.parentUI.getMainApp().toMainScene();
    }
  }

  @FXML
  void handleExit(ActionEvent event) {
    Utils.showExitConfirmation();
  }

  @FXML
  void handleRestoreSession() {
    App.getInstance().doLogout();
    App.getInstance().restoreCompany();
    this.parentUI.getMainApp().toMainScene();
  }

  @FXML
  void handlePreferences() {
    Runtime rs = Runtime.getRuntime();

    try {
      if (System.getProperty("os.name").contains("Win")) rs.exec("notepad " + Constants.PARAMS_FILENAME);
      else if (System.getProperty("os.name").contains("nux")) rs.exec("gedit " + Constants.PARAMS_FILENAME);
      else if (System.getProperty("os.name").contains("mac")) rs.exec("open " + Constants.PARAMS_FILENAME);
    } catch (Exception ex) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
