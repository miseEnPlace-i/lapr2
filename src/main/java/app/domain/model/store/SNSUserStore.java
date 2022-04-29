package app.domain.model.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.domain.model.CalendarUtils;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import app.domain.shared.EmailSender;
import app.domain.shared.PasswordGenerator;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUserStore {

    // User List
    private List<SNSUser> snsUsers;

    // Auth Facade
    private AuthFacade authFacade;

    /**
     * Constructor for SNSUserStore.
     */
    public SNSUserStore(AuthFacade authFacade) {
        this.snsUsers = new ArrayList<SNSUser>();
        this.authFacade = authFacade;
    }

    /**
     * Creates an SNS User instance.
     * 
     * @param citizenCard the citizen card of the SNS User.
     * @param snsNumber   the SNS Number of the SNS User.
     * @param name        the name of the SNS User.
     * @param birthDayStr the birth day as a string of the SNS User.
     * @param gender      the SNS User gender
     * @param phoneNumber SNS User phone number
     * @param email       SNS User email
     * @param address     SNS User address
     * @return SNSUser
     */
    public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, String birthDayStr, char gender,
            String phoneNumber, String email, String address) {
        Calendar birthDay;
        try {
            birthDay = CalendarUtils.parse(birthDayStr);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

        SNSUser snsUser = new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
        return snsUser;
    }

    /**
     * Checks if there are duplicates.
     * 
     * @param snsUser
     */
    public void validateSNSUser(SNSUser snsUser) {
        if (snsUser == null) {
            throw new Error("SNS User is null");
        }

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
     * Inserts a SNS User object to the list and adds a User to the AuthFacade.
     * 
     * @param user
     */
    public void saveSNSUser(SNSUser snsUser) {
        String name = snsUser.getName();
        String email = snsUser.getEmail();
        String pwd = PasswordGenerator.generatePwd();

        authFacade.addUserWithRole(name, email, pwd, Constants.ROLE_SNS_USER);

        snsUsers.add(snsUser);

        // TODO: send password email
        // EmailSender emailSender = new EmailSender();
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
