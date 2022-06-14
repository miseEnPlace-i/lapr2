package app.domain.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * 
 * 
 */
public class Administration implements Serializable {
  private Vaccine vaccine;
  private int doseNumber;
  private String lotNumber;
  private VaccinationCenter center;
  private Calendar administrationDate;

  public Administration(Vaccine vaccine, int doseNumber, String lotNumber, VaccinationCenter center, Calendar administrationDate) {
    this.vaccine = vaccine;
    this.doseNumber = doseNumber;
    this.lotNumber = lotNumber;
    this.center = center;
    this.administrationDate = administrationDate;
  }
}
