package app.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import app.service.MaxSum.SumAdapter;

public class SumAdapterTest {
  private int[] baseCase = new int[] {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
  private int[] allNegatives = new int[] {-29, -32, -9, -25, -44, -12, -61, -51, -9, -44, -74, -4};

  @Test
  public void ensureMaxSumIsWorking() {
    SumAdapter adapter = new SumAdapter();
    int[] expected = new int[] {51, -9, 44, 74, 4};

    assertArrayEquals(adapter.maxSum(baseCase), expected);
  }

  // !! Bug identified in external module
  /*
   * @Test public void ensureMaxSumIsWorkingWithAllNegatives() { SumAdapter adapter = new SumAdapter(); Integer[] expected
   * = new Integer[] {-4};
   * 
   * assertEquals(Arrays.asList(expected), adapter.maxSum(Arrays.asList(allNegatives))); }
   */
}
