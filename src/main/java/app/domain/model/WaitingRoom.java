package app.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WaitingRoom implements Iterable<Arrive> {
  List<Arrive> waitingRoom = new ArrayList<Arrive>();

  @Override
  public Iterator<Arrive> iterator() {
    return waitingRoom.iterator();
  }

  public int size() {
    return waitingRoom.size();
  }
}
