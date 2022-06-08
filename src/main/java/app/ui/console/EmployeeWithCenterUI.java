package app.ui.console;

import app.session.EmployeeSession;

public abstract class EmployeeWithCenterUI implements Runnable {
  private EmployeeSession employeeSession = new EmployeeSession();

  public EmployeeWithCenterUI() {}

  public void run() {
    new SelectEmployeeVaccinationCenterUI(employeeSession).run();

    if (!employeeSession.hasCenter()) return;

    this.callback();
  }

  public EmployeeSession getEmployeeSession() {
    return employeeSession;
  }

  public String getCurrentVaccinationCenter() {
    return employeeSession.getVaccinationCenter().getName();
  }

  public abstract void callback();
}
