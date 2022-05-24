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
}
