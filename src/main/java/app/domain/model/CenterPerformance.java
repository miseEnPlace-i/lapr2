package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.list.CenterEventList;
import app.domain.shared.CenterEventType;
import app.service.MaxSum.MaxSumSublistService;
import app.utils.Time;

public class CenterPerformance {
  private CenterEventList events;
  private List<Integer> differenceList;
  private List<Integer> maxSumSubList;

  private int interval;

  private int maxSum;
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
    endingInterval = convertIndexToTime(endIndex);

    maxSum = maxSumSubListData.getSum();

    maxSumSubList = maxSumSubListData.getMaxSumSubList();

  }

  private Time convertIndexToTime(int index) {
    return new Time(openingHours.convertToMinutes() + index * interval);
  }

  private List<Integer> calculateDifferencesList() {
    int nOfWorkingMinutes = closingHours.convertToMinutes() - openingHours.convertToMinutes();
    int nOfIntervals = (int) Math.floor(nOfWorkingMinutes / interval);

    List<Integer> differences = new ArrayList<Integer>(nOfIntervals);

    for (int i = 0; i < nOfIntervals; i++) {
      Time beginningInterval = new Time(openingHours.convertToMinutes() + i * interval);
      Time endInterval = new Time(beginningInterval.convertToMinutes() + interval);

      int differenceForInterval = getDifferenceForInterval(events, beginningInterval, endInterval);

      differences.add(differenceForInterval);
    }

    return differences;
  }

  private int getDifferenceForInterval(CenterEventList events, Time beginningTime, Time endingTime) {
    int intervalDifference = 0;

    // -1 is needed because the last interval has different treatment
    for (int i = 0; i < events.size() - 1; i++) {
      CenterEvent event = events.get(i);
      Time eventTime = new Time(event.getDate());

      if (eventTime.isBetweenExcludeRight(beginningTime, endingTime)) {
        if (event.isType(CenterEventType.ARRIVAL)) intervalDifference++;
        if (event.isType(CenterEventType.DEPARTURE)) intervalDifference--;
      }
    }

    return intervalDifference;
  }

  public List<Integer> getDifferencesList() {
    return differenceList;
  }

  public List<Integer> getMaxSumSubList() {
    return maxSumSubList;
  }

  public int getMaxSum() {
    return maxSum;
  }

  public Time getStartingInterval() {
    return startingInterval;
  }

  public Time getEndingInterval() {
    return endingInterval;
  }

  @Override
  public String toString() {
    return "CenterPerformance [events=" + events + ", differenceList=" + differenceList + ", maxSumSubList=" + maxSumSubList + ", interval=" + interval
        + ", maxSum=" + maxSum + ", startingInterval=" + startingInterval + ", endingInterval=" + endingInterval + ", openingHours=" + openingHours
        + ", closingHours=" + closingHours + "]";
  }
}
