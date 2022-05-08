package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccineTechnologyStore {
  private List<String> types;

  /**
   * Constructor for VaccineTechnologyStore
   */
  public VaccineTechnologyStore() {
    this.types = new ArrayList<String>();
  }

  /**
   * Adds to the list of types a new technology type
   * 
   * @param technology the vaccine technology type
   */
  public void addVaccineTechnology(String technology) {
    types.add(technology);
  }

  /**
   * Gets all vaccine technology types
   * 
   * @return list of Vaccine technology types
   */
  public List<String> getList() {
    return types;
  }

  /**
   * Sees if exists a certain type of technology
   * 
   * @param technology the vaccine technology to see if it matches any of those already in the system
   * @return "true" if valid, "false" if invalid
   */
  public boolean existsType(String technology) {
    return types.contains(technology);
  }

  /**
   * Asks for the size of the List types
   * 
   * @return number of vaccine technology types registered in the system
   */
  public int size() {
    return types.size();
  }
}
