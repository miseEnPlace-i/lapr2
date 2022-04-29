package app.domain.model;

import java.security.Policy;

/**
 * Vaccination Center class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */

public class VaccinationCenter {
    private String name = "";
    private String address = "";
    private String emailAddress = "";
    private int phoneNum = 0;
    private int faxNum = 0;
    private String webAddress = "";
    private String openingHours = "";
    private String closingHours = "";
    private int slotDuration = 0;
    private int maxVacSlot = 0;
    private Employee coordinator;

    /**
     * Constructor for Vaccination Center
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
     */

    public VaccinationCenter(String name, String address, String emailAddress, int phoneNum,
            int faxNum, String webAddress, String openingHours, String closingHours,
            int slotDuration, int maxVacSlot, Employee coordinator) {
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        this.phoneNum = phoneNum;
        this.faxNum = faxNum;
        this.webAddress = webAddress;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.slotDuration = slotDuration;
        this.maxVacSlot = maxVacSlot;
        this.coordinator = coordinator;
    }

    public boolean equals(Object obj) {
        return false;
    }

    // Getters
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
     * @return String return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @return int return the phoneNum
     */
    public int getPhoneNum() {
        return phoneNum;
    }

    /**
     * @return int return the faxNum
     */
    public int getFaxNum() {
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
     * @return int return the slotDuration
     */
    public int getSlotDuration() {
        return slotDuration;
    }

    /**
     * @return int return the maxVacSlot
     */
    public int getMaxVacSlot() {
        return maxVacSlot;
    }

    public String toString() {
        // TO DO
        return "";
    }

    public Employee getCoordinator() {
        return null;
    }

    public static void validateCenterName(String name) {
        if (!name.matches("*[0-9]*.")) {
            throw new IllegalArgumentException("Name not valid");
        }
    }

}
