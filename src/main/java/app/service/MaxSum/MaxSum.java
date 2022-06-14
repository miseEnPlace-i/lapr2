package app.service.MaxSum;

import org.apache.commons.lang3.ArrayUtils;

public class MaxSum implements IMaxSum {
  /**
   * Brute-force approach O(n^2)
   * 
   * @param list - list of integers
   * @return the max sum sublist
   */
  public int[] maxSum(int[] list) {
    int startIndex = 0;
    int endIndex = list.length;
    int sum = Integer.MIN_VALUE;

    for (int left = 0; left < list.length; left++) {
      int currentSum = 0;

      for (int right = left; right < list.length; right++) {
        currentSum += list[right];

        if (currentSum > sum) {
          sum = currentSum;
          startIndex = left;
          endIndex = right;
        }
      }
    }

    return ArrayUtils.subarray(list, startIndex, endIndex + 1);
  }
}
