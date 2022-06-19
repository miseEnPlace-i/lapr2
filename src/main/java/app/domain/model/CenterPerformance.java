package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.list.CenterEventList;
import app.domain.shared.CenterEventType;
import app.service.MaxSum.MaxSumSublistService;
import app.utils.Time;

public class CenterPerformance implements Serializable {
  private CenterEventList events;
  private List<Integer> differenceList;
  private List<Integer> maxSumSubList;

  private int interval;

  private int maxSum;
  private double timeElapsed;

  private Time startingInterval;
  private Time endingInterval;

  private Time openingHours;
  private Time closingHours;

  public CenterPerformance(CenterEventList events, int interval, Time openingHours, Time closingHours) {
    this.events = events;
    this.interval = interval;
    this.openingHours = openingHours;
    this.closingHours = closingHours;

    differenceList = calculateDifferencesList();

    MaxSumSublistService maxSumSubListData = new MaxSumSublistService(differenceList);

    int startIndex = maxSumSubListData.getStartIndex();
    startingInterval = convertIndexToTime(startIndex);

    int endIndex = maxSumSubListData.getEndIndex();
    endingInterval = convertIndexToTime(endIndex + 1);

    maxSum = maxSumSubListData.getSum();

    maxSumSubList = maxSumSubListData.getMaxSumSubList();
    timeElapsed = maxSumSubListData.getTimeElapsed();
  }

  /**
   * 
   * @param index The index of the event in the list.
   * @return The time of the event.
   */
  private Time convertIndexToTime(int index) {
    return new Time(openingHours.convertToMinutes() + index * interval);
  }

  /**
   * The list is calculated by the difference between arrivals and departures for a given interval of time, in minutes
   * 
   * @return The list of differences of the events
   */
  private List<Integer> calculateDifferencesList() {
    int nOfWorkingMinutes = closingHours.convertToMinutes() - openingHours.convertToMinutes();
    int nOfIntervals = (int) Math.floor(nOfWorkingMinutes / interval);

    List<Integer> differences = new ArrayList<Integer>(nOfIntervals);

    /**
     * Here we could do nOfIntervals - 1 and treat the last interval as a special case to include the users that arrive
     * after the closing hour. In the examples given this is not the case, so we decided not to do it
     */
    for (int i = 0; i < nOfIntervals; i++) {
      Time beginningInterval = new Time(openingHours.convertToMinutes() + i * interval);
      Time endInterval = new Time(beginningInterval.convertToMinutes() + interval);

      int differenceForInterval = getDifferenceForInterval(events, beginningInterval, endInterval);

      differences.add(differenceForInterval);
    }

    return differences;
  }

  /**
   * 
   * @param events The list of events.
   * @param beginningTime The beginning of the interval.
   * @param endingTime The end of the interval.
   * @return The difference between the arrivals and departures for the given interval.
   */
  private int getDifferenceForInterval(CenterEventList events, Time beginningTime, Time endingTime) {
    int intervalDifference = 0;

    for (int i = 0; i < events.size(); i++) {
      CenterEvent event = events.get(i);
      Time eventTime = new Time(event.getDate());

      if (eventTime.isBetweenExcludeRight(beginningTime, endingTime)) {
        if (event.isType(CenterEventType.ARRIVAL)) intervalDifference++;
        if (event.isType(CenterEventType.DEPARTURE)) intervalDifference--;
      }
    }

    return intervalDifference;
  }

  /**
   * 
   * @return The list of differences of the events.
   */
  public List<Integer> getDifferencesList() {
    return differenceList;
  }

  /**
   * 
   * @return A readable string with the differences of the events.
   */
  public String stringifyDifferencesList() {
    return stringifyList(differenceList);
  }

  /**
   * 
   * @param list The list to stringify.
   * @return A readable string with the list.
   */
  private String stringifyList(List<Integer> list) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(list.get(0));

    for (int i = 1; i < list.size(); i++) {
      sb.append(", ");
      sb.append(list.get(i));
    }

    sb.append("]");
    return sb.toString();
  }

  /**
   * 
   * @return The max contiguous sum sublist.
   */
  public List<Integer> getMaxSumSubList() {
    return maxSumSubList;
  }

  /**
   * 
   * @return a readable string with the max contiguous sum sublist.
   */
  public String stringifyMaxSumSublist() {
    return stringifyList(maxSumSubList);
  }

  /**
   * 
   * @return The max sum of the maximum contiguous sum sublist.
   */
  public int getMaxSum() {
    return maxSum;
  }

  /**
   * 
   * @return The start time of the max contiguous sum sublist.
   */
  public Time getStartingInterval() {
    return startingInterval;
  }

  /**
   * 
   * @return The end time of the max contiguous sum sublist.
   */
  public Time getEndingInterval() {
    return endingInterval;
  }

  /**
   * 
   * @return The time elapsed to find the max contiguous sum sublist.
   */
  public double getTimeElapsed() {
    return timeElapsed;
  }

  @Override
  public String toString() {
    return "CenterPerformance [differenceList=" + differenceList + ", maxSumSubList=" + maxSumSubList + ", interval=" + interval + ", maxSum=" + maxSum
        + ", startingInterval=" + startingInterval + ", endingInterval=" + endingInterval + ", openingHours=" + openingHours + ", closingHours=" + closingHours
        + "]";
  }
}
