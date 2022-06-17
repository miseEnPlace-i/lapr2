package app.domain.model;

import app.utils.Time;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class HealthCareCenter extends VaccinationCenter {
  private String aces;
  private String ars;

  /**
   * Constructor for health care center
   * 
   * @param name the health care center name
   * @param address the health care center address
   * @param email the health care center email
   * @param phoneNum the health care center phone number
   * @param faxNum the health care center fax number
   * @param webAddress the health care center website address
   * @param openingHours the health care center opening hours
   * @param closingHours the health care center closing hours
   * @param slotDuration the health care center slot duration
   * @param maxVacSlot the health care center maximum vaccines per slot
   * @param coordinator the health care center coordinator
   * @param aces the health care center ages
   * @param ars the health care center ars
   */
  public HealthCareCenter(String name, Address address, String email, String phoneNum, String faxNum, String webAddress, Time openingHours, Time closingHours,
      Slot slot, Employee coordinator, String aces, String ars) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours, slot, coordinator);

    setAces(aces);
    setArs(ars);
  }

  private void setAces(String aces) {
    if (aces == null || aces.isEmpty()) throw new IllegalArgumentException("AGES not valid.");

    this.aces = aces;
  }

  private void setArs(String ars) {
    if (ars == null || ars.isEmpty()) {
      throw new IllegalArgumentException("ARS not valid.");
    }
    this.ars = ars;
  }


  /**
   * Shows all health care center data
   */
  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("Health Care Center data:\n");

    sb.append(super.toString());

    sb.append(String.format("ACES: %s\n", this.aces));
    sb.append(String.format("ARS: %s\n", this.ars));

    return sb.toString();
  }
}
