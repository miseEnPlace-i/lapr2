package app.domain.model;

import app.domain.model.list.AdminProcList;
import app.domain.model.store.VaccineTypeStore;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class Vaccine {

  private String designation;
  private String brand;
  private String id;
  private VaccineType vacType;
  private AdminProcList adminProcList;

  public Vaccine(String designation, String id, String brand, String vacTypeId) {
    validateDesignation(designation);
    validateBrand(brand);
    validateId(id);

    this.designation = designation;
    this.brand = brand;
    this.id = id;
    this.vacType = VaccineTypeStore.getVaccineTypeById(vacTypeId);
  }

  public void validateDesignation(String designation) {
    if (designation == "" || designation == null) {
      throw new IllegalArgumentException("Designation field is required.");
    }
  }

  public void validateBrand(String brand) {
    if (brand == "" || brand == null) {
      throw new IllegalArgumentException("Brand field is required.");
    }
  }

  public void validateId(String id) {
    if (id == "" || id == null) {
      throw new IllegalArgumentException("Id field is required.");
    }
  }

  public void validateVacType(String vacTypeId) {
    if (vacTypeId == "" || vacTypeId == null) {
      throw new IllegalArgumentException("Vaccine type id field is required.");
    }

    if (VaccineTypeStore.getVaccineTypeById(vacTypeId) == null) {
      throw new IllegalArgumentException("The vaccine type must exist.");
    }
  }


  public void createAdminProc(int minAge, int maxAge, int numberOfDoses) {
    // TODO
  }


}
