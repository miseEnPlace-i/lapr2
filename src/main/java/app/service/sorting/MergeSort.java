package app.service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import app.dto.LegacyDataDTO;

/**
 * Merge sort algorithm.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class MergeSort implements ISortStrategy {
    private Comparator<LegacyDataDTO> comparator;

    public MergeSort() {}

    @Override
    public void doSort(List<LegacyDataDTO> data, Comparator<LegacyDataDTO> c) {
        this.comparator = c;
        List<LegacyDataDTO> result = mergeSort(data);

        data.clear();
        data.addAll(result);
    }

    private List<LegacyDataDTO> mergeSort(List<LegacyDataDTO> data) {
        // if the list contains only one element, it is already sorted
        if (data.size() <= 1) return data;

        // divide the list into two halves
        int mid = data.size() / 2;
        // System.out.print(mid + " ");

        // left: 0
        // mid: mid
        // right: n

        // keep dividing recursively until left + right = 1
        List<LegacyDataDTO> left = mergeSort(data.subList(0, mid));
        List<LegacyDataDTO> right = mergeSort(data.subList(mid, data.size()));

        // finally, merge both halves
        return merge(left, right);
    }

    private List<LegacyDataDTO> merge(List<LegacyDataDTO> left, List<LegacyDataDTO> right) {
        // let's create 2 sub lists of data
        // where we will merge them into the main list

        List<LegacyDataDTO> data = new ArrayList<LegacyDataDTO>();
        int i = 0, j = 0;

        // reorder the greater elements into the correct position
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) < 0) {
                data.add(left.get(i));
                i++;
            } else {
                data.add(right.get(j));
                j++;
            }
        }

        // finally, fill with the remainding elements
        while (i < left.size()) {
            data.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            data.add(right.get(j));
            j++;
        }

        return data;
    }
}
