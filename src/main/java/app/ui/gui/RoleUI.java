package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import app.controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public abstract class RoleUI implements Initializable, IGui {
  protected ApplicationUI mainApp;

  @FXML
  protected Label lblName;

  @FXML
  protected Label lblRole;

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
}
