package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.VaccineType;


/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccineTypeStore {
  private List<VaccineType> vaccineTypes;

  public VaccineTypeStore() {
    this.vaccineTypes = new ArrayList<VaccineType>();
  }

  public VaccineType getVaccineTypeById(String vaccineTypeId) {
    // TODO
    return null;
  }

  public VaccineType addVaccineType(String code, String description, String technology) {
    VaccineType vaccineType = new VaccineType(code, description, technology);

    return vaccineType;
  }

  public boolean saveVaccineType(VaccineType vt) {
    return vaccineTypes.add(vt);
  }
}
