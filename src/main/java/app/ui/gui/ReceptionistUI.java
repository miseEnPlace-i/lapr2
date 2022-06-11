package app.ui.gui;

import app.session.EmployeeSession;

public class ReceptionistUI extends RoleUI {
  private EmployeeSession employeeSession;
  // select employee ctrl

  void init() {
    
  }

  @Override
  public String getUIRoleName() {
    return "RECEPTIONIST";
  }
}
