package app.domain.model.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import app.domain.model.CenterEvent;
import app.domain.model.SNSUser;
import app.domain.shared.CenterEventType;

public class CenterEventList implements Iterable<CenterEvent>, Serializable {
  private List<CenterEvent> events;

  public CenterEventList() {
    this.events = new ArrayList<CenterEvent>();
  }

  @Override
  public Iterator<CenterEvent> iterator() {
    return events.iterator();
  }

  /**
   * 
   * @param i the index of the event to be returned
   * @return the event at the given index
   */
  public CenterEvent get(int i) {
    return events.get(i);
  }

  /**
   * 
   * @param date the date of the event to be returned
   * @param eventType the type of the event to be returned
   * @param snsUser the SNSUser of the event to be returned
   * @return the event at the given date and type and SNSUser
   */
  public CenterEvent create(Calendar date, CenterEventType eventType, SNSUser snsUser) {
    CenterEvent centerEvent = new CenterEvent(date, eventType, snsUser);
    return centerEvent;
  }

  /**
   * Adds a new event to the list
   * 
   * @param event the event to be added to the list
   */
  public void save(CenterEvent event) {
    this.events.add(event);
  }

  /**
   * 
   * @param day the day of the events to be returned
   * @return the list of events at the given day
   */
  public CenterEventList getEventListForDay(Calendar day) {
    CenterEventList eventsInDay = new CenterEventList();

    for (CenterEvent event : this.events)
      if (event.isInDay(day)) eventsInDay.save(event);

    return eventsInDay;
  }

  /**
   * 
   * @return the number of events in the list
   */
  public int size() {
    return this.events.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (CenterEvent centerEvent : events) {
      sb.append(centerEvent.toString());
      sb.append("\n");
    }

    return sb.toString();
  }
}
