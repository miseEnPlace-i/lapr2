package app.domain.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import app.controller.App;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;

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
     * @param address
     * @return SNSUser
     */
    public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, String birthDayStr, char gender,
            String phoneNumber, String email, String address) {
        // TODO: improve this code (código desenrasca)
        String[] fields = birthDayStr.split("/");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(fields[2]), Integer.parseInt(fields[1]) - 1, Integer.parseInt(fields[0]));

        Date birthDay = calendar.getTime();

        SNSUser snsUser = new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
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
        String name = snsUser.getName();
        String email = snsUser.getEmail();
        // TODO: generate password
        String pwd = "123456";

        authFacade.addUserWithRole(name, email, pwd, Constants.ROLE_SNS_USER);

        snsUsers.add(snsUser);

        // TODO: send password email

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
