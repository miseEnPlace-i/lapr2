package app.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Waiting Room class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class WaitingRoom implements Iterable<Arrival> {
  List<Arrival> waitingRoom = new ArrayList<Arrival>();

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
  public Arrival createArrival(SNSUser snsUser, Appointment appointment) {
    return new Arrival(snsUser, appointment);
  }

  /**
   * Adds an Arrival to the Waiting Room.
   */
  public void saveArrival(Arrival arrival) {
    waitingRoom.add(arrival);
  }

  /**
   * Finds today's SNS User arrival.
   */
  public boolean hasSNSUserArrivedToday(SNSUser snsUser) {
    for (Arrival arrival : waitingRoom) {
      if (arrival.getSNSUser().equals(snsUser)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Prints the waiting room as a string
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nWaiting Room:");
    for (Arrival arrival : waitingRoom) {
      sb.append("\n\t" + arrival.toString());
    }
    return sb.toString();
  }

}
