package app.controller;

/**
 * Interface for all the controllers that register resources.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public interface IRegisterController<T> {
  /**
   * Registers a resource.
   */
  public void save();

  /**
   * 
   * @return The resource name
   */
  public String getResourceName();

  /**
   * @return a readable representation of the data
   */
  public String stringifyData();

  /**
   * @return the registered object
   */
  public T getRegisteredObject();
}
