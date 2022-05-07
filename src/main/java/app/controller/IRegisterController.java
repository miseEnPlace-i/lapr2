package app.controller;

/**
 * Interface for all the controllers that register resources.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public interface IRegisterController {
  /**
   * Registers a resource.
   */
  public void save();

  /**
   * @return a readable representation of the data
   */
  public String stringifyData();
}
