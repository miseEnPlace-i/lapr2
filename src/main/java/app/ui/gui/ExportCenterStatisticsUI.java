package app.ui.gui;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ExportCenterStatisticsController;
import app.controller.FindCoordinatorVaccinationCenterController;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * ExportCenterStatisticsUI
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsUI extends ChildUI<CoordinatorUI> {
    private ExportCenterStatisticsController ctrl;
    private EmployeeSession employeeSession;
    private FindCoordinatorVaccinationCenterController ctrlCenter;
    private Map<Calendar, Integer> dataMap;

    @FXML
    private Label lblCenterName;

    @FXML
    private DatePicker initialDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private Button exportStatistics;

    @FXML
    private TextField filePathName;

    @Override
    void init() {
        this.employeeSession = new EmployeeSession();
        this.ctrlCenter = new FindCoordinatorVaccinationCenterController(App.getInstance().getCompany(), employeeSession);

        this.ctrlCenter.findCoordinatorCenter();

        this.lblCenterName.setText(this.ctrlCenter.getVaccinationCenterName());
        try {
            this.ctrl = new ExportCenterStatisticsController(App.getInstance().getCompany(), employeeSession);
        } catch (NotAuthorizedException e) {
        }
    }

    private void displayExportInformation() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Please confirm the data");
        alert.setHeaderText("Confirm the data below:");
        alert.setContentText(toString());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation succeeded!");
            } else {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Operation canceled!");
            }
        });
    }

    @FXML
    void export(ActionEvent event) {
        this.displayExportInformation();
        ctrl.createFullyVaccinatedData(filePathName.getText(), getStartDate(), getEndDate());
        dataMap = ctrl.generateFullyVaccinatedUsersInterval();
        ctrl.createCsvExporter(filePathName.getText());
        ctrl.saveData(dataMap);
        super.btnBack(event);
    }

    private Calendar getStartDate() {
        LocalDate localDate = initialDate.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private Calendar getEndDate() {
        LocalDate localDate = endDate.getValue();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Data from the Center: %s ", lblCenterName.getText()));
        sb.append(String.format("\nFile Path Name: %s ", filePathName.getText()));
        sb.append(String.format("\nExport center statistics from: %s", format.format(getStartDate().getTime())));
        sb.append(String.format("\nTo: %s", format.format(getEndDate().getTime())));

        return sb.toString();
    }

}
