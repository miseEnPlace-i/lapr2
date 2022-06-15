package app.domain.model;

import java.io.Serializable;
import app.domain.model.list.DoseInfoList;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class AdminProcess implements Serializable {

  private int minAge;
  private int maxAge;
  private int numberOfDoses;
  private DoseInfoList doseInfoList;


  public AdminProcess(int minAge, int maxAge, int numberOfDoses) {
    setAgeInterval(minAge, maxAge);
    setNumberOfDoses(numberOfDoses);
    doseInfoList = new DoseInfoList();
  }

  // GETTERS AND SETTERS
  public int getMinAge() {
    return this.minAge;
  }

  public int getMaxAge() {
    return this.maxAge;
  }

  public void setAgeInterval(int minAge, int maxAge) {
    validateMaxAge(maxAge);
    validateMinAge(minAge);
    validateInterval(minAge, maxAge);
    this.maxAge = maxAge;
    this.minAge = minAge;
  }

  public int getNumberOfDoses() {
    return this.numberOfDoses;
  }

  public void setNumberOfDoses(int numberOfDoses) {
    validateNumberOfDoses(numberOfDoses);
    this.numberOfDoses = numberOfDoses;
  }


  // VALIDATIONS
  public void validateMinAge(int minAge) {
    if (minAge < 0) {
      throw new IllegalArgumentException("The minimum age must be positive.");
    }
  }

  public void validateMaxAge(int maxAge) {
    if (maxAge < 0) {
      throw new IllegalArgumentException("The maximum age must be positive.");
    }
  }

  public void validateNumberOfDoses(int numberOfDoses) {
    if (numberOfDoses <= 0) throw new IllegalArgumentException("The number of doses must be positive.");
  }

  public void validateInterval(int minAge, int maxAge) {
    if (minAge > maxAge) throw new IllegalArgumentException("The minimum age must be smaller than the maximum.");
  }


  // CREATE DOSE INFO
  public DoseInfo createDoseInfo(int dosage, int timeSinceLastDose) {
    DoseInfo di = new DoseInfo(dosage, timeSinceLastDose);
    return di;
  }

  // GET LIST OF DOSE INFO
  public DoseInfoList getDoseInfoList() {
    return doseInfoList;
  }

  // TO STRING
  @Override
  public String toString() {
    return "Min age: " + this.minAge + "\nMax age: " + this.maxAge + "\nNumberOfDoses: " + this.numberOfDoses;
  }

  // ADD A NEW DOSE INFO
  public void addDoseInfo(DoseInfo doseInfo) {
    doseInfoList.addDoseInfo(doseInfo);
  }

  /**
   * Checks if the given age is between minAge and maxAge.
   * 
   * @param age the age to be checked
   * @return true if it is between interval, false otherwise
   */
  public boolean admitsAge(int age) {
    return ((age >= minAge) && (age <= maxAge));
  }

  public int getDosageByDoseNumber(int doseNumber) {
    DoseInfo doseInfo = doseInfoList.getDoseInfoByDoseNumber(doseNumber);

    return doseInfo.getDosage();
  }
}
