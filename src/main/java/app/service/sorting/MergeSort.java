package app.service.sorting;

import java.util.List;
import app.dto.LegacyDataDTO;

/**
 * Merge sort algorithm.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class MergeSort implements ISortStrategy {
    public MergeSort() {}

    @Override
    public void doSort(List<LegacyDataDTO> data) {
        int n = data.size();
        if (n < 2) return;
        int mid = n / 2;
        List<LegacyDataDTO> left = data.subList(0, mid);
        List<LegacyDataDTO> right = data.subList(mid, n);
        doSort(left);
        doSort(right);
        merge(data, left, right);
    }

    private void merge(List<LegacyDataDTO> data, List<LegacyDataDTO> left, List<LegacyDataDTO> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) < 0) {
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
