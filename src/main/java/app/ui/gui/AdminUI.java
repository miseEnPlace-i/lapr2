package app.ui.gui;

public class AdminUI extends RoleUI {
  @Override
  void init(ApplicationUI mainApp) {
    this.setMainApp(mainApp);
    // If you need to do something when the UI is initialized, do it here
  }

  @Override
  public String getUIRoleName() {
    return "ADMINISTRATOR";
  }
}
