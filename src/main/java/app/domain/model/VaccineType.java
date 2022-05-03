package app.domain.model;

import app.domain.shared.Constants;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */

public class VaccineType {
  private String code;
  private String description;
  private String technology;

  /**
   * Constructor for the vaccine type
   * 
   * @param code the vaccine type code
   * @param description the vaccine type description
   * @param technology the vaccine type technology
   */

  public VaccineType(String code, String description, String technology) {
    setCode(code);
    setDescription(description);
    setTechnology(technology);
  }

  /**
   * Sets the vaccine type code
   * 
   * @param code the vaccine type code
   * 
   * @throws IllegalArgumentException if the code is null, empty or not valid.
   */

  private void setCode(String code) {
    if (code == null || code.isEmpty() || code.length() != Constants.VACCINE_TYPE_CODE_LENGTH) throw new IllegalArgumentException("Vaccine type code must be " + Constants.VACCINE_TYPE_CODE_LENGTH + " characters long.");

    this.code = code;
  }

  /**
   * Sets the vaccine type description
   * 
   * @param description the vaccine type description
   * 
   * @throws IllegalArgumentException if the description is null, empty or not valid.
   */

  private void setDescription(String description) {
    if (description.isEmpty() || description == null) throw new IllegalArgumentException("Description is not valid.");

    this.description = description;
  }

  /**
   * Sets the vaccine type technology
   * 
   * @param technology the vaccine type technology
   * 
   * @throws IllegalArgumentException if the technology is null, empty or not valid.
   */

  private void setTechnology(String technology) {
    if (technology == null || technology.isEmpty() || !isVaccineTechnologyValid(technology)) throw new IllegalArgumentException("Invalid vaccine technology: " + technology);

    this.technology = technology;
  }

  /**
   * Checks if the vaccine technology is valid.
   * 
   * @param technology the vaccine type technology.
   * 
   * @return "true" if valid, "false" if invalid
   */

  private boolean isVaccineTechnologyValid(String technology) {
    for (Constants.VaccineTechnology vaccineTechnology : Constants.VaccineTechnology.values())
      if (vaccineTechnology.toString().equals(technology)) return true;

    return false;
  }

  /**
   * Checks if two vaccine types are the same
   * 
   * @return "true" if they are the same, "false" if they are different
   */

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof VaccineType)) return false;

    VaccineType other = (VaccineType) obj;

    return this.code.equals(other.code);
  }

  /**
   * Shows all the vaccine type specifications
   */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccine type specifications:\n");
    sb.append(String.format("Code: %s\n", this.code));
    sb.append(String.format("Description: %s\n", this.description));
    sb.append(String.format("Technology: %s\n", this.technology));

    return sb.toString();
  }
}
