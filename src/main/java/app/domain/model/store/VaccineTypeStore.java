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
  private List<VaccineType> vaccineTypes;
  private VaccineTechnologyStore vaccineTechnologyStore;

  /**
   * Constructor for VaccineTypeStore
   */
  public VaccineTypeStore(VaccineTechnologyStore technologyStore) {
    this.vaccineTypes = new ArrayList<VaccineType>();
    this.vaccineTechnologyStore = technologyStore;
  }

  /**
   * 
   * @param vaccineTypeCode the vaccine type code
   * @return vaccine type with a given Code
   */
  public VaccineType getVaccineTypeByCode(String code) {
    if (code == null) throw new IllegalArgumentException("Vaccine Type Code cannot be null");

    for (VaccineType vaccineType : vaccineTypes)
      if (vaccineType.hasCode(code)) return vaccineType;

    return null;
  }

  public List<VaccineType> getList() {
    return vaccineTypes;
  }

  public VaccineType addVaccineType(String code, String description, String technology) {
    if (!vaccineTechnologyStore.existsType(technology))
      throw new IllegalArgumentException("Technology must exist.");

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
