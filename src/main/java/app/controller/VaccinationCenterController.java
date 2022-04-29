package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;

public class VaccinationCenterController {
    private App app;
    private Company company;
    private VaccinationCenter center;
    private VaccinationCenterStore vacStore;

    public VaccinationCenterController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.vacStore = this.company.getVaccinationCenterStore();
        this.center = null;
    }

    public void createVaccinationCenter(String name, String address, String emailAddress,
            int phoneNum, int faxNum, String webAddress, String openingHours, String closingHours,
            int slotDuration, int maxVacSlot, Employee coordinator) {

        this.center = vacStore.createVaccinationCenter(name, address, emailAddress, phoneNum,
                faxNum, webAddress, openingHours, closingHours, slotDuration, maxVacSlot,
                coordinator);

        vacStore.validateVaccinationCenter(center);
    }

    public void saveVaccinationCenter() {
        vacStore.saveVaccinationCenter(center);
    }
}
