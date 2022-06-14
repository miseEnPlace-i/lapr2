package app.domain.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.list.CenterEventList;
import app.service.FormatVerifier;
import app.utils.Time;

/**
 * Vaccination Center super class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public abstract class VaccinationCenter implements Serializable {
  private String name;
  private String address;
  private String email;
  private String phoneNum;
  private String faxNum;
  private String webAddress;
  private Time openingHours;
  private Time closingHours;
  private Slot slot;
  private Employee coordinator;
  private WaitingRoom waitingRoom;
  private RecoveryRoom recoveryRoom;
  private AppointmentScheduleList appointmentList;
  private CenterEventList eventList;
  private List<VaccineAdministration> vaccineAdministrationList;

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
   * @param maxVaccinesPerSlot the vaccination center maximum vaccines per slot
   * @param coordinator the vaccination center coordinator
   */
  public VaccinationCenter(String name, String address, String email, String phoneNum, String faxNum, String webAddress, Time openingHours, Time closingHours,
      Slot slot, Employee coordinator) {
    setName(name);
    setAddress(address);
    setEmail(email);
    setPhoneNum(phoneNum);
    setFaxNum(faxNum);
    setWebAddress(webAddress);
    setOpeningHours(openingHours);
    setClosingHours(closingHours);
    setSlot(slot);
    setCoordinator(coordinator);
    this.waitingRoom = new WaitingRoom();
    this.appointmentList = new AppointmentScheduleList(this);
    this.eventList = new CenterEventList();
    this.vaccineAdministrationList = new ArrayList<VaccineAdministration>();
    this.recoveryRoom = new RecoveryRoom();
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
    return slot.getMaxVaccinesPerSlot();
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
  public Time getOpeningHours() {
    return this.openingHours;
  }

  /**
   * Gets the closing hours.
   * 
   * @return the closing hours.
   */
  public Time getClosingHours() {
    return this.closingHours;
  }

  /**
   * Gets the slot duration.
   * 
   * @return the slot duration.
   */
  public int getSlotDuration() {
    return this.slot.getDuration();
  }

  /**
   * Gets the events.
   * 
   */
  public CenterEventList getEvents() {
    return this.eventList;
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
    if (phoneNum == null || phoneNum.isEmpty()) throw new IllegalArgumentException("Phone number cannot be null or empty.");

    if (!FormatVerifier.validatePhoneNumber(phoneNum)) throw new IllegalArgumentException("Phone number is not valid.");

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
    if (faxNum == null || faxNum.isEmpty()) throw new IllegalArgumentException("Fax number cannot be null or empty.");

    if (!FormatVerifier.validateFaxNumber(faxNum)) throw new IllegalArgumentException("Fax number is not valid.");

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
    if (webAddress == null || webAddress.isEmpty()) throw new IllegalArgumentException("Website address cannot be null or empty.");

    if (!FormatVerifier.validateURL(webAddress)) throw new IllegalArgumentException("Website address is not valid.");

    this.webAddress = webAddress;
  }

  /**
   * Sets the center opening hours.
   * 
   * @param openingHours the vaccination center opening hours.
   * 
   * @throws IllegalArgumentException if the opening hours are null, empty or not valid.
   */
  private void setOpeningHours(Time openingHours) {
    this.openingHours = openingHours;
  }

  /**
   * Sets the center closing hours.
   * 
   * @param closingHours the vaccination center closing hours.
   * @throws ParseException
   * 
   * @throws IllegalArgumentException if the closing hours are null, empty or not valid.
   */
  private void setClosingHours(Time closingHours) {
    if (!closingHours.isAfter(openingHours)) throw new IllegalArgumentException("Closing hours must be after the opening hours.");

    this.closingHours = closingHours;
  }

  /**
   * Sets the center slot duration.
   * 
   * @param slotDuration the vaccination center slot duration.
   * 
   * @throws IllegalArgumentException if the slot duration is null or not valid.
   */
  private void setSlot(Slot slot) {
    this.slot = slot;
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

  public RecoveryRoom getRecoveryRoom() {
    return recoveryRoom;
  }

  public List<VaccineAdministration> getVaccineAdministrations() {
    return vaccineAdministrationList;
  }

  public AppointmentScheduleList getAppointmentList() {
    return appointmentList;
  }

  public CenterPerformance getCenterPerformanceForDay(Calendar day, int interval) {
    CenterEventList events = this.eventList.getEventListForDay(day);

    if (events.size() == 0) return null;

    CenterPerformance centerPerformance = new CenterPerformance(events, interval, openingHours, closingHours);

    return centerPerformance;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(String.format("\nName: %s\n", this.getName()));
    sb.append(String.format("Address: %s\n", this.getAddress()));
    sb.append(String.format("Email: %s\n", this.getEmail()));
    sb.append(String.format("Phone number: %s\n", this.getPhone()));
    sb.append(String.format("Fax number: %s\n", this.getFax()));
    sb.append(String.format("Web address: %s\n", this.getWebAddress()));
    sb.append(String.format("Opening hours: %s\n", this.getOpeningHours()));
    sb.append(String.format("Closing hours: %s\n", this.getClosingHours()));
    sb.append(String.format("Slot duration: %s\n", this.getSlotDuration()));
    sb.append(String.format("Maximum vaccines per slot: %s\n", this.getMaxVacSlot()));
    sb.append(String.format("Coordinator: %s\n", this.getCoordinatorName()));

    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    VaccinationCenter center = (VaccinationCenter) obj;
    if (obj == null) return false;

    // name, email & phone number should be unique for each Center
    if (this.name.equals(center.name)) return true;
    if (this.email.equals(center.email)) return true;
    if (this.phoneNum.equals(center.phoneNum)) return true;

    return false;
  }


  /**
   * Checks if the center is open at a given time.
   * 
   * @param hours time (HH:mm) to check
   * @return true if center is open, false otherwise
   */
  public boolean isOpenAt(Time hours) {
    return hours.isBetween(openingHours, getRealClosingHours());
  }

  /**
   * Get the real closing hours of the center. The real closing hours represent the maximum schedule the staff can stay in
   * the center.
   * 
   * It is calculated adding the closing hours of the center with the slot duration.
   */
  public Time getRealClosingHours() {
    int openingMinutesOfDay = openingHours.convertToMinutes();
    int realClosingMinutesOfDay = openingMinutesOfDay + (appointmentList.getNOfSlotsPerDay() * slot.getDuration());

    realClosingMinutesOfDay--;

    int hours = realClosingMinutesOfDay / 60;
    int minutes = realClosingMinutesOfDay % 60;

    Time realClosingHours = new Time(hours, minutes);

    return realClosingHours;
  }

  public boolean hasAvailabilityInSlot(Calendar date) {
    return appointmentList.checkSlotAvailability(date);
  }

  /**
   * Adds a Vaccine Administration object to the list.
   * 
   * @param vaccineAdministration the Vaccine Administration object to be added.
   */
  public void addVaccineAdministrationToList(VaccineAdministration vaccineAdministration) {
    this.vaccineAdministrationList.add(vaccineAdministration);
  }
}
