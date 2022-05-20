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


}
