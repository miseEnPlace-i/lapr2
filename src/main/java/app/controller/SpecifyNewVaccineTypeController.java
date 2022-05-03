package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccineTypeStore;

/**
 * Specify New Vaccine Type Controller
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class SpecifyNewVaccineTypeController {
  private App app;
  private Company company;
  private VaccineTypeStore vaccineTypeStore;
  private VaccineType vt;

  /**
   * Constructor for SpecifyNewVaccineTypeController
   */
  public SpecifyNewVaccineTypeController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.vaccineTypeStore = this.company.getVaccineTypeStore();
  }

  public VaccineType addVaccineType(String name, String description, String technology) {
    this.vt = vaccineTypeStore.addVaccineType(name, description, technology);

    return this.vt;
  }

  public boolean saveVaccineType() {
    return vaccineTypeStore.saveVaccineType(this.vt);
  }
}
