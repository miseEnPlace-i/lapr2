package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;

public class ImportLegacyDataController {
    private Company company;
    private VaccinationCenter center;

    public ImportLegacyDataController(Company company/*, VaccinationCenter center */) {
        this.company = company;
        // this.center = center;
    }
}
