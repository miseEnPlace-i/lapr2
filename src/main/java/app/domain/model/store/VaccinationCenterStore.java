package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.shared.Constants;

/**
 * Vaccination Center store
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterStore {
  // Vaccination Centers list
  private List<VaccinationCenter> vaccinationCenters;

  /**
   * Constructor for VaccinationCenterStore
   */
  public VaccinationCenterStore() {
    this.vaccinationCenters = new ArrayList<VaccinationCenter>();
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
  public VaccinationCenter createCommunityMassCenter(String name, String address,
      String emailAddress, String phoneNum, String faxNum, String webAddress, String openingHours,
      String closingHours, int slotDuration, int maxVacSlot, Employee coordinator) {

    boolean isCoordinatorValid = validateCoordinator(coordinator);

    if (!isCoordinatorValid) return null;

    CommunityMassVaccinationCenter center =
        new CommunityMassVaccinationCenter(name, address, emailAddress, phoneNum, faxNum,
            webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator);

    return center;
  }

  public VaccinationCenter createHealthCareCenter(String name, String address, String emailAddress,
      String phoneNum, String faxNum, String webAddress, String openingHours, String closingHours,
      int slotDuration, int maxVacSlot, Employee coordinator, String ages, String ags) {

    boolean isCoordinatorValid = validateCoordinator(coordinator);

    if (!isCoordinatorValid) return null;

    HealthCareCenter center = new HealthCareCenter(name, address, emailAddress, phoneNum, faxNum,
        webAddress, openingHours, closingHours, slotDuration, maxVacSlot, coordinator, ages, ags);

    return center;
  }

  private boolean validateCoordinator(Employee coordinator) {
    if (coordinator == null) return false;
    if (!coordinator.hasRoleId(Constants.ROLE_COORDINATOR)) return false;

    for (VaccinationCenter vaccinationCenter : vaccinationCenters)
      if (vaccinationCenter.getCoordinatorName().equals(coordinator.getName())) return false;

    return true;
  }

  /**
   * Validates a Vaccination Center and calls the method checkDuplicates.
   * 
   * @param center the vaccination center
   */
  public void validateVaccinationCenter(VaccinationCenter center) {
    if (center == null) throw new IllegalArgumentException("\nVaccination center is not valid.");

    checkDuplicates(center);
  }

  /**
   * Check for duplicates.
   * 
   * @param center the vaccination center
   */
  private void checkDuplicates(VaccinationCenter center) {
    if (vaccinationCenters.contains(center))
      throw new IllegalArgumentException("\nDuplicated Vaccination Center.");
  }

  /**
   * Saves a Vaccination Center.
   * 
   * @param center the vaccination center
   */
  public void saveVaccinationCenter(VaccinationCenter center) {
    vaccinationCenters.add(center);
  }

  /**
   * Asks for the size of the List vacCenters
   * 
   * @return number of vaccination centers registered in the system
   */
  public int size() {
    return vaccinationCenters.size();
  }

  /**
   * @return all Vaccination Centers List
   */
  public List<VaccinationCenterListDTO> getVaccinationCenters() {
    List<VaccinationCenterListDTO> centers = new ArrayList<VaccinationCenterListDTO>();

    for (VaccinationCenter vaccinationCenter : vaccinationCenters) {
      VaccinationCenterListDTO vaccinationCenterDTO =
          new VaccinationCenterListDTO(vaccinationCenter.getName(), vaccinationCenter.getAddress(),
              vaccinationCenter.getEmail(), vaccinationCenter.getPhone());

      centers.add(vaccinationCenterDTO);
    }

    return centers;
  }

  public WaitingRoom getWaitingRoom(String phone) {
    return getVaccinationCenterByPhone(phone).getWaitingRoom();
  }

  private VaccinationCenter getVaccinationCenterByPhone(String phone) {
    for (VaccinationCenter center : vaccinationCenters) {
      if (center.getPhone().equals(phone)) {
        return center;
      }
    }
    return null;
  }

  public boolean exists(String phone) {
    // !! Refactor !!
    return !(getVaccinationCenterByPhone(phone) == null);
  }
}
