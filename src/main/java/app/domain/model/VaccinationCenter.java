package app.domain.model;

/**
 * Community Mass Vaccination Center class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */

public class VaccinationCenter {
  private String name = "";
  private String address = "";
  private String email = "";
  private String phoneNum = "";
  private String faxNum = "";
  private String webAddress = "";
  private String openingHours = "";
  private String closingHours = "";
  private int slotDuration = 0;
  private int maxVacSlot = 0;
  private Employee coordinator;

  /**
   * Constructor for the Community Mass Vaccination Center
   * 
   * @param name the vaccination center name
   * @param address the vaccination center address
   * @param emailAddress the vaccination center email address
   * @param phoneNum the vaccination center phone number
   * @param faxNum the vaccination center fax number
   * @param webAddress the vaccination center web address
   * @param openingHours the vaccination center opening hours
   * @param closingHours the vaccination center closing hours
   * @param slotDuration the vaccination center slot duration
   * @param maxVacSlot the vaccination center maximum vaccines per slot
   * @param coordinator the vaccination center coordinator
   */

  public VaccinationCenter(String name, String address, String email, String phoneNum, String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot, Employee coordinator) {

    setName(name);
    setAddress(address);
    setEmail(email);
    setPhoneNum(phoneNum);
    setFaxNum(faxNum);
    setWebAddress(webAddress);
    setOpeningHours(openingHours);
    setClosingHours(closingHours);
    setSlotDuration(slotDuration);
    setMaxVacSlot(maxVacSlot);
    setCoordinator(coordinator);
  }

  /**
   * @param name the name to set
   */
  private void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is not valid");
    }
    this.name = name;
  }

  /**
   * @param address the address to set
   */
  private void setAddress(String address) {
    if (address == null || address.isEmpty()) {
      throw new IllegalArgumentException("Address is not valid");
    }
    this.address = address;
  }

  /**
   * @param email the email to set
   */
  private void setEmail(String email) {
    if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
      throw new IllegalArgumentException("Email is not valid");
    }
    this.email = email;
  }

  /**
   * @param phoneNum the phoneNum to set
   */
  private void setPhoneNum(String phoneNum) {
    if (phoneNum == null || phoneNum.isEmpty() || !phoneNum.matches("^\\+\\d{3} \\d{9}$")) {
      throw new IllegalArgumentException("Phone number is not valid");
    }
    this.phoneNum = phoneNum;
  }

  /**
   * @param faxNum the faxNum to set
   */
  private void setFaxNum(String faxNum) {
    if (faxNum == null || faxNum.isEmpty() || !faxNum.matches("^\\+\\d{3} \\d{12}$")) {
      throw new IllegalArgumentException("Fax number is not valid");
    }
    this.faxNum = faxNum;
  }

  /**
   * @param webAddress the webAddress to set
   */
  private void setWebAddress(String webAddress) {
    if (webAddress == null || webAddress.isEmpty() || !webAddress.matches("^(https:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
      throw new IllegalArgumentException("Website address is not valid");
    }
    this.webAddress = webAddress;
  }

  /**
   * @param openingHours the openingHours to set
   */
  private void setOpeningHours(String openingHours) {
    if (openingHours == null || openingHours.isEmpty()) {
      throw new IllegalArgumentException("Opening hours is not valid");
    }
    this.openingHours = openingHours;
  }

  /**
   * @param closingHours the closingHours to set
   */
  private void setClosingHours(String closingHours) {
    if (closingHours == null || closingHours.isEmpty()) {
      throw new IllegalArgumentException("Closing hours is not valid");
    }
    this.closingHours = closingHours;
  }

  /**
   * @param slotDuration the slotDuration to set
   */
  private void setSlotDuration(int slotDuration) {
    if (slotDuration == 0) {
      throw new IllegalArgumentException("Slot duration is not valid");
    }
    this.slotDuration = slotDuration;
  }

  /**
   * @param maxVacSlot the maxVacSlot to set
   */
  private void setMaxVacSlot(int maxVacSlot) {
    if (maxVacSlot == 0) {
      throw new IllegalArgumentException("Maximum number of vaccines per slot is not valid");
    }
    this.maxVacSlot = maxVacSlot;
  }

  /**
   * @param coordinator the coordinator to set
   */
  private void setCoordinator(Employee coordinator) {
    if (coordinator == null) {
      throw new IllegalArgumentException("Coordinator is not valid");
    }
    this.coordinator = coordinator;
  }

}
