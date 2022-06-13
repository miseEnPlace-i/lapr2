package app.service.MaxSum;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import com.isep.mdis.Sum;

public class SumAdapter implements IMaxSum {
  public int[] maxSum(int[] list) {
    return Sum.Max(list);
  }



}
