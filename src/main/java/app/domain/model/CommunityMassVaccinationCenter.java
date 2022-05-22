package app.domain.model;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class CommunityMassVaccinationCenter extends VaccinationCenter {


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
      int maxVacSlot, Employee coordinator) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours,
        slotDuration, maxVacSlot, coordinator);

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
    sb.append(String.format("Phone number: %s\n", super.getPhoneNum()));
    sb.append(String.format("Fax number: %s\n", super.getFaxNum()));
    sb.append(String.format("Web address: %s\n", super.getWebAddress()));
    sb.append(String.format("Opening hours: %s\n", super.getOpeningHours()));
    sb.append(String.format("Closing hours: %s\n", super.getClosingHours()));
    sb.append(String.format("Slot duration: %s\n", super.getSlotDuration()));
    sb.append(String.format("Maximum vaccines per slot: %s\n", super.getMaxVacSlot()));
    sb.append(String.format("Coordinator: %s\n", super.getCoordinatorName()));


    return sb.toString();
  }

}
