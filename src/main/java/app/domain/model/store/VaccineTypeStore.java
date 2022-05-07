package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.VaccineType;


/**
 * Vaccine type store
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccineTypeStore {
  private static List<VaccineType> vaccineTypes;
  private static VaccineType vaccineType;

  /**
   * Constructor for VaccineTypeStore
   */

  public VaccineTypeStore(VaccineType vaccineType) {
    this.vaccineTypes = new ArrayList<VaccineType>();
    this.vaccineType = vaccineType;
  }

  /**
   * 
   * @param vaccineTypeId
   * @return vaccine type with a given ID
   */

  public static VaccineType getVaccineTypeById(String vaccineTypeId) {
    // NEEDS REVISION
    if (vaccineTypeId == null) throw new IllegalArgumentException("Vaccine Type id cannot be null");
    if (!vaccineType.existsTypeId(vaccineTypeId))
      throw new IllegalArgumentException("Vaccine Type does not exist");
    boolean flag = false;
    List<VaccineType> lstVacType = new ArrayList<>();

    for (VaccineType vaccineType : vaccineTypes) {
      if (vaccineTypeId.equals(vaccineType) && !flag) {
        flag = true;
      }
    }
    return vaccineType;
  }

  public VaccineType getVacTypeById(String vacTypeId) {
    for (VaccineType vacT : vaccineTypes)
      if (vacT.getCode() == vacTypeId) return vacT;
    return null;
  }

  public List<VaccineType> getList() {
    return vaccineTypes;
  }


  public VaccineType addVaccineType(String code, String description, String technology) {
    VaccineType vaccineType = new VaccineType(code, description, technology);

    return vaccineType;
  }

  /**
   * Saves a vaccine type
   * 
   * @param vt the vaccine type
   */

  public void saveVaccineType(VaccineType vt) {
    this.vaccineTypes.add(vt);
  }
}
