package app.service.sorting;

import java.util.Comparator;
import java.util.List;
import app.dto.LegacyDataDTO;

/**
 * Bubble sort algorithm.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class BubbleSort implements ISortStrategy {
    public BubbleSort() {}

    @Override
    public void doSort(List<LegacyDataDTO> data, Comparator<LegacyDataDTO> c) {
        int n = data.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // compare using the comparator
                if (c.compare(data.get(j), data.get(j + 1)) > 0) {
                    LegacyDataDTO temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
    }
}
