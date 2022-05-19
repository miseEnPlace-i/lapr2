package app.controller;

import app.domain.model.Company;
import app.domain.model.store.AppointmentStore;

/**
 * Register SNS User Arrival Controller.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController {
    private Company company;
    private AppointmentStore store;

    /**
     * Constructor for RegisterSNSUserController.
     */
    public RegisterSNSUserArrivalController(Company company) {
        this.company = company;
        this.store = this.company.getAppointmentStore();
    }

    public void findAppointment(String snsNumber) {
        store.findAppointment(snsNumber);
    }
}
