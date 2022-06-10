package app.ui.gui;

import app.controller.AnalyseCenterPerformanceController;
import app.controller.App;
import app.session.EmployeeSession;

public class AnalyseCenterPerformanceUI extends ChildUI<CoordinatorUI> {
  private AnalyseCenterPerformanceController ctrl;
  private EmployeeSession employeeSession;

  void init() {
    this.ctrl = new AnalyseCenterPerformanceController(App.getInstance().getCompany(), employeeSession);
  }

  public void setEmployeeSession(EmployeeSession session) {
    this.employeeSession = session;
  }
}
