package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import app.controller.App;
import app.session.EmployeeSession;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public abstract class EmployeeRoleUI extends RoleUI {
  protected EmployeeSession employeeSession = new EmployeeSession();

  @FXML
  private Label lblCenter;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
    String role = App.getInstance().getCurrentUserSession().getUserRoles().get(0).getDescription();

    super.lblName.setText(email);
    super.lblRole.setText(role);
    if (employeeSession.getVaccinationCenter() != null) lblCenter.setText(employeeSession.getVaccinationCenter().getName());
  }
}
