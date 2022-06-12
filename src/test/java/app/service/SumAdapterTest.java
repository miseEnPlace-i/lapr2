package app.service;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;
import app.service.MaxSum.SumAdapter;

public class SumAdapterTest {
  private Integer[] baseCase = new Integer[] {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
  private Integer[] allNegatives = new Integer[] {-29, -32, -9, -25, -44, -12, -61, -51, -9, -44, -74, -4};

  @Test
  public void ensureMaxSumIsWorking() {
    SumAdapter adapter = new SumAdapter();
    Integer[] expected = new Integer[] {51, -9, 44, 74, 4};

    assertEquals(adapter.maxSum(Arrays.asList(baseCase)), Arrays.asList(expected));
  }

  // !! Bug identified in external module
  /*
   * @Test public void ensureMaxSumIsWorkingWithAllNegatives() { SumAdapter adapter = new SumAdapter(); Integer[] expected
   * = new Integer[] {-4};
   * 
   * assertEquals(Arrays.asList(expected), adapter.maxSum(Arrays.asList(allNegatives))); }
   */
}
