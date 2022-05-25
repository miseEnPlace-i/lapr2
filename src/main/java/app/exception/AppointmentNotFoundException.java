package app.exception;

/**
 * Appointment not found exception.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class AppointmentNotFoundException extends Exception {
  public AppointmentNotFoundException() {
    super();
  }

  public AppointmentNotFoundException(String message) {
    super(message);
  }
}
