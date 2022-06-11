package app.service;

import java.util.List;

public class MaxSumSublistService {
  private List<Integer> maxSumSublist = null;
  private int startIndex = 0;
  private int endIndex = 0;
  private int sum = Integer.MIN_VALUE;

  public MaxSumSublistService(List<Integer> list) {
    if (list.size() == 0) throw new IllegalArgumentException("The list cannot be empty");

    maxSumSublist = calculateMaxSumSublist(list);
  }

  /**
   * Brute-force approach O(n^2)
   * 
   * @param list - list of integers
   * @return the max sum sublist
   */
  private List<Integer> calculateMaxSumSublist(List<Integer> list) {
    for (int left = 0; left < list.size(); left++) {
      int currentSum = 0;

      for (int right = left; right < list.size(); right++) {
        currentSum += list.get(right);

        if (currentSum > sum) {
          sum = currentSum;
          startIndex = left;
          endIndex = right;
        }
      }
    }


    return list.subList(startIndex, endIndex + 1);
  }

  public int getStartIndex() {
    return startIndex;
  }

  public int getEndIndex() {
    return endIndex;
  }

  public int getSum() {
    return sum;
  }

  public List<Integer> getMaxSumSubList() {
    return maxSumSublist;
  }
}
