package app.controller;

import java.util.Date;
import app.domain.model.Company;
import app.domain.model.CsvExporterData;
import app.domain.model.VaccinationCenter;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;

public class CsvExportController {
    private Company company;
    private EmployeeSession session;

    public CsvExportController(Company company, EmployeeSession coordinatorSession) throws NotAuthorizedException {
        if (!coordinatorSession.hasCenter()) throw new NotAuthorizedException("Coordinator is not logged in");
        this.session = coordinatorSession;
        this.company = company;
    }

    public CsvExporterData createCsvExporterData(String filePath, Date start, Date end) {
        VaccinationCenter center = session.getVaccinationCenter();

        CsvExporterData exporter = new CsvExporterData(filePath, start, end, center);

        return exporter;
    }

}
