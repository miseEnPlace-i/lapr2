package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.domain.shared.MenuFXMLPath;
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
}
