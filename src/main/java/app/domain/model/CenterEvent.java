package app.domain.model;

import java.util.Calendar;
import app.domain.shared.CenterEventType;

public class CenterEvent {
  private Calendar date;
  private CenterEventType eventType;
  private SNSUser snsUser;

  public CenterEvent(Calendar date, CenterEventType eventType, SNSUser snsUser) {
    this.date = date;
    this.eventType = eventType;
    this.snsUser = snsUser;
  }

  public boolean isInDay(Calendar day) {
    return this.date.get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR);
  }

  public Calendar getDate() {
    return date;
  }

  public boolean isType(CenterEventType type) {
    return type.equals(eventType);
  }
}
