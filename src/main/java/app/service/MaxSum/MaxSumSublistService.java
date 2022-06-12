package app.service.MaxSum;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import app.domain.shared.Constants;
import app.service.PropertiesUtils;

public class MaxSumSublistService {
  private List<Integer> sublist = null;
  private int startIndex = 0;
  private int endIndex = 0;
  private int sum = 0;

  public MaxSumSublistService(List<Integer> differenceList) {
    if (differenceList.size() == 0) throw new IllegalArgumentException("The list cannot be empty");

    IMaxSum strategy = getMaxSumStrategy();
    sublist = strategy.maxSum(differenceList);
    findIndexes(differenceList);
    calculateSum(differenceList);
  }

  private IMaxSum getMaxSumStrategy() {
    Properties props = PropertiesUtils.getProperties();
    String algorithmName = props.getProperty(Constants.PARAMS_PERFORMANCE_ALGORITHM);

    System.out.println(algorithmName);
    if (algorithmName.equals("BruteForce")) return new MaxSum();
    else if (algorithmName.equals("Linear")) return new SumAdapter();
    else throw new IllegalArgumentException("Unknown algorithm name: " + algorithmName);
  }

  private void findIndexes(List<Integer> list) {
    startIndex = Collections.indexOfSubList(list, sublist);
    endIndex = startIndex + sublist.size() - 1;
  }

  private void calculateSum(List<Integer> list) {
    for (int i = startIndex; i <= endIndex; i++)
      sum += list.get(i);
  }

  public int getStartIndex() {
    return startIndex;
  }

  public int getEndIndex() {
    return endIndex;
  }

  public int getSum() {
    return sum;
  }

  public List<Integer> getMaxSumSubList() {
    return sublist;
  }
}
