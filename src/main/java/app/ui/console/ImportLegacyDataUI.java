package app.ui.console;

import app.controller.App;
import app.controller.ImportLegacyDataController;
import app.domain.model.Company;

public class ImportLegacyDataUI implements Runnable {
    private ImportLegacyDataController ctrl;

    public ImportLegacyDataUI() {
        App app = App.getInstance();
        Company company = app.getCompany();
        this.ctrl = new ImportLegacyDataController(company);
    }

    @Override
    public void run() {
        
    }
}
