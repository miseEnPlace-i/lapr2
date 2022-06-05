package app.ui.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class MainUI implements Initializable {
    private App mainApp;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPwd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void btnExit(ActionEvent event) {
        mainApp.getStage().close();
    }

    @FXML
    void btnLogin(ActionEvent event) {
        // test
        try {
            BaseScreenUI analyseCenterUI = (BaseScreenUI) this.mainApp.replaceSceneContent("/fxml/BaseScreen.fxml");
            analyseCenterUI.setMainApp(this.mainApp);
        } catch (Exception e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
