package app.utils;

import java.util.Calendar;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class Time {
  private int hours;
  private int minutes;

  public static Time getNow() {
    Time time = new Time(Calendar.getInstance());

    return time;
  }

  public Time(Calendar date) {
    if (date == null) throw new IllegalArgumentException("Date cannot be null");

    setHours(date.get(Calendar.HOUR_OF_DAY));
    setMinutes(date.get(Calendar.MINUTE));
  }

  public Time(int hours, int minutes) {
    setHours(hours);
    setMinutes(minutes);
  }

  /**
   * Instantiates a new time for a given string in the format HH:mm
   * 
   * @param time the time string in the format HH:mm
   */
  public Time(String time) {
    if (time == null) throw new IllegalArgumentException("Date cannot be null");

    String[] timeString = time.trim().split(":");

    if (timeString.length != 2) throw new IllegalArgumentException("Invalid time format");
    if (timeString == null || timeString[0].isEmpty() || timeString[1].isEmpty()) throw new IllegalArgumentException("Time cannot be null or empty.");

    int hours = Integer.parseInt(timeString[0]);
    int minutes = Integer.parseInt(timeString[1]);

    setHours(hours);
    setMinutes(minutes);
  }

  /**
   * @return the hours of the time
   */
  public int getHours() {
    return hours;
  }

  /**
   * @return the minutes of the time
   */
  public int getMinutes() {
    return minutes;
  }

  /**
   * @return the total amount of minutes of the time
   */
  public int convertToMinutes() {
    return hours * 60 + minutes;
  }

  private void setHours(int hours) {
    if (hours < 0 || hours >= 24) throw new IllegalArgumentException("Hours must be between 0 and 23");

    this.hours = hours;
  }

  private void setMinutes(int minutes) {
    if (minutes < 0 || minutes >= 60) throw new IllegalArgumentException("Minutes must be between 0 and 59");

    this.minutes = minutes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Time time = (Time) o;

    if (hours != time.hours) return false;
    return minutes == time.minutes && hours == time.hours;
  }

  /**
   * @param otherTime the time to compare to.
   * 
   * @return true if the time is after the given time, false otherwise.
   */
  public boolean isAfter(Time otherTime) {
    if (this.hours > otherTime.hours) return true;
    if (this.hours == otherTime.hours && this.minutes > otherTime.minutes) return true;

    return false;
  }

  /**
   * 
   * @param time the time to compare to.
   * @return true if the time is before the given time, false otherwise.
   */
  public boolean isBefore(Time time) {
    return !isAfter(time) && !equals(time);
  }

  /**
   * @param startTime the start time of the interval.
   * @param endTime the end time of the interval.
   * 
   * @return true if the time is between the given times, false otherwise.
   */
  public boolean isBetween(Time startTime, Time endTime) {
    if (equals(startTime) || equals(endTime)) return true;

    return isAfter(startTime) && isBefore(endTime);
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d", hours, minutes);
  }
}
