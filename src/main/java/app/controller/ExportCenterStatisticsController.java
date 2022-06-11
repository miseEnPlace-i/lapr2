package app.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import app.domain.model.Company;
import app.domain.model.CsvExporter;
import app.domain.model.VaccinationCenter;
import app.exception.NotAuthorizedException;
import app.service.FullyVaccinatedData;
import app.session.EmployeeSession;

public class ExportCenterStatisticsController {
    private Company company;
    private EmployeeSession session;
    private FullyVaccinatedData exporter;
    private Map<Calendar, Integer> dataMap;
    private CsvExporter csvExporter;

    public ExportCenterStatisticsController(Company company, EmployeeSession coordinatorSession) throws NotAuthorizedException {
        if (!coordinatorSession.hasCenter()) throw new NotAuthorizedException("Coordinator is not logged in.");
        this.session = coordinatorSession;
        this.company = company;
    }

    public FullyVaccinatedData createFullyVaccinatedData(String filePath, Calendar start, Calendar end) {
        VaccinationCenter center = session.getVaccinationCenter();

        FullyVaccinatedData exporter = new FullyVaccinatedData(filePath, start, end, center);

        return exporter;
    }

    public Map<Calendar, Integer> generateFullyVaccinatedUsersInterval(FullyVaccinatedData exporter) {
        dataMap = exporter.getFullyVaccinatedUsersPerDayMap();
        return dataMap;
    }

    public CsvExporter createCsvExporter(String filePath) {
        csvExporter = new CsvExporter(filePath);
        return csvExporter;
    }

    public void saveData(Map dataMap) {
        csvExporter.writeToFile(dataMap);
    }
}
