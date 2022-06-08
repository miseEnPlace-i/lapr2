package app.ui.gui;

import app.session.EmployeeSession;

public class NurseUI extends RoleUI {
  private EmployeeSession employeeSession;
  // select employee ctrl

  @Override
  void init() {

  }

  @Override
  public String getUIRoleName() {
    return "NURSE";
  }
}
