package app.domain.model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.VaccineType;
import app.dto.VaccineTypeDTO;
import app.mapper.VaccineTypeMapper;


/**
 * Vaccine type store
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccineTypeStore implements Serializable {
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
   * Gets the a vaccine type by code
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

  /**
   * Gets list
   * 
   * @return list of vaccine types
   */
  public List<VaccineType> getList() {
    return vaccineTypes;
  }

  /**
   * Gets list
   * 
   * @return list of vaccine types
   */
  public List<VaccineTypeDTO> getVaccineTypes() {
    return VaccineTypeMapper.toDto(vaccineTypes);
  }

  /**
   * Adds a new vaccine type
   * 
   * @param code the vaccine type code
   * @param description the vaccine type description
   * @param technology the vaccine type technology
   * @return the new vaccine type
   */
  public VaccineType addVaccineType(String code, String description, String technology) {
    if (!vaccineTechnologyStore.existsType(technology)) throw new IllegalArgumentException("Technology must exist.");

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

  /**
   * Asks for the size of the List types
   * 
   * @return number of vaccine types registered in the system
   */
  public int size() {
    return vaccineTypes.size();
  }
}
