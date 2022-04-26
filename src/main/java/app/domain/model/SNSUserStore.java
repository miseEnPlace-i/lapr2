package app.domain.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUserStore {

    // User List
    private List<SNSUser> snsUsers;

    /**
     * Constructor for SNSUserStore.
     */
    public SNSUserStore() {
        this.snsUsers = new ArrayList<SNSUser>();
    }

    public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, String birthDayStr, char gender, String phoneNumber, String email) {
        // TODO: improve this code (código desenrasca)
        String[] fields = birthDayStr.split("/");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(fields[2]), Integer.parseInt(fields[1]) - 1, Integer.parseInt(fields[0]));

        Date birthDay = calendar.getTime();

        SNSUser snsUser = new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email);
        return snsUser;
    }

    public void validateUser(SNSUser snsUser) {
        // get the SNS User email
        String email = snsUser.getEmail();

        // get an instance of AuthFacade to check if the email is already in use
        // TODO
    }

    /**
     * Inserts a SNS User object to the list.
     * 
     * @param user
     */
    public void saveUser(SNSUser snsUser1) {
        snsUsers.add(snsUser1);
    }

    public void checkDuplicates() {
    }
}
