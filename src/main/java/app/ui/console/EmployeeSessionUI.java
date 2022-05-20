package app.ui.console;

import app.controller.session.EmployeeSession;

public abstract class EmployeeSessionUI implements Runnable {
    private EmployeeSession employeeSession = new EmployeeSession();

    public EmployeeSessionUI() {}

    public void run() {
        new SelectEmployeeVaccinationCenterUI(employeeSession).run();

        new SelectEmployeeVaccinationCenterUI(employeeSession).run();

        if (!employeeSession.isLoggedIn()) return;

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
