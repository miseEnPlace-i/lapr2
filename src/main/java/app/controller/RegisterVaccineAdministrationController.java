package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineAdministration;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccineStore;

/**
 * Register Vaccine Administration Controller
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class RegisterVaccineAdministrationController implements IRegisterController<VaccineAdministration> {
  private Company company;
  private VaccineStore vaccineStore;
  private SNSUserStore snsUserStore;
  private VaccineAdministration vaccineAdministration;
  private VaccineAdministrationList vaccineAdministrationList;

  /**
   * Constructor for RegisterVaccineAdministrationController.
   * 
   * @param company Company
   */
  public RegisterVaccineAdministrationController(Company company) {
    this.company = company;
    this.vaccineStore = company.getVaccineStore();
    this.snsUserStore = company.getSNSUserStore();
  }

  /**
   * Get the resource name.
   * 
   * @return the resource name
   */
  @Override
  public String getResourceName() {
    return "Vaccine Administration";
  }

  /**
   * Get the registered object.
   * 
   * @return the registered object
   */
  @Override
  public VaccineAdministration getRegisteredObject() {
    return this.vaccineAdministration;
  }

  /**
   * Get the Vaccine Administration data stringified.
   * 
   * @return the data stringified
   */
  @Override
  public String stringifyData() {
    return this.vaccineAdministration.toString();
  }

  /**
   * Saves the Vaccine Administration.
   * 
   * @param VaccineAdministration the Vaccine Administration to be saved
   */
  @Override
  public void save() {
    this.vaccineAdministrationList.saveVaccineAdministration(this.vaccineAdministration);
  }
}
