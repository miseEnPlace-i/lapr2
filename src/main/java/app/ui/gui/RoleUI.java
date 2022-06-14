package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public abstract class RoleUI implements Initializable, IGui {
  protected ApplicationUI mainApp;

  @FXML
  protected Label lblName;

  @FXML
  protected Label lblRole;

  @FXML
  private MenuItem menuGoToMenu;

  @FXML
  private MenuItem menuSaveSession;

  @FXML
  private MenuItem menuLogout;

  @FXML
  private MenuItem menuExit;

  @FXML
  private MenuItem menuDevelopmentTeam;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
    String role = App.getInstance().getCurrentUserSession().getUserRoles().get(0).getDescription();

    lblName.setText(email);
    lblRole.setText(role);
  }

  abstract void init(ApplicationUI mainApp) throws Exception;

  @Override
  public void setMainApp(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  public ApplicationUI getMainApp() {
    return this.mainApp;
  }

  @FXML
  void btnBack(ActionEvent event) {
    App.getInstance().doLogout();
    this.mainApp.toMainScene();
  }

  public abstract String getUIRoleName();

  @FXML
  void handleGoToMenu(ActionEvent event) {
    App.getInstance().doLogout();
    this.mainApp.toMainScene();
  }

  @FXML
  void handleDevelopmentTeam(ActionEvent event) {
    App.getInstance().doLogout();
    try {
      DevTeamUI ui = (DevTeamUI) this.mainApp.replaceSceneContent("/fxml/DevTeam.fxml");
      ui.setMainApp(this.mainApp);
    } catch (Exception ex) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  @FXML
  void handleSaveSession(ActionEvent event) {
    if (App.getInstance().saveCurrentCompany()) Utils.showInformation("Session saved!", "Your changes have been saved successfully!");
  }

  @FXML
  void handleLogout(ActionEvent event) {
    if (Utils.showConfirmation("Logout?", "Are you sure you want to logout?")) {
      App.getInstance().doLogout();
      this.mainApp.toMainScene();
    }
  }

  @FXML
  void handleExit(ActionEvent event) {
    Utils.showExitConfirmation();
  }
}
