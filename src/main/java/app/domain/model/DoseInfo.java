package app.domain.model;

import java.io.Serializable;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class DoseInfo implements Serializable {
  private int dosage;
  private int timeSinceLastDose;

  public DoseInfo(int dosage, int timeSinceLastDose) {
    setDosage(dosage);
    setTimeSinceLastDose(timeSinceLastDose);
  }

  public int getDosage() {
    return this.dosage;
  }

  public void setDosage(int dosage) {
    validateDosage(dosage);
    this.dosage = dosage;
  }

  public int getTimeSinceLastDose() {
    return this.timeSinceLastDose;
  }

  public void setTimeSinceLastDose(int timeSinceLastDose) {
    validateTimeSinceLastDose(timeSinceLastDose);
    this.timeSinceLastDose = timeSinceLastDose;
  }

  public void validateDosage(int dosage) {
    if (dosage <= 0) throw new IllegalArgumentException("The dosage must be positive.");
  }

  public void validateTimeSinceLastDose(int timeSinceLastDose) {
    if (timeSinceLastDose < 0) throw new IllegalArgumentException("The time since last dose must be positive.");
  }

  @Override
  public String toString() {
    return "Dosage: " + this.dosage + "\nTime since last dose: " + this.timeSinceLastDose;
  }
}
