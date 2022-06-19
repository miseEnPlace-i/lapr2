package app.controller;

import app.domain.model.AdverseReaction;
import app.domain.model.Company;
import app.domain.model.HealthData;
import app.domain.model.SNSUser;
import app.domain.model.store.SNSUserStore;

public class RegisterAdverseReactionController implements IRegisterController<AdverseReaction> {
  private Company company;
  private SNSUserStore snsUserStore;
  private AdverseReaction adverseReaction;
  private HealthData userHealthData;

  public RegisterAdverseReactionController(Company company) {
    this.company = company;
    this.snsUserStore = this.company.getSNSUserStore();
  }

  /**
   * Creates a new adverse reaction for a user
   * 
   * @param snsNumber the SNS number of the user
   * @param description the description of the adverse reaction
   */
  public void createAdverseReaction(String snsNumber, String description) {
    this.adverseReaction = new AdverseReaction(description);
    SNSUser snsUser = getSNSUser(snsNumber);
    this.userHealthData = snsUser.getUserHealthData();
  }

  /**
   * @param snsNumber the SNS number of the user
   * @return the SNS user with the given SNS number
   */
  private SNSUser getSNSUser(String snsNumber) {
    SNSUser snsUser = this.snsUserStore.findSNSUserByNumber(snsNumber);

    if (snsUser == null) throw new IllegalArgumentException("SNS User not found");

    return snsUser;
  }

  @Override
  public String stringifyData() {
    return this.adverseReaction.toString();
  }

  @Override
  public AdverseReaction getRegisteredObject() {
    return this.adverseReaction;
  }

  @Override
  public String getResourceName() {
    return "Adverse Reaction";
  }

  @Override
  public void save() {
    this.userHealthData.addAdverseReaction(this.adverseReaction);
  }
}
