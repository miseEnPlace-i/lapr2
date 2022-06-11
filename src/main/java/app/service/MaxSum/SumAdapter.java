package app.service.MaxSum;

import com.isep.mdis.Sum;

public class SumAdapter implements IMaxSum {
  public int[] maxSum(int[] list) {
    return Sum.Max(list);
  }
}
