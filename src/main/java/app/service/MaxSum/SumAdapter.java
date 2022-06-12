package app.service.MaxSum;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import com.isep.mdis.Sum;

public class SumAdapter implements IMaxSum {
  public List<Integer> maxSum(List<Integer> list) {
    int[] array = parseListToPrimitiveArray(list);

    int[] result = Sum.Max(array);

    return parseArrayToList(result);
  }

  private List<Integer> parseArrayToList(int[] array) {
    return Arrays.stream(array).boxed().toList();
  }

  private int[] parseListToPrimitiveArray(List<Integer> list) {
    return ArrayUtils.toPrimitive(list.toArray(new Integer[list.size()]));
  }
}
