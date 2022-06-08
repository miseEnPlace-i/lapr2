package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;

/**
 * Specify New Vaccine Type Controller
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class RegisterNewVaccineTypeController implements IRegisterController<VaccineType> {
  private Company company;
  private VaccineTypeStore vaccineTypeStore;
  private VaccineType vt;
  private VaccineTechnologyStore vaccineTechnologyStore;

  /**
   * Constructor for SpecifyNewVaccineTypeController
   */
  public RegisterNewVaccineTypeController(Company company) {
    this.company = company;
    this.vaccineTypeStore = this.company.getVaccineTypeStore();
    this.vaccineTechnologyStore = this.company.getVaccineTechnologyStore();
  }

  /**
   * Method to add a new vaccine type to vaccine type list
   * 
   * @param code the vaccine type code
   * @param description the vaccine type description
   * @param technology the vaccine type technology
   * @return vaccine type
   */
  public VaccineType create(String code, String description, String technology) {
    this.vt = vaccineTypeStore.addVaccineType(code, description, technology);

    return this.vt;
  }

  @Override
  public void save() {
    vaccineTypeStore.saveVaccineType(this.vt);
  }

  @Override
  public String stringifyData() {
    return this.vt.toString();
  }

  @Override
  public String getResourceName() {
    return "Vaccine Type";
  }

  public List<String> getVaccineTechnologyList() {
    return vaccineTechnologyStore.getList();
  }

  @Override
  public VaccineType getRegisteredObject() {
    return vt;
  }
}
