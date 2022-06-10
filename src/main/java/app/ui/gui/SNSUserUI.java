package app.ui.gui;

public class SNSUserUI extends RoleUI {
  // maybe this class shouldn't inherit from RoleUI
  // because it shouldn't have an employee session?
  void init() {
    // If you need to do something when the UI is initialized, do it here
  }

  @Override
  public String getUIRoleName() {
    return "SNS_USER";
  }
}
