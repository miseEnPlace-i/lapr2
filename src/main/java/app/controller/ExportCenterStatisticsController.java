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

/**
 * ExportCenterStatisticsController
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class ExportCenterStatisticsController {
    private Company company;
    private EmployeeSession session;
    private FullyVaccinatedData exporter;
    private Map<Calendar, Integer> dataMap;
    private CsvExporter csvExporter;

    /**
     * ExportCenterStatisticsController constructor
     * 
     * @param company the company
     * @param coordinatorSession the coordinator session
     * @throws NotAuthorizedException exception if coordinator is not logged in
     */
    public ExportCenterStatisticsController(Company company, EmployeeSession coordinatorSession) throws NotAuthorizedException {
        if (!coordinatorSession.hasCenter()) throw new NotAuthorizedException("Coordinator is not logged in.");
        this.session = coordinatorSession;
        this.company = company;
    }

    /**
     * Creates fullyVaccinatedData
     * 
     * @param filePath the file path to save the statistics
     * @param start the start date of statistics
     * @param end the end date of statistics
     * @return fullyVaccinatedData object
     */
    public FullyVaccinatedData createFullyVaccinatedData(String filePath, Calendar start, Calendar end) {
        VaccinationCenter center = session.getVaccinationCenter();

        FullyVaccinatedData exporter = new FullyVaccinatedData(filePath, start, end, center);

        return exporter;
    }

    /**
     * Gathers all the information needed to export to a file the statistics
     * 
     * @param exporter -> FullyVaccinatedData Object
     * @return hashMap will all the data needed
     */
    public Map<Calendar, Integer> generateFullyVaccinatedUsersInterval(FullyVaccinatedData exporter) {
        dataMap = exporter.getFullyVaccinatedUsersPerDayMap();
        return dataMap;
    }

    /**
     * Creates CsvExporter
     * 
     * @param filePath the file path to save the statistics
     * @return
     */
    public CsvExporter createCsvExporter(String filePath) {
        csvExporter = new CsvExporter(filePath);
        return csvExporter;
    }

    /**
     * Saves data
     * 
     * @param dataMap the hashMap will all information
     */
    public void saveData(Map dataMap) {
        csvExporter.writeToFile(dataMap);
    }
}
