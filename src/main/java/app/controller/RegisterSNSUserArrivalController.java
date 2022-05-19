package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.store.SNSUserStore;

/**
 * Register SNS User Arrival Controller.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController {
    private Company company;
    private SNSUserStore store;
    private SNSUser snsUser;

    /**
     * Constructor for RegisterSNSUserController.
     */
    public RegisterSNSUserArrivalController(Company company) {
        this.company = company;
        this.store = this.company.getSNSUserStore();
        this.snsUser = null;
    }

    public void findAppointment(String snsNumber) {
        
    }
}
