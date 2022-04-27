package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.SNSUserStore;

/**
 * Register SNS User Controller
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserController {

    private App app;
    private Company company;
    private SNSUserStore store;

    /**
     * Constructor for RegisterSNSUserController.
     */
    public RegisterSNSUserController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.store = this.company.getSNSUserStore();
    }

    /**
     * C
     * 
     * @param citizenCard
     * @param snsNumber
     * @param name
     * @param birthDay
     * @param gender
     * @param phoneNumber
     * @param email
     * @return SNSUser
     */
    public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, String birthDay, char gender, String phoneNumber, String email, String address) {
        // create an instance of an SNS User
        SNSUser snsUser = store.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);

        // validate the SNS User
        store.validateSNSUser(snsUser);

        return snsUser;
    }

    /**
     * 
     */
    public void saveSNSUser(SNSUser snsUser) {
        store.saveSNSUser(snsUser);
    }
}