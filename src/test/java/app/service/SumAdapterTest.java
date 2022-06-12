package app.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.Test;
import app.service.MaxSum.SumAdapter;

public class SumAdapterTest {
  @Test
  public void testMaxSum() {
    SumAdapter adapter = new SumAdapter();

    int[] example = new int[] {29, -32, -9, -25, 44, 12, -61, 51, -9, 44, 74, 4};
    int[] expected = new int[] {51, -9, 44, 74, 4};

    assertArrayEquals(adapter.maxSum(example), expected);
  }
}
