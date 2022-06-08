package app.dto;

import app.domain.model.VaccineType;

/**
 * Vaccine DTO
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineDTO {
  private String designation;
  private String brand;
  private String id;
  private VaccineType vacType;

  /**
   * Constructor for VaccineDTO
   * 
   * @param designation the vaccine designation
   * @param brand the vaccine brand
   * @param id the vaccine id
   */
  public VaccineDTO(String designation, String brand, String id, VaccineType vacType) {
    this.designation = designation;
    this.brand = brand;
    this.id = id;
    this.vacType = vacType;
  }

  /**
   * Get the vaccine designation
   * 
   * @return the vaccine designation
   */
  public String getDesignation() {
    return this.designation;
  }

  /**
   * Get the vaccine brand
   * 
   * @return the vaccine brand
   */
  public String getBrand() {
    return this.brand;
  }

  /**
   * Get the vaccine id
   * 
   * @return the vaccine id
   */
  public String getId() {
    return this.id;
  }

  /**
   * Get the vaccine type
   * 
   * @return the vaccine type
   */
  public VaccineType getVacType() {
    return this.vacType;
  }
}
