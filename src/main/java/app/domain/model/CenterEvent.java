package app.domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import app.domain.shared.CenterEventType;

public class CenterEvent implements Serializable {
  private Calendar date;
  private CenterEventType eventType;
  private SNSUser snsUser;

  public CenterEvent(Calendar date, CenterEventType eventType, SNSUser snsUser) {
    this.date = date;
    this.eventType = eventType;
    this.snsUser = snsUser;
  }

  /**
   * 
   * @param day The day of the event.
   * @return true if the event is on the given day, false otherwise.
   */
  public boolean isInDay(Calendar day) {
    return this.date.get(Calendar.DAY_OF_YEAR) == day.get(Calendar.DAY_OF_YEAR);
  }

  /**
   * 
   * @return The date of the event.
   */
  public Calendar getDate() {
    return date;
  }

  /**
   * 
   * @param type The type of the event.
   * @return true if the event is of the given type, false otherwise.
   */
  public boolean isType(CenterEventType type) {
    return type.equals(eventType);
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    return "CenterEvent [date=" + sdf.format(date.getTime()) + ", eventType=" + eventType.toString() + ", snsUser=" + snsUser.getName() + "]";
  }
}
