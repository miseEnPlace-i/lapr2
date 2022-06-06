package app.exception;

/**
 * Not found exception.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class NotFoundException extends Exception {
  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }
}
