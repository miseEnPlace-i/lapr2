package app.domain.model;

import java.util.Calendar;

/**
 * Appointment class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Arrival {
  private String snsNumber;
  private Calendar arrivalTime;
  // TODO: leaveTime

  /**
   * Constructor for Arrival.
   */
  public Arrival(String snsNumber) {
    this.snsNumber = snsNumber;
    this.arrivalTime = Calendar.getInstance();
  }

  /**
   * Gets the snsNumber.
   */
  public String getSnsNumber() {
    return snsNumber;
  }

  /**
   * Gets the date.
   */
  public Calendar getArrivalTime() {
    return arrivalTime;
  }

}
