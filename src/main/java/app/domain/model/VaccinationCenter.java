package app.domain.model;

import app.domain.model.list.AppointmentScheduleList;
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
  private WaitingRoom waitingRoom;
  private AppointmentScheduleList appointmentList;

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
    this.waitingRoom = new WaitingRoom();
    this.appointmentList = new AppointmentScheduleList();
  }

  /**
   * @return the name of the vaccination center
   */
  public String getName() {
    return name;
  }

  /**
   * @return the address of the vaccination center
   */
  public String getAddress() {
    return address;
  }

  /**
   * @return the phone of the vaccination center
   */
  public String getPhone() {
    return phoneNum;
  }

  /**
   * @return the fax of the vaccination center
   */
  public String getFax() {
    return faxNum;
  }

  /**
   * @return the web address of the vaccination center
   */
  public String getWebAddress() {
    return webAddress;
  }

  /**
   * @return the email of the vaccination center
   */
  public String getEmail() {
    return email;
  }

  /**
   * @return the maximum number of vaccines given per slot
   */
  public int getMaxVacSlot() {
    return maxVacSlot;
  }

  /**
   * @return the name of the coordinator of the vaccination center
   */
  public String getCoordinatorName() {
    return coordinator.name;
  }

  /**
   * @return the name of the coordinator of the vaccination center
   */
  public Employee getCoordinator() {
    return coordinator;
  }

  /**
   * Gets the opening hours.
   * 
   * @return the opening hours.
   */
  public String getOpeningHours() {
    return this.openingHours;
  }

  /**
   * Gets the closing hours.
   * 
   * @return the closing hours.
   */
  public String getClosingHours() {
    return this.closingHours;
  }

  /**
   * Gets the slot duration.
   * 
   * @return the slot duration.
   */
  public int getSlotDuration() {
    return this.slotDuration;
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
      throw new IllegalArgumentException("Name cannot be null or empty.");
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
      throw new IllegalArgumentException("Address cannot be null or empty.");
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
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email cannot be null or empty.");
    }
    if (!FormatVerifier.validateEmail(email)) {
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
    if (phoneNum == null || phoneNum.isEmpty()) {
      throw new IllegalArgumentException("Phone number cannot be null or empty.");
    }
    if (!FormatVerifier.validatePhoneNumber(phoneNum)) {
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
    if (faxNum == null || faxNum.isEmpty()) {
      throw new IllegalArgumentException("Fax number cannot be null or empty.");
    }
    if (!FormatVerifier.validateFaxNumber(faxNum)) {
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
    if (webAddress == null || webAddress.isEmpty()) {
      throw new IllegalArgumentException("Website address cannot be null or empty.");
    }
    if (!FormatVerifier.validateURL(webAddress)) {
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

    if (openingHours == null || openingHours.isEmpty()) {
      throw new IllegalArgumentException("Opening hours cannot be null or empty.");
    }
    if (hours < 0 || hours > 24 || minutes < 0 || minutes > 60) {
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

    if (closingHours == null || closingHours.isEmpty()) {
      throw new IllegalArgumentException("Closing hours cannot be null or empty.");
    }
    if (hours < 0 || hours > 24 || minutes < 0 || minutes > 60) {
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
      throw new IllegalArgumentException("Slot duration is not valid. Enter a positive number.");
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
      throw new IllegalArgumentException(
          "Maximum number of vaccines per slot is not valid. Enter a positive number.");
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

  /**
   * @return true if the center phone is the same as the given phone number, false otherwise.
   */
  public boolean hasPhone(String phone) {
    return phoneNum.equals(phone);
  }

  public WaitingRoom getWaitingRoom() {
    return waitingRoom;
  }

  public AppointmentScheduleList getAppointmentList() {
    return appointmentList;
  }

  /**
   * Shows all vaccination center data
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Vaccination Center data:\n");
    sb.append(String.format("Name: %s\n", this.name));
    sb.append(String.format("Address: %s\n", this.address));
    sb.append(String.format("Email: %s\n", this.email));
    sb.append(String.format("Phone number: %s\n", this.phoneNum));
    sb.append(String.format("Fax number: %s\n", this.faxNum));
    sb.append(String.format("Web address: %s\n", this.webAddress));
    sb.append(String.format("Opening hours: %s\n", this.openingHours));
    sb.append(String.format("Closing hours: %s\n", this.closingHours));
    sb.append(String.format("Slot duration: %s\n", this.slotDuration));
    sb.append(String.format("Maximum vaccines per slot: %s\n", this.maxVacSlot));
    sb.append(String.format("Coordinator: %s\n", this.coordinator.getName()));

    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    VaccinationCenter center = (VaccinationCenter) obj;
    if (obj == null) return false;
    if (obj == this) return false;

    // For now, only email, phone number, fax number should be unique for each Center
    if (this.email.equals(center.email)) return true;
    if (this.phoneNum.equals(center.phoneNum)) return true;
    if (this.faxNum.equals(center.faxNum)) return true;

    return false;
  }
}
