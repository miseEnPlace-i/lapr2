package app.domain.model;

import java.util.Calendar;
import java.util.List;
import app.utils.Time;

public class CenterPerformance {
  private Calendar day;
  private List<Integer> maxSumSubList;
  private int sum;
  private Time startingInterval;
  private Time endingInterval;

  private Time openingHours;

  public CenterPerformance(Calendar day, int interval, Time openingHours) {
    this.day = day;
    this.openingHours = openingHours;
  }

  public Calendar getDay() {
    return day;
  }

  public List<Integer> getMaxSumSubList() {
    return maxSumSubList;
  }

  public int getSum() {
    return sum;
  }

  public Time getStartingInterval() {
    return startingInterval;
  }

  public Time getEndingInterval() {
    return endingInterval;
  }
}
