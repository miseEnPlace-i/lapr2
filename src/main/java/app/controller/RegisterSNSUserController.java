package app.controller;

import java.text.ParseException;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.store.SNSUserStore;

/**
 * Register SNS User Controller
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserController implements IRegisterController {

  private App app;
  private Company company;
  private SNSUserStore store;
  private SNSUser snsUser;

  /**
   * Constructor for RegisterSNSUserController.
   */
  public RegisterSNSUserController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.store = this.company.getSNSUserStore();
    this.snsUser = null;
  }

  /**
   * Creates an SNS User instance and validates it.
   * 
   * @param citizenCard
   * @param snsNumber
   * @param name
   * @param birthDay
   * @param gender
   * @param phoneNumber
   * @param email
   * @param address
   */
  public void create(String citizenCard, String snsNumber, String name, String birthDay,
      char gender, String phoneNumber, String email, String address)
      throws IllegalArgumentException, ParseException {
    // create an instance of an SNS User
    this.snsUser = store.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber,
        email, address);

    // validate the SNS User
    store.validateSNSUser(snsUser);
  }

  /**
   * Adds an SNS User to the SNS Users list.
   */
  @Override
  public void save() {
    store.saveSNSUser(this.snsUser);
  }

  /**
   * Returns the SNS User data as a String.
   */
  public String stringifyData() {
    return snsUser.toString();
  }
}
