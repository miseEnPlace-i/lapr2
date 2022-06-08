package app.service;

import java.util.List;

public class MaxSumSublistService {
  private List<Integer> maxSumSublist = null;
  private int startIndex = 0;
  private int endIndex = maxSumSublist.size();
  private int maxSum = Integer.MIN_VALUE;

  public MaxSumSublistService(List<Integer> list) {
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

        if (currentSum > maxSum) {
          maxSum = currentSum;
          startIndex = left;
          endIndex = right;
        }
      }
    }

    return list;
  }

  public int getStartIndex() {
    return startIndex;
  }

  public int getEndIndex() {
    return endIndex;
  }

  public int getMaxSum() {
    return maxSum;
  }

  public List<Integer> getMaxSumSubList() {
    return maxSumSublist;
  }
}
