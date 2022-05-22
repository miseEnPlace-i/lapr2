package app.domain.model;

public class HealthCareCenter extends VaccinationCenter {

  private String ages;
  private String ars;

  public HealthCareCenter(String name, String address, String email, String phoneNum, String faxNum,
      String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot,
      Employee coordinator, String ages, String ars) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours,
        slotDuration, maxVacSlot, coordinator);

    setAges(ages);
    setArs(ars);
  }

  private void setAges(String ages) {
    if (ages == null || ages.isEmpty()) {
      throw new IllegalArgumentException("AGES not valid.");
    }
    this.ages = ages;
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
    sb.append(String.format("AGES: %s\n", this.ages));
    sb.append(String.format("ARS: %s\n", this.ars));

    return sb.toString();
  }

}
