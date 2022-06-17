package app.service.sorting;

import java.util.Comparator;
import java.util.List;
import app.dto.LegacyDataDTO;

/**
 * Merge sort algorithm.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class MergeSort implements ISortStrategy {
    public MergeSort() {}

    // TODO: FIX
    @Override
    public void doSort(List<LegacyDataDTO> data, Comparator<LegacyDataDTO> c) {
        int n = data.size();
        if (n <= 1) return;
        int mid = n / 2;
        List<LegacyDataDTO> left = data.subList(0, mid);
        List<LegacyDataDTO> right = data.subList(mid, n);
        doSort(left, c);
        doSort(right, c);
        merge(data, left, right, c);
    }

    private void merge(List<LegacyDataDTO> data, List<LegacyDataDTO> left, List<LegacyDataDTO> right, Comparator<LegacyDataDTO> c) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (c.compare(left.get(i), right.get(j)) < 0) {
                data.set(k, left.get(i));
                i++;
            } else {
                data.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < left.size()) {
            data.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < right.size()) {
            data.set(k, right.get(j));
            j++;
            k++;
        }
    }
}
