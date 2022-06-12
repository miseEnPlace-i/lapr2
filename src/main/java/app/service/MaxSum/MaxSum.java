package app.service.MaxSum;

import java.util.List;

public class MaxSum implements IMaxSum {
  /**
   * Brute-force approach O(n^2)
   * 
   * @param list - list of integers
   * @return the max sum sublist
   */
  public List<Integer> maxSum(List<Integer> list) {
    int startIndex = 0;
    int endIndex = list.size();
    int sum = Integer.MIN_VALUE;

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
}
