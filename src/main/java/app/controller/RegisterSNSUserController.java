package app.controller;

import java.text.ParseException;
import java.util.Date;
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
  public RegisterSNSUserController(Company company) {
    this.company = company;
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
  public void create(String citizenCard, String snsNumber, String name, Date birthDay, char gender,
      String phoneNumber, String email, String address)
      throws IllegalArgumentException, ParseException {
    // create an instance of an SNS User
    this.snsUser = store.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber,
        email, address);

    // validate the SNS User
    store.validateSNSUser(snsUser);
  }

  @Override
  public void save() {
    store.saveSNSUser(this.snsUser);
  }

  @Override
  public String stringifyData() {
    return snsUser.toString();
  }

  @Override
  public String getResourceName() {
    return "SNS User";
  }
}
