package app.domain.model;

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
  public CommunityMassVaccinationCenter(String name, String address, String email, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator, VaccineType vaccineType) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours,
        slotDuration, maxVacSlot, coordinator);

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
    return vaccineType == this.vaccineType;
  }

  /**
   * Shows all community mass vaccination center data
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Community Mass Vaccination Center data:\n");
    sb.append(String.format("\nName: %s\n", super.getName()));
    sb.append(String.format("Address: %s\n", super.getAddress()));
    sb.append(String.format("Email: %s\n", super.getEmail()));
    sb.append(String.format("Phone number: %s\n", super.getPhone()));
    sb.append(String.format("Fax number: %s\n", super.getFax()));
    sb.append(String.format("Web address: %s\n", super.getWebAddress()));
    sb.append(String.format("Opening hours: %s\n", super.getOpeningHours()));
    sb.append(String.format("Closing hours: %s\n", super.getClosingHours()));
    sb.append(String.format("Slot duration: %s\n", super.getSlotDuration()));
    sb.append(String.format("Maximum vaccines per slot: %s\n", super.getMaxVacSlot()));
    sb.append(String.format("Coordinator: %s\n", super.getCoordinatorName()));
    sb.append(String.format("Vaccine type given on the center:\n\n%s", getVaccineType()));


    return sb.toString();
  }

}
