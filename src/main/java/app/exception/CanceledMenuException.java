package app.exception;

/**
 * Canceled menu option in menu selected exception.
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class CanceledMenuException extends Exception {
  public CanceledMenuException() {
    super();
  }

  public CanceledMenuException(String message) {
    super(message);
  }
}
