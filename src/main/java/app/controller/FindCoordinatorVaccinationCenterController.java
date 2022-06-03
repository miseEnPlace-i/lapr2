package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;
import app.session.EmployeeSession;

public class FindCoordinatorVaccinationCenterController {
    private VaccinationCenterStore vaccinationCenterStore;
    private EmployeeSession session;

    public FindCoordinatorVaccinationCenterController(Company company, EmployeeSession session) {
        this.vaccinationCenterStore = company.getVaccinationCenterStore();
        this.session = session;
    }
    
    public void findCoordinatorCenter() {
        String email = session.getEmail();
        VaccinationCenter center = this.vaccinationCenterStore.getVaccinationCenterWithCoordinatorEmail(email);
        this.session.setVaccinationCenter(center);
    }

    public String getVaccinationCenterName() {
        if (!this.session.hasCenter()) return null;
        return this.session.getVaccinationCenter().getName();
    }

    // public void setVaccinationCenter(VaccinationCenter center) {
    //     this.session.setVaccinationCenter(center);
    // }
}
