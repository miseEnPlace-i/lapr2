package app.domain.model;

import java.util.Calendar;

/**
 * Arrival class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Arrival {
  private Appointment appointment;
  private Calendar arrivalTime;
  private Calendar departureTime;

  /**
   * Constructor for Arrival.
   */
  public Arrival(Appointment appointment) {
    this.appointment = appointment;
    this.arrivalTime = Calendar.getInstance();
  }

  /**
   * Gets the snsNumber.
   */
  public SNSUser getSNSUser() {
    return this.appointment.getSnsUser();
  }

  /**
   * Gets the arrival time.
   */
  public Calendar getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Gets the appointment.
   */
  public Appointment getAppointment() {
    return appointment;
  }

  /**
   * Gets the departure time.
   */
  public Calendar getDepartureTime() {
    return departureTime;
  }

  /**
   * Sets the departure time.
   */
  public void setDepartureTime(Calendar departureTime) {
    this.departureTime = departureTime;
  }

  /**
   * Returns the Arrival as a string
   */
  @Override
  public String toString() {
    return "Arrival: " + this.getSNSUser().getSnsNumber() + " @ " + this.arrivalTime.getTime();
  }
}
