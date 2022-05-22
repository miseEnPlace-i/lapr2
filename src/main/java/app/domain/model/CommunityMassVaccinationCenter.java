package app.domain.model;

public class CommunityMassVaccinationCenter extends VaccinationCenter {


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
