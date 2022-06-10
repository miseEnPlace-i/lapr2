package app.domain.model;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Waiting Room class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class WaitingRoom implements Iterable<Arrival> {
  Queue<Arrival> waitingRoom;

  public WaitingRoom() {
    this.waitingRoom = new LinkedList<Arrival>();
  }

  @Override
  public Iterator<Arrival> iterator() {
    return waitingRoom.iterator();
  }

  public int size() {
    return waitingRoom.size();
  }

  /**
   * Creates an instance of Arrival.
   * 
   * @param snsNumber the SNS Number of the SNS User.
   * @return Arrival
   */
  public Arrival createArrival(Appointment appointment, Calendar arrivalDate) {
    return new Arrival(appointment, arrivalDate);
  }

  /**
   * Adds an Arrival to the Waiting Room.
   */
  public void saveArrival(Arrival arrival) {
    waitingRoom.add(arrival);
  }

  /**
   * Removes the last Arrival from the Waiting Room.
   */
  public void removeLastArrival() {
    waitingRoom.remove();
  }

  /**
   * Finds today's SNS User arrival.
   */
  public boolean hasSNSUserArrivedToday(SNSUser snsUser) {
    for (Arrival arrival : waitingRoom)
      if (arrival.getSNSUser().equals(snsUser)) return true;

    return false;
  }

  /**
   * Prints the waiting room as a string
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nWaiting Room:");

    for (Arrival arrival : waitingRoom)
      sb.append("\n\t" + arrival.toString());

    return sb.toString();
  }
}
