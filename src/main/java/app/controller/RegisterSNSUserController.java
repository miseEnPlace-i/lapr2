package app.controller;

import app.domain.model.SNSUser;
import app.domain.model.SNSUserStore;

/**
 * Register SNS User Controller
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserController {

    private App app;

    /**
     * Constructor for RegisterSNSUserController.
     */
    public RegisterSNSUserController() {
        this.app = App.getInstance();
    }

    /**
     * 
     * 
     * @param citizenCard
     * @param snsNumber
     * @param name
     * @param birthDay
     * @param gender
     * @param phoneNumber
     * @param email
     */
    public void createSNSUser(String citizenCard, String snsNumber, String name, String birthDay, char gender, String phoneNumber, String email) {
        // get the SNS User Store from Company
        SNSUserStore store = this.app.getCompany().getSNSUserStore();

        // create an instance of an SNS User
        SNSUser snsUser = store.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email);

        // validate the SNS User
        // store.validateUser(snsUser);
    }

    /**
     * 
     */
    public void saveSNSUser() {
    }
}