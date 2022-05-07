package app.controller;

import java.util.List;
import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;


/**
 * Create Vaccine Controller
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class RegisterVaccineController implements IRegisterController {
  private App app;
  private Company company;
  private VaccineStore vaccineStore;
  private VaccineTypeStore vaccineTypeStore;

  /**
   * Constructor for CreateVaccineController
   */
  public RegisterVaccineController() {
    this.app = App.getInstance();
    this.company = this.app.getCompany();
    this.vaccineStore = this.company.getVaccineStore();
    this.vaccineTypeStore = this.company.getVaccineTypeStore();
  }


  public List<VaccineType> getVacTypes() {
    // TODO
    return null;
  }

  public List<Vaccine> getVaccines() {
    // TODO
    return null;
  }

  public void create(String designation, String id, String brand, String vacTypeId) {
    // TODO
  }


  public void createAdminProc(int minAge, int maxAge, int numberOfDoses) {
    // TODO
  }

  public void saveAdminProc() {
    // TODO
  }

  public void createDoseInfo(int dosage, int timaToNextDose) {
    // TODO
  }

  public void saveDoseInfo() {
    // TODO
  }

  @Override
  public void save() {
    // TODO
  }

  @Override
  public String stringifyData() {
    // TODO
    return null;
  }

}
