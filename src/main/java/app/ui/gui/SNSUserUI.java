package app.ui.gui;

public class SNSUserUI extends RoleUI {
  // maybe this class shouldn't inherit from RoleUI
  // because it shouldn't have an employee session?

  private ApplicationUI mainApp;

  void init(ApplicationUI mainApp) {
    this.mainApp = mainApp;
  }

  @Override
  public String getUIRoleName() {
    return "SNS_USER";
  }
}
