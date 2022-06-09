package app.ui.gui;

import app.controller.App;
import app.controller.ExportCenterStatisticsController;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.domain.model.Company;
import app.session.EmployeeSession;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * ExportCenterStatisticsUI
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsUI extends ChildUI<CoordinatorUI> {
    private ExportCenterStatisticsController ctrl;
    private EmployeeSession employeeSession;
    private FindCoordinatorVaccinationCenterController ctrlCenter;

    @FXML
    private Label lblCenterName;

    void init() {
        App app = App.getInstance();
        Company company = app.getCompany();
        this.employeeSession = new EmployeeSession();
        this.ctrlCenter = new FindCoordinatorVaccinationCenterController(company, employeeSession);

        this.ctrlCenter.findCoordinatorCenter();

        this.lblCenterName.setText(this.ctrlCenter.getVaccinationCenterName());
    }
}
