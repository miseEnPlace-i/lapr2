package app.domain.model;

public class MassCommunityVaccinationCenter extends VaccinationCenter {

  public MassCommunityVaccinationCenter(String name, String address, String email, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator) {

    super(name, address, email, phoneNum, faxNum, webAddress, openingHours, closingHours,
        slotDuration, maxVacSlot, coordinator);

  }

}
