package app.domain.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.controller.App;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUserStore implements IStore {

    // User List
    private List<SNSUser> snsUsers;

    // Auth Facade
    private AuthFacade authFacade;

    /**
     * Constructor for SNSUserStore.
     */
    public SNSUserStore() {
        this.snsUsers = new ArrayList<SNSUser>();
        this.authFacade = App.getInstance().getCompany().getAuthFacade();
    }

    /**
     * Creates an SNS User instance.
     * 
     * @param citizenCard
     * @param snsNumber
     * @param name
     * @param birthDayStr
     * @param gender
     * @param phoneNumber
     * @param email
     * @return SNSUser
     */
    public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, String birthDayStr, char gender,
            String phoneNumber, String email) {
        // TODO: improve this code (código desenrasca)
        String[] fields = birthDayStr.split("/");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(fields[2]), Integer.parseInt(fields[1]) - 1, Integer.parseInt(fields[0]));

        Date birthDay = calendar.getTime();

        SNSUser snsUser = new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email);
        return snsUser;
    }

    /**
     * Checks if there are duplicates.
     * 
     * @param snsUser
     */
    public void validateSNSUser(SNSUser snsUser) {
        // get the SNS User email
        String email = snsUser.getEmail();

        // check with AuthFacade if the email is already in use
        boolean existsUser = this.authFacade.existsUser(email);

        if (existsUser) {
            throw new IllegalArgumentException("Email already in use");
        }

        checkDuplicates(snsUser);
    }

    /**
     * Inserts a SNS User object to the list.
     * 
     * @param user
     */
    public void saveSNSUser(SNSUser snsUser) {
        snsUsers.add(snsUser);
    }

    /**
     * Checks if there are duplicates in the store.
     * 
     * @param snsUser
     */
    public void checkDuplicates(SNSUser snsUser) {
        if (snsUsers.contains(snsUser)) {
            throw new IllegalArgumentException("Duplicate SNS User");
        }
    }
}
