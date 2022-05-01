package app.domain.model;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

/**
 * Vaccination Center class
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
     * Constructor for Vaccination Center
     * 
     * @param name         the vaccination center name
     * @param address      the vaccination center address
     * @param emailAddress the vaccination center email address
     * @param phoneNum     the vaccination center phone number
     * @param faxNum       the vaccination center fax number
     * @param webAddress   the vaccination center web address
     * @param openingHours the vaccination center opening hours
     * @param closingHours the vaccination center closing hours
     * @param slotDuration the vaccination center slot duration
     * @param maxVacSlot   the vaccination center maximum vaccines per slot
     */

    public VaccinationCenter(String name, String address, String email, String phoneNum, String faxNum,
            String webAddress, String openingHours, String closingHours, int slotDuration,
            int maxVacSlot, Employee coordinator) {

        validateCenterName(name);
        validateAddress(address);
        validateEmail(email);
        validatePhoneNumber(phoneNum);
        validateFaxNumber(faxNum);
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

    public VaccinationCenter(Object name, Object address, Object email, Object phoneNum, Object faxNum,
            Object webAddress, Object openingHours, Object closingHours, Object slotDur, Object maxVac,
            Object coordinator) {

        validateCenterName((String) (name));
        validateAddress((String) (address));
        validateEmail((String) (email));
        validatePhoneNumber((String) (phoneNum));
        validateFaxNumber((String) (faxNum));
        validateWebsite((String) webAddress);
        validateOpeningHours((String) openingHours);
        validateClosingHours((String) closingHours);
        validateSlotDuration(slotDuration);
        validateMaxVacPerSlot(maxVacSlot);
        validateCoordinator((Employee) coordinator);

        this.name = (String) name;
        this.address = (String) address;
        this.email = (String) email;
        this.phoneNum = (String) phoneNum;
        this.faxNum = (String) faxNum;
        this.webAddress = (String) webAddress;
        this.openingHours = (String) openingHours;
        this.closingHours = (String) closingHours;
        this.slotDuration = (int) slotDur;
        this.maxVacSlot = (int) maxVac;
        this.coordinator = (Employee) coordinator;
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
        if (name == null || !name.matches(".*\\S+.*")) {
            throw new IllegalArgumentException("Name not valid");
        }
    }

    private static void validateAddress(String address) {
        // should not be empty
        // regex: ^.+$
        if (address == null || !address.matches("^.+$")) {
            throw new IllegalArgumentException("Address is not valid");
        }
    }

    private static void validateEmail(String email) {
        // regex:
        // ^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$
        if (email == null || !email.matches(
                "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }

    private static void validatePhoneNumber(String phoneNumber) {
        // should have a + prefix
        // regex +\d{3} \d{9}
        if (phoneNumber == null || !phoneNumber.matches("^\\+\\d{3} \\d{9}$")) {
            throw new IllegalArgumentException("Phone Number is not valid");
        }
    }

    private static void validateFaxNumber(String faxNumber) {
        // maximum 12 char (+351 (3) - area code (2) - local number (6 to 7))
        // should have a prefix +351
        if (faxNumber == null || !faxNumber.matches("^\\+\\d{351} \\d{9}$") || faxNumber.length() < 12
                || faxNumber.length() < 11) {
            throw new IllegalArgumentException("Fax number is not valid");
        }
    }

    private static void validateWebsite(String website) {
        // TODO
        if (website == null) {
            throw new IllegalArgumentException("Website address invalid");
        }
    }

    private static void validateOpeningHours(String openHours) {
        // TODO
        if (openHours == null) {
            throw new IllegalArgumentException("Opening hours invalid");
        }
    }

    private static void validateClosingHours(String closHours) {
        // TODO
        if (closHours == null) {
            throw new IllegalArgumentException("Closing hours invalid");
        }
    }

    private static void validateSlotDuration(int slotDur) {
        // TODO

        if (slotDur == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateMaxVacPerSlot(int maxVac) {
        // TODO
        if (maxVac == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void validateCoordinator(Employee coordEmployee) {
        // TODO
        if (coordEmployee == null) {
            throw new IllegalArgumentException("Coordinator invalid");
        }
    }
}
