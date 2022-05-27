package app.domain.model;

import java.util.Calendar;

/**
 * Arrival class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Arrival {
  private SNSUser snsUser;
  private Appointment appointment;
  private Calendar arrivalTime;
  private Calendar departureTime;

  /**
   * Constructor for Arrival.
   */
  public Arrival(SNSUser snsUser, Appointment appointment) {
    this.snsUser = snsUser;
    this.appointment = appointment;
    this.arrivalTime = Calendar.getInstance();
  }

  /**
   * Gets the snsNumber.
   */
  public SNSUser getSNSUser() {
    return this.snsUser;
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
    return "Arrival: " + this.snsUser.getSnsNumber() + " @ " + this.arrivalTime.getTime();
  }
}
