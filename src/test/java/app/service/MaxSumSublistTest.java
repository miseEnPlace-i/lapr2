package app.service;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MaxSumSublistTest {

  private Integer[] baseCase = new Integer[] {2, 2, 5, -1, -6, -1, 8, -2};
  private Integer[] allNegativeCase = new Integer[] {-2, -6, -8, -1, -2, -4, -10};
  private Integer[] twoSublists = new Integer[] {2, 1, 5, -1, -6, -1, 8, -2};
  private Integer[] emptyList = new Integer[] {};
  private Integer[] otherExample = new Integer[] {1, -2, 5, -6, 2, 8, -1, 4, 10};

  @Test
  public void ensureSublistIsCorrect() {
    List<Integer> list = Arrays.asList(baseCase);
    MaxSumSublistService data = new MaxSumSublistService(list);

    Integer[] expected = new Integer[] {2, 2, 5};

    List<Integer> result = data.getMaxSumSubList();

    assertEquals(Arrays.asList(expected), result);
  }

  @Test
  public void ensureSublistIsCorrectWithNegativeValues() {
    List<Integer> list = Arrays.asList(allNegativeCase);
    MaxSumSublistService data = new MaxSumSublistService(list);

    Integer[] expected = new Integer[] {-1};

    List<Integer> result = data.getMaxSumSubList();

    assertEquals(Arrays.asList(expected), result);
  }

  @Test
  public void ensureSublistIsCorrectWithTwoEqualSublistSums() {
    // In this test the first sublist with max sum will be chosen
    List<Integer> list = Arrays.asList(twoSublists);
    MaxSumSublistService data = new MaxSumSublistService(list);

    Integer[] expected = new Integer[] {2, 1, 5};

    List<Integer> result = data.getMaxSumSubList();

    assertEquals(Arrays.asList(expected), result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptySublistIsNotAllowed() {
    List<Integer> list = Arrays.asList(emptyList);
    new MaxSumSublistService(list);
  }

  @Test
  public void ensureOtherInputWorks() {
    List<Integer> list = Arrays.asList(otherExample);
    MaxSumSublistService data = new MaxSumSublistService(list);

    Integer[] expected = new Integer[] {2, 8, -1, 4, 10};

    List<Integer> result = data.getMaxSumSubList();

    assertEquals(Arrays.asList(expected), result);
  }
}
