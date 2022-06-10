package app.ui.gui;

import app.controller.App;
import app.controller.ImportLegacyDataController;
import app.domain.model.Company;
import app.session.EmployeeSession;

public class ImportLegacyData1UI extends ChildUI<CoordinatorUI> {
    private ImportLegacyDataController ctrl;
    private EmployeeSession employeeSession;

    void init() {
        Company company = App.getInstance().getCompany();
        this.employeeSession = super.getParentUI().getEmployeeSession();
        this.ctrl = new ImportLegacyDataController(company, this.employeeSession.getVaccinationCenter());
    }

    // TODO
}
