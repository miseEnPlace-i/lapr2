package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class DevTeamUI implements Initializable {
    private ApplicationUI mainApp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setMainApp(ApplicationUI mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }

}
