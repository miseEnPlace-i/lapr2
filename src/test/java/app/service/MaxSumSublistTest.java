package app.service;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import app.service.MaxSum.MaxSum;

public class MaxSumSublistTest {
  private int[] baseCase = new int[] {2, 2, 5, -1, -6, -1, 8, -2};
  private int[] allNegativeCase = new int[] {-2, -6, -8, -1, -2, -4, -10};
  private int[] twoSublists = new int[] {2, 1, 5, -1, -6, -1, 8, -2};
  private int[] emptyList = new int[] {};
  private int[] otherExample = new int[] {1, -2, 5, -6, 2, 8, -1, 4, 10};

  @Test
  public void ensureSublistIsCorrect() {
    int[] result = new MaxSum().maxSum(baseCase);

    int[] expected = new int[] {2, 2, 5};

    assertArrayEquals(expected, result);
  }

  @Test
  public void ensureSublistIsCorrectWithNegativeValues() {
    int[] result = new MaxSum().maxSum(allNegativeCase);
    int[] expected = new int[] {-1};

    assertArrayEquals(expected, result);
  }

  @Test
  public void ensureSublistIsCorrectWithTwoEqualSublistSums() {
    // In this test the first sublist with max sum will be chosen
    int[] result = new MaxSum().maxSum(twoSublists);

    int[] expected = new int[] {2, 1, 5};

    assertArrayEquals(expected, result);
  }

  @Test
  public void ensureOtherInputWorks() {
    int[] result = new MaxSum().maxSum(otherExample);

    int[] expected = new int[] {2, 8, -1, 4, 10};

    assertArrayEquals(expected, result);
  }
}
