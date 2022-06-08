package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.utils.Time;

public class CenterPerformance {
  private List<CenterEvent> events;
  private List<Integer> differenceList;
  private List<Integer> maxSumSubList;
  private int sum;
  private Time startingInterval;
  private Time endingInterval;

  private Time openingHours;

  public CenterPerformance(List<CenterEvent> events, int interval, Time openingHours) {
    this.events = events;
    this.openingHours = openingHours;
    differenceList = calculateDifferencesList();
  }

  private List<Integer> calculateDifferencesList() {
    List<Integer> differences = new ArrayList<Integer>();

    for (int i = 0; i < events.size(); i++) {

    }
    return null;
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
