package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;

/**
 * Vaccination Center store
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */

public class VaccinationCenterStore {

  // Vaccination Centers list
  private List<VaccinationCenter> vacCenters;

  /**
   * Constructor for VaccinationCenterStore
   */

  public VaccinationCenterStore() {
    this.vacCenters = new ArrayList<VaccinationCenter>();
  }

  /**
   * Creates an Vaccination Center instance.
   * 
   * @param name the vaccination center name
   * @param address the vaccination center address
   * @param emailAddress the vaccination center email address
   * @param phoneNum the vaccination center phone number
   * @param faxNum the vaccination center fax number
   * @param webAddress the vaccination center website address
   * @param openingHours the vaccination center opening hours
   * @param closingHours the vaccination center closing hours
   * @param slotDuration the vaccination center slot duration
   * @param maxVacSlot the vaccination center maximum vaccines per slot
   * @param coordinator the vaccination center coordinator
   * @return VaccinationCenter
   */

  public VaccinationCenter createVaccinationCenter(String name, String address, String emailAddress, String phoneNum, String faxNum, String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot,
      Employee coordinator) {

    VaccinationCenter center = new VaccinationCenter(name, address, emailAddress, phoneNum, faxNum, webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);
    return center;
  }

  /**
   * Validates a Vaccination Center and calls the method checkDuplicates.
   * 
   * @param center the vaccination center
   */

  public void validateVaccinationCenter(VaccinationCenter center) {
    if (center == null) {
      throw new Error("\nVaccination center is invalid.");
    }
    checkDuplicates(center);
  }

  /**
   * Check for duplicates.
   * 
   * @param center the vaccination center
   */

  public void checkDuplicates(VaccinationCenter center) {
    if (vacCenters.contains(center)) {
      throw new IllegalArgumentException("\nDuplicated Vaccination Center.");
    }
  }

  /**
   * Saves a Vaccination Center.
   * 
   * @param center the vaccination center
   */
  public void saveVaccinationCenter(VaccinationCenter center) {
    vacCenters.add(center);
  }
}
