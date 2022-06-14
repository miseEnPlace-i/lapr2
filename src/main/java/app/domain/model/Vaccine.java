package app.domain.model;

import java.io.Serializable;
import app.domain.model.list.AdminProcList;
import app.domain.model.list.DoseInfoList;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class Vaccine implements Serializable {
  private String designation;
  private String brand;
  private String id;
  private VaccineType vacType;
  private AdminProcList adminProcList;


  public Vaccine(String designation, String id, String brand, VaccineType vacType) {
    setBrand(brand);
    setDesignation(designation);
    setId(id);
    setVacType(vacType);
    adminProcList = new AdminProcList();
  }

  // VALIDATIONS
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

  public void validateVacType(VaccineType vacType) {
    if (vacType == null) {
      throw new IllegalArgumentException("The vaccine type must exist.");
    }
  }


  // GETTER & SETTERS
  public String getDesignation() {
    return this.designation;
  }

  public void setDesignation(String designation) {
    validateDesignation(designation);
    this.designation = designation;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    validateBrand(brand);
    this.brand = brand;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    validateId(id);
    this.id = id;
  }

  public VaccineType getVacType() {
    return this.vacType;
  }

  public void setVacType(VaccineType vacType) {
    validateVacType(vacType);
    this.vacType = vacType;
  }


  // GET ADMINSTRATION PROCESS LIST
  public AdminProcList getAdminProcList() {
    return adminProcList;
  }


  // CREATE ADMINISTRATION PROCESS
  public AdminProcess createAdminProc(int minAge, int maxAge, int numberOfDoses) {
    AdminProcess ap = new AdminProcess(minAge, maxAge, numberOfDoses);
    return ap;
  }

  // TO STRING
  @Override
  public String toString() {
    return "Designation: " + this.designation + "\nBrand: " + this.brand + "\nId: " + this.id + "\nVaccine Type: " + this.vacType.getCode();
  }

  // ADD A NEW AP
  public void addAdminProc(AdminProcess adminProc) {
    adminProcList.addAdminProc(adminProc);
  }

  /**
   * Checks if the vaccine has an admin. process that includes the given age.
   * 
   * @param age the age given
   * @return true if has an admin. process, false otherwise
   */
  public boolean hasAdministrationProcessForGivenAge(int age) {
    for (AdminProcess adPr : adminProcList.getList()) {
      if (adPr.admitsAge(age)) {
        return true;
      }
    }

    return false;
  }

  public AdminProcess getAdminProc(int age) {
    for (AdminProcess adPr : adminProcList.getList()) {
      if (adPr.admitsAge(age)) {
        return adPr;
      }
    }

    return null;
  }


  public boolean checkUserFullyVaccinated(int age, int dose) {

    AdminProcess adminProcByAge = getAdminProc(age);
    DoseInfoList doseInfoLst = adminProcByAge.getDoseInfoList();
    int size = doseInfoLst.getSize();

    return dose == size ? true : false;
  }

  /**
   * Get the dosage for the given age and dose number.
   * 
   * @param doseNumber the dose number
   * @param age the age
   * 
   * @return the dosage
   */
  public int getDosageByDoseNumberAndAge(int doseNumber, int age) {
    for (AdminProcess adPr : adminProcList.getList()) {
      if (adPr.admitsAge(age)) {
        return adPr.getDosageByDoseNumber(doseNumber);
      }
    }

    return 0;
  }
}

