package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.AuthController;
import app.domain.shared.MenuFXMLPath;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

public class AuthUI implements Initializable, IGui {
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

  @Override
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
    login();
  }

  @FXML
  void onKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) login();
  }

  private void login() {
    String email = txtEmail.getText();
    String pwd = txtPwd.getText();

    if (!ctrl.doLogin(email, pwd)) {
      // TODO 3 tries system
      return;
    }

    UserRoleDTO role = ctrl.getUserRoles().get(0);
    String menuFXML = getMenuWithRoleFXML(role);

    try {
      IGui gui = (IGui) mainApp.replaceSceneContent(menuFXML);
      gui.setMainApp(mainApp);
    } catch (Exception e) {
      Logger.getLogger(ApplicationUI.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  private String getMenuWithRoleFXML(UserRoleDTO role) {
    for (MenuFXMLPath path : MenuFXMLPath.values())
      if (path.name().equals(role.getId())) return path.toString();

    return null;
  }
}
