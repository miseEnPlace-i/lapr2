package app.domain.model.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.CenterEvent;
import app.domain.model.SNSUser;
import app.domain.shared.CenterEventType;

public class CenterEventList {
  private List<CenterEvent> events;

  public CenterEventList() {
    this.events = new ArrayList<CenterEvent>();
  }

  public CenterEvent create(Calendar date, CenterEventType eventType, SNSUser snsUser) {
    CenterEvent centerEvent = new CenterEvent(date, eventType, snsUser);
    this.events.add(centerEvent);
    return centerEvent;
  }

  public void save(CenterEvent event) {
    this.events.add(event);
  }

  public List<CenterEvent> getEventListForDay(Calendar day) {
    List<CenterEvent> eventsInDay = new ArrayList<CenterEvent>();

    for (CenterEvent event : this.events)
      if (event.isInDay(day)) eventsInDay.add(event);

    return eventsInDay;
  }
}
