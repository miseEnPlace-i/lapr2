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

  /**
   * Constructor for Arrival.
   */
  public Arrival(Appointment appointment, Calendar arrivalTime) {
    this.appointment = appointment;
    this.arrivalTime = arrivalTime;
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
   * Returns the Arrival as a string
   */
  @Override
  public String toString() {
    return "Arrival: " + this.getSNSUser().getSnsNumber() + " @ " + this.arrivalTime.getTime();
  }
}
