package app.domain.model;

import app.service.FormatVerifier;

/**
 * Vaccination Center super class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenter {
  private String name;
  private String address;
  private String email;
  private String phoneNum;
  private String faxNum;
  private String webAddress;
  private String openingHours;
  private String closingHours;
  private int slotDuration;
  private int maxVacSlot;
  private Employee coordinator;

  /**
   * Constructor for the Vaccination Center
   * 
   * @param name the vaccination center name
   * @param address the vaccination center address
   * @param email the vaccination center email address
   * @param phoneNum the vaccination center phone number
   * @param faxNum the vaccination center fax number
   * @param webAddress the vaccination center web address
   * @param openingHours the vaccination center opening hours
   * @param closingHours the vaccination center closing hours
   * @param slotDuration the vaccination center slot duration
   * @param maxVacSlot the vaccination center maximum vaccines per slot
   * @param coordinator the vaccination center coordinator
   */
  public VaccinationCenter(String name, String address, String email, String phoneNum,
      String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration,
      int maxVacSlot, Employee coordinator) {

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
   * Sets the center name.
   * 
   * @param name the vaccination center name
   * 
   * @throws IllegalArgumentException if the name is null, empty or not valid.
   */
  private void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is not valid.");
    }
    this.name = name;
  }

  /**
   * Sets the center address
   * 
   * @param address the vaccination center address
   * 
   * @throws IllegalArgumentException if the address is null, empty or not valid.
   */
  private void setAddress(String address) {
    if (address == null || address.isEmpty()) {
      throw new IllegalArgumentException("Address is not valid.");
    }
    this.address = address;
  }

  /**
   * Sets the center email.
   * 
   * @param email the vaccination center email address
   * 
   * @throws IllegalArgumentException if the email address is null, empty or not valid.
   */
  private void setEmail(String email) {
    if (email == null || email.isEmpty() || !FormatVerifier.validateEmail(email)) {
      throw new IllegalArgumentException("Email is not valid.");
    }
    this.email = email;
  }

  /**
   * Sets the center phone number.
   * 
   * @param phoneNum the vaccination center phone number
   * 
   * @throws IllegalArgumentException if the phone number is null, empty or not valid.
   */
  private void setPhoneNum(String phoneNum) {
    if (phoneNum == null || phoneNum.isEmpty() || !FormatVerifier.validatePhoneNumber(phoneNum)) {
      throw new IllegalArgumentException("Phone number is not valid.");
    }
    this.phoneNum = phoneNum;
  }

  /**
   * Sets the center fax number.
   * 
   * @param faxNum the vaccination center fax number.
   * 
   * @throws IllegalArgumentException if the fax number is null, empty or not valid.
   */
  private void setFaxNum(String faxNum) {
    if (faxNum == null || faxNum.isEmpty() || !FormatVerifier.validateFaxNumber(faxNum)) {
      throw new IllegalArgumentException("Fax number is not valid.");
    }
    this.faxNum = faxNum;
  }

  /**
   * Sets the center website address.
   * 
   * @param webAddress the vaccination center website address.
   * 
   * @throws IllegalArgumentException if the website address is null, empty or not valid.
   */
  private void setWebAddress(String webAddress) {
    if (webAddress == null || webAddress.isEmpty() || !FormatVerifier.validateURL(webAddress)) {
      throw new IllegalArgumentException("Website address is not valid.");
    }
    this.webAddress = webAddress;
  }

  /**
   * Sets the center opening hours.
   * 
   * @param openingHours the vaccination center opening hours.
   * 
   * @throws IllegalArgumentException if the opening hours are null, empty or not valid.
   */
  private void setOpeningHours(String openingHours) {
    String[] openHours = openingHours.split(":");
    int hours = Integer.parseInt(openHours[0]);
    int minutes = Integer.parseInt(openHours[1]);

    if (openingHours == null || openingHours.isEmpty() || hours < 0 || hours > 24 || minutes < 0
        || minutes > 60) {
      throw new IllegalArgumentException("Opening hours is not valid.");
    }
    this.openingHours = openingHours;
  }

  /**
   * Sets the center closing hours.
   * 
   * @param closingHours the vaccination center closing hours.
   * 
   * @throws IllegalArgumentException if the closing hours are null, empty or not valid.
   */
  private void setClosingHours(String closingHours) {
    String[] closHours = closingHours.split(":");
    int hours = Integer.parseInt(closHours[0]);
    int minutes = Integer.parseInt(closHours[1]);

    if (closingHours == null || closingHours.isEmpty() || hours < 0 || hours > 24 || minutes < 0
        || minutes > 60) {
      throw new IllegalArgumentException("Closing hours is not valid.");
    }
    this.closingHours = closingHours;
  }

  /**
   * Sets the center slot duration.
   * 
   * @param slotDuration the vaccination center slot duration.
   * 
   * @throws IllegalArgumentException if the slot duration is null or not valid.
   */
  private void setSlotDuration(int slotDuration) {
    if (slotDuration <= 0) {
      throw new IllegalArgumentException("Slot duration is not valid.");
    }
    this.slotDuration = slotDuration;
  }

  /**
   * Sets the center maximum vaccines per slot.
   * 
   * @param maxVacSlot the vaccination center maximum vaccines per slot.
   * 
   * @throws IllegalArgumentException if the maximum vaccines per slot are null or not valid.
   */
  private void setMaxVacSlot(int maxVacSlot) {
    if (maxVacSlot <= 0) {
      throw new IllegalArgumentException("Maximum number of vaccines per slot is not valid.");
    }
    this.maxVacSlot = maxVacSlot;
  }

  /**
   * Sets the center coordinator.
   * 
   * @param coordinator the vaccination center coordinator.
   * 
   * @throws IllegalArgumentException if the coordinator is null or not valid
   */
  private void setCoordinator(Employee coordinator) {
    if (coordinator == null) {
      throw new IllegalArgumentException("Coordinator is not valid.");
    }
    this.coordinator = coordinator;
  }

  @Override
  public boolean equals(Object obj) {
    VaccinationCenter center = (VaccinationCenter) obj;
    if (obj == null) return false;
    if (obj == this) return false;

    // For now, only email, phone number, fax number and website address should be unique for each Center
    if (this.email.equals(center.email)) return true;
    if (this.phoneNum.equals(center.phoneNum)) return true;
    if (this.faxNum.equals(center.faxNum)) return true;
    if (this.webAddress.equals(center.webAddress)) return true;

    return false;
  }

  /**
   * @return String return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return String return the address
   */
  public String getAddress() {
    return address;
  }

  /**
   * @return String return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return String return the phoneNum
   */
  public String getPhoneNum() {
    return phoneNum;
  }

  /**
   * @return String return the faxNum
   */
  public String getFaxNum() {
    return faxNum;
  }

  /**
   * @return String return the webAddress
   */
  public String getWebAddress() {
    return webAddress;
  }

  /**
   * @return String return the openingHours
   */
  public String getOpeningHours() {
    return openingHours;
  }

  /**
   * @return String return the closingHours
   */
  public String getClosingHours() {
    return closingHours;
  }

  /**
   * @return int return the slot Duration
   */
  public int getSlotDuration() {
    return slotDuration;
  }

  /**
   * @return int return the maximum vaccines per slot
   */
  public int getMaxVacSlot() {
    return maxVacSlot;
  }

  /**
   * @return Employee return the coordinator name
   */
  public String getCoordinatorName() {
    return this.coordinator.name;
  }

}
