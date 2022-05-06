package app.domain.model;

/**
 * Vaccination Center class
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
   * Sets the center name.
   * 
   * @param name the vaccination center name
   * 
   * @throws IllegalArgumentException if the name is null, empty or not valid.
   */
  private void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name is not valid");
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
      throw new IllegalArgumentException("Address is not valid");
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
    if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
      throw new IllegalArgumentException("Email is not valid");
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
    if (phoneNum == null || phoneNum.isEmpty() || !phoneNum.matches("\\+[0-9]{3}[0-9]{9}$")) {
      throw new IllegalArgumentException("Phone number is not valid");
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
    if (faxNum == null || faxNum.isEmpty() || !faxNum.matches("\\+[0-9]{1,3}-[0-9]{3}\\-[0-9]{7}")) {
      throw new IllegalArgumentException("Fax number is not valid");
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
    if (webAddress == null || webAddress.isEmpty() || !webAddress.matches("^(https:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
      throw new IllegalArgumentException("Website address is not valid");
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

    if (openingHours == null || openingHours.isEmpty() || hours < 0 || hours > 24 || minutes < 0 || minutes > 60) {
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

    if (closingHours == null || closingHours.isEmpty() || hours < 0 || hours > 24 || minutes < 0 || minutes > 60) {
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
      throw new IllegalArgumentException("Slot duration is not valid");
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
      throw new IllegalArgumentException("Maximum number of vaccines per slot is not valid");
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
      throw new IllegalArgumentException("Coordinator is not valid");
    }
    this.coordinator = coordinator;
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
}
