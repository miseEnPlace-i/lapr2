package app.ui.gui;

public class SNSUserUI extends RoleUI {
  void init(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @Override
  public String getUIRoleName() {
    return "SNS_USER";
  }
}
