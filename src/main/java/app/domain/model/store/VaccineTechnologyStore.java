package app.domain.model.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccineTechnologyStore implements Serializable {
  // List of vaccine technology types
  private List<String> techTypes;

  /**
   * Constructor for VaccineTechnologyStore
   */
  public VaccineTechnologyStore() {
    this.techTypes = new ArrayList<String>();
  }

  /**
   * Adds to the list of types a new technology type
   * 
   * @param technology the vaccine technology type
   */
  public void addVaccineTechnology(String technology) {
    techTypes.add(technology);
  }

  /**
   * Gets all vaccine technology types
   * 
   * @return list of Vaccine technology types
   */
  public List<String> getList() {
    return techTypes;
  }

  /**
   * Sees if exists a certain type of technology
   * 
   * @param technology the vaccine technology to see if it matches any of those already in the system
   * @return "true" if valid, "false" if invalid
   */
  public boolean existsType(String technology) {
    return techTypes.contains(technology);
  }

  /**
   * Asks for the size of the List types
   * 
   * @return number of vaccine technology types registered in the system
   */
  public int size() {
    return techTypes.size();
  }
}
