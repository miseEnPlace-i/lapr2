package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;

/**
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
   * @param name
   * @param address
   * @param emailAddress
   * @param phoneNum
   * @param faxNum
   * @param webAddress
   * @param openingHours
   * @param closingHours
   * @param slotDuration
   * @param maxVacSlot
   * @param coordinator
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
   * @param center
   */

  public void validateVaccinationCenter(VaccinationCenter center) {
    if (center == null) {
      throw new Error("Vaccination center is null");
    }
    checkDuplicates(center);
  }

  /**
   * Check for duplicates.
   * 
   * @param center
   */

  public void checkDuplicates(VaccinationCenter center) {
    if (vacCenters.contains(center)) {
      throw new IllegalArgumentException("Duplicate Vaccination Center");
    }
  }

  /**
   * Saves a Vaccination Center.
   * 
   * @param center
   */
  public void saveVaccinationCenter(VaccinationCenter center) {
    vacCenters.add(center);
  }
}
