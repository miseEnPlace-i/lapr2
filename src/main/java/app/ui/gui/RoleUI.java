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

    @Override
    public void setMainApp(ApplicationUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

    public abstract String getUIRoleName();
}