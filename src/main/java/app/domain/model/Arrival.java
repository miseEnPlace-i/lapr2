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
  private Calendar arrivalTime;
  // TODO: leaveTime for statistics

  /**
   * Constructor for Arrival.
   */
  public Arrival(SNSUser snsUser) {
    this.snsUser = snsUser;
    this.arrivalTime = Calendar.getInstance();
  }

  /**
   * Gets the snsNumber.
   */
  public SNSUser getSNSUser() {
    return this.snsUser;
  }

  /**
   * Gets the date.
   */
  public Calendar getArrivalTime() {
    return arrivalTime;
  }

  /**
   * Returns the Arrival as a string
   */
  @Override
  public String toString() {
    return "Arrival: " + this.snsUser.getSnsNumber() + " @ " + this.arrivalTime.getTime();
  }
}
