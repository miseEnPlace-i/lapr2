package app.controller;

import java.util.Date;
import app.domain.model.Company;
import app.domain.model.FullyVaccinatedData;
import app.domain.model.VaccinationCenter;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;

public class ExportCenterStatisticsController {
    private Company company;
    private EmployeeSession session;

    public ExportCenterStatisticsController(Company company, EmployeeSession coordinatorSession) throws NotAuthorizedException {
        if (!coordinatorSession.hasCenter()) throw new NotAuthorizedException("Coordinator is not logged in");
        this.session = coordinatorSession;
        this.company = company;
    }

    public FullyVaccinatedData createCsvExporterData(String filePath, Date start, Date end) {
        VaccinationCenter center = session.getVaccinationCenter();

        FullyVaccinatedData exporter = new FullyVaccinatedData(filePath, start, end, center);

        return exporter;
    }

}
