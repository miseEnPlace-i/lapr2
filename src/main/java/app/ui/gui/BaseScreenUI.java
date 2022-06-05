package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class BaseScreenUI implements Initializable {
    private App mainApp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnBack(ActionEvent event) {
        this.mainApp.toMainScene();
    }
}
