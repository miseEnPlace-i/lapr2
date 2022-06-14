package app.domain.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Vaccine administration model class.
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineAdministration implements Comparable<VaccineAdministration>, Serializable {
  private SNSUser snsUser;
  private Vaccine vaccine;
  private String lotNumber;
  private int doseNumber;
  private VaccinationCenter vaccinationCenter;
  private Calendar date;
  private AdverseReaction adverseReaction;

  /**
   * Constructor for VaccineAdministration.
   * 
   * @param snsUser the snsUser who got the vaccine
   * @param vaccine the vaccine that was administered
   * @param lotNumber the lot number of the vaccine
   * @param doseNumber the dose number of the vaccine
   * @param vaccinationCenter the vaccination center where the vaccine was administered
   * @param date the date the vaccine was administered
   */
  public VaccineAdministration(SNSUser snsUser, Vaccine vaccine, String lotNumber, int doseNumber, VaccinationCenter vaccinationCenter, Calendar date) {
    this.snsUser = snsUser;
    this.vaccine = vaccine;
    setLotNumber(lotNumber);
    setDoseNumber(doseNumber);
    this.vaccinationCenter = vaccinationCenter;
    this.date = date;
  }

  /**
   * Sets the lot number of the vaccine.
   * 
   * @param lotNumber the lot number of the vaccine
   */
  public void setLotNumber(String lotNumber) {
    if (lotNumber == null) throw new IllegalArgumentException("Lot number cannot be null");
    if (lotNumber.isEmpty()) throw new IllegalArgumentException("Lot number cannot be empty");

    if (!lotNumber.matches("^[A-Z0-9]{5}-[0-9]{2}$")) throw new IllegalArgumentException("Lot number is not valid");

    this.lotNumber = lotNumber;
  }

  /**
   * Sets the dose number of the vaccine.
   * 
   * @param doseNumber the dose number of the vaccine
   */
  public void setDoseNumber(int doseNumber) {
    if (doseNumber < 1) throw new IllegalArgumentException("Dose number cannot be less than 1");

    this.doseNumber = doseNumber;
  }

  /**
   * Gets the snsUser who got the vaccine.
   * 
   * @return the snsUser who got the vaccine
   */
  public SNSUser getSnsUser() {
    return snsUser;
  }

  /**
   * Gets the vaccination center were the vaccine was administered.
   * 
   * @return the vaccination center where the vaccine was administered
   */
  public VaccinationCenter getVaccinationCenter() {
    return vaccinationCenter;
  }

  /**
   * Gets the vaccine that was administered.
   * 
   * @return the vaccine that was administered
   */
  public Vaccine getVaccine() {
    return vaccine;
  }

  /**
   * Gets the dose number of the vaccine.
   * 
   * @return the dose number of the vaccine
   */
  public int getDoseNumber() {
    return doseNumber;
  }

  /**
   * Adds an adverse reaction to the vaccine administration.
   * 
   * @param adverseReaction the adverse reaction to add
   */
  public void addAdverseReaction(AdverseReaction adverseReaction) {
    this.adverseReaction = adverseReaction;
  }

  /**
   * Gets the adverse reaction of the vaccine administration.
   * 
   * @return the adverse reaction of the vaccine administration
   */
  public AdverseReaction getAdverseReaction() {
    return adverseReaction;
  }

  /**
   * Gets the vaccine administration date.
   * 
   * @return the vaccine administration date
   */
  public Calendar getDate() {
    return date;
  }

  /**
   * Checks if the vaccine administrated has the given vaccine type.
   * 
   * @param vaccineType the vaccine type to check
   * 
   * @return true if the vaccine administrated has the given vaccine type, false otherwise
   */
  public boolean vaccineHasVaccineType(VaccineType vaccineType) {
    return vaccine.getVacType().equals(vaccineType);
  }

  @Override
  public int compareTo(VaccineAdministration other) {
    return this.date.compareTo(other.date);
  }
}
