package app.exception;

/**
 * Exception to be thrown when an employee is not authorized to perform some action
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class NotAuthorizedException extends Exception {
  public NotAuthorizedException() {
    super();
  }

  public NotAuthorizedException(String message) {
    super(message);
  }
}
