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

  public VaccineType(String code, String description, String technology) {
    setCode(code);
    setDescription(description);
    setTechnology(technology);
  }

  private void setCode(String code) {
    if (code.length() != Constants.VACCINE_TYPE_CODE_LENGTH) throw new IllegalArgumentException("Vaccine type code must be " + Constants.VACCINE_TYPE_CODE_LENGTH + " characters long.");

    this.code = code;
  }

  private void setDescription(String description) {
    this.description = description;
  }

  private void setTechnology(String technology) {
    if (!isVaccineTechnologyValid(technology)) throw new IllegalArgumentException("Invalid vaccine technology: " + technology);

    this.technology = technology;
  }

  private boolean isVaccineTechnologyValid(String technology) {
    for (Constants.VaccineTechnology vaccineTechnology : Constants.VaccineTechnology.values())
      if (vaccineTechnology.toString().equals(technology)) return true;

    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof VaccineType)) return false;

    VaccineType other = (VaccineType) obj;

    return this.code.equals(other.code);
  }

  @Override
  public String toString() {
    // TODO
    return "";
  }
}
