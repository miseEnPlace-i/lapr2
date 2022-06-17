package app.domain.model;

import app.utils.Time;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class CommunityMassVaccinationCenter extends VaccinationCenter {


  private VaccineType vaccineType;

  /**
   * Constructor for community mass vaccination center
   * 
   * @param name the community mass vaccination center name
   * @param address the community mass vaccination center address
   * @param email the community mass vaccination center email address
   * @param phoneNum the community mass vaccination center phone number
   * @param faxNum the community mass vaccination center fax number
   * @param webAddress the community mass vaccination center website address
   * @param openingHours the community mass vaccination center opening hours
   * @param closingHours the community mass vaccination center closing hours
   * @param slotDuration the community mass vaccination center slot duration
   * @param maxVacSlot the community mass vaccination center maximum vaccines per slot
   * @param coordinator the community mass vaccination center coordinator
   */
  public CommunityMassVaccinationCenter(String name, Address address, String email, String phoneNum, String faxNum, String webAddress, Time openingHours,
      Time closingHours, Slot slot, Employee coordinator, VaccineType vaccineType) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours, slot, coordinator);

    setVaccineType(vaccineType);

  }

  private void setVaccineType(VaccineType vaccineType) {
    if (vaccineType == null) throw new IllegalArgumentException("Vaccine type not valid.");

    this.vaccineType = vaccineType;
  }

  /**
   * @return VaccineType return the vaccineType
   */
  public VaccineType getVaccineType() {
    return vaccineType;
  }

  /**
   * Checks if this center administers certain vaccine type.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return true if it administers, false otherwise
   */
  public boolean administersVaccineType(VaccineType vaccineType) {
    return this.vaccineType.equals(vaccineType);
  }

  /**
   * Shows all community mass vaccination center data
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Community Mass Vaccination Center data:\n");

    sb.append(super.toString());

    sb.append(String.format("Vaccine type given on the center:\n\n%s", getVaccineType()));

    return sb.toString();
  }

}
