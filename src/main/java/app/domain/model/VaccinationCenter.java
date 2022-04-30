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
    private String email = "";
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

    public VaccinationCenter(String name, String address, String email, int phoneNum, int faxNum,
            String webAddress, String openingHours, String closingHours, int slotDuration,
            int maxVacSlot, Employee coordinator) {

        validateCenterName(name);
        validateAddress(address);
        validateEmail(email);
        validatePhoneNumber(String.valueOf(phoneNum));
        validateFaxNumber(String.valueOf(faxNum));
        validateWebsite(webAddress);
        validateOpeningHours(openingHours);
        validateClosingHours(closingHours);
        validateSlotDuration(slotDuration);
        validateMaxVacPerSlot(maxVacSlot);
        validateCoordinator(coordinator);


        this.name = name;
        this.address = address;
        this.email = email;
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
        return email;
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

    private static void validateCenterName(String name) {
        if (!name.matches("^.+$")) {
            throw new IllegalArgumentException("Name not valid");
        }
    }

    private static void validateAddress(String address) {
        // should not be empty
        // regex: ^.+$
        if (!address.matches("^.+$")) {
            throw new IllegalArgumentException("Address is not valid");
        }
    }

    private static void validateEmail(String email) {
        // regex:
        // ^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$
        if (!email.matches(
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        // should have a + prefix
        // regex +\d{3} \d{9}
        if (!phoneNumber.matches("^\\+\\d{3} \\d{9}$")) {
            throw new IllegalArgumentException("Phone Number is not valid");
        }
    }

    private static void validateFaxNumber(String faxNumber) {
        // maximum 12 char (+351 (3) - area code (2) - local number (6 to 7))
        // should have a prefix +351
        if (!faxNumber.matches("^\\+\\d{351} \\d{9}$") && faxNumber.length() < 12
                || faxNumber.length() < 11) {
            throw new IllegalArgumentException("Fax number is not valid");
        }
    }

    private static void validateWebsite(String website) {
        // TODO
    }

    private static void validateOpeningHours(String openHours) {
        // TODO
    }

    private static void validateClosingHours(String closHours) {
        // TODO
    }

    private static void validateSlotDuration(int slotDur) {
        // TODO
    }

    private static void validateMaxVacPerSlot(int maxVac) {
        // TODO
    }

    private static void validateCoordinator(Employee coordEmployee) {
        // TODO
    }
}
