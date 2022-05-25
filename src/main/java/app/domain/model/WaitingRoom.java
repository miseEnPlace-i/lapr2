package app.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
  public Arrival createArrival(String snsNumber) {
    return new Arrival(snsNumber);
  }

  /**
   * Adds an Arrival to the Waiting Room.
   */
  public void saveArrival(Arrival arrival) {
    waitingRoom.add(arrival);
  }
}
