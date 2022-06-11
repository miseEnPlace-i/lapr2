package app.ui.gui;

import app.ui.console.UploadUsersFromFileUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AdminUI extends RoleUI {
  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
    // If you need to do something when the UI is initialized, do it here
  }

  @FXML
  void importSNSUsers(ActionEvent event) {
    (new UploadUsersFromFileUI()).run();
  }

  @Override
  public String getUIRoleName() {
    return "ADMINISTRATOR";
  }
}
