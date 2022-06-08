package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.domain.shared.CenterEventType;
import app.service.MaxSumSublistService;
import app.utils.Time;

public class CenterPerformance {
  private List<CenterEvent> events;
  private List<Integer> differenceList;
  private List<Integer> maxSumSubList;

  private int interval;

  private int sum;
  private Time startingInterval;
  private Time endingInterval;

  private Time openingHours;

  public CenterPerformance(List<CenterEvent> events, int interval, Time openingHours) {
    this.events = events;
    this.interval = interval;
    this.openingHours = openingHours;
    differenceList = calculateDifferencesList();

    MaxSumSublistService maxSumSubListData = new MaxSumSublistService(differenceList);

    int startIndex = maxSumSubListData.getStartIndex();
    startingInterval = convertIndexToTime(startIndex);

    int endIndex = maxSumSubListData.getEndIndex();
    endingInterval = convertIndexToTime(endIndex);

    int maxSum = maxSumSubListData.getMaxSum();

    maxSumSubList = maxSumSubListData.getMaxSumSubList();
  }

  private Time convertIndexToTime(int index) {
    return new Time(openingHours.convertToMinutes() + index * interval);
  }

  private List<Integer> calculateDifferencesList() {
    List<Integer> differences = new ArrayList<Integer>();

    for (int i = 0; i < events.size(); i++) {
      Time beginningInterval = new Time(openingHours.convertToMinutes() + i * interval);
      Time endInterval = new Time(beginningInterval.convertToMinutes() + interval);

      int differenceForInterval = getDifferenceForInterval(events, beginningInterval, endInterval);

      differences.add(differenceForInterval);
    }

    return differences;
  }

  private int getDifferenceForInterval(List<CenterEvent> events, Time beginningTime, Time endingTime) {
    int intervalDifference = 0;

    for (CenterEvent event : events) {
      Time eventTime = new Time(event.getDate());

      if (eventTime.isBetween(beginningTime, endingTime)) {
        if (event.isType(CenterEventType.ARRIVAL)) intervalDifference++;
        if (event.isType(CenterEventType.DEPARTURE)) intervalDifference--;
      }
    }

    return intervalDifference;
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
