package app.domain.model.store;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.domain.model.SNSUser;

/**
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

  /**
   * Creates an SNS User instance.
   * 
   * @param citizenCard the citizen card of the SNS User.
   * @param snsNumber the SNS Number of the SNS User.
   * @param name the name of the SNS User.
   * @param birthDayStr the birth day as a string of the SNS User.
   * @param gender the SNS User gender
   * @param phoneNumber SNS User phone number
   * @param email SNS User email
   * @param address SNS User address
   * @return SNSUser
   */
  public SNSUser createSNSUser(String citizenCard, String snsNumber, String name, Date birthDay,
      char gender, String phoneNumber, String email, String address)
      throws IllegalArgumentException, ParseException {
    SNSUser snsUser =
        new SNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);

    return snsUser;
  }

  /**
   * Checks if there are duplicates.
   * 
   * @param snsUser
   */
  public void validateSNSUser(SNSUser snsUser) {
    if (snsUser == null) throw new Error("SNS User is null.");

    checkDuplicates(snsUser);
  }

  /**
   * Inserts a SNS User object to the list and adds a User to the AuthFacade.
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
    if (snsUsers.contains(snsUser)) throw new IllegalArgumentException("Duplicate SNS User.");
  }

  /**
   * Finds a SNS User by SNS Number.
   * 
   * @param snsNumber The SNS User Number.
   * @return SNSUser
   */
  public SNSUser findSNSUserByNumber(String snsNumber) {
    for (SNSUser snsUser : snsUsers) {
      if (snsUser.getSnsNumber().equals(snsNumber)) {
        return snsUser;
      }
    }

    return null;
  }

  /**
   * Finds a SNS User by Email.
   * 
   * @param email The SNS User email.
   * @return SNSUser
   */
  public SNSUser findSNSUserByEmail(String email) {
    for (SNSUser snsUser : snsUsers) {
      if (snsUser.getEmail().equals(email)) {
        return snsUser;
      }
    }

    return null;
  }

  /**
   * Gets the size of the store.
   * 
   * @return int of number of SNS Users in the store.
   */
  public int size() {
    return snsUsers.size();
  }
}
