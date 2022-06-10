package app.dto;

/**
 * Dosage Info DTO
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class DosageInfoDTO {
  private int doseNumber;
  private int dosage;

  /**
   * Constructor for DosageInfoDTO
   * 
   * @param doseNumber the dose number
   * @param dosage the dosage
   */
  public DosageInfoDTO(int doseNumber, int dosage) {
    this.doseNumber = doseNumber;
    this.dosage = dosage;
  }

  /**
   * Get the dose number
   * 
   * @return the dose number
   */
  public int getDoseNumber() {
    return this.doseNumber;
  }

  /**
   * Get the dosage
   * 
   * @return the dosage
   */
  public int getDosage() {
    return this.dosage;
  }
}
