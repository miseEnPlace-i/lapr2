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
    private Comparator<LegacyDataDTO> comparator;

    public MergeSort() {}

    // TODO: FIX
    @Override
    public void doSort(List<LegacyDataDTO> data, Comparator<LegacyDataDTO> c) {
        this.comparator = c;
        mergeSort(data, 0, data.size() - 1);
    }

    private void mergeSort(List<LegacyDataDTO> data, int left, int right) {
        // if the list contains only one element, it is already sorted
        if (left >= right) return;

        // divide the list into two halves
        int mid = (left + right) / 2;

        // left: 0
        // mid: mid
        // right: n

        // keep dividing recursively until left + right = 1
        mergeSort(data, left, mid);
        mergeSort(data, mid + 1, right);

        // finally, merge both halves
        merge(data, left, mid, right);
    }

    private void merge(List<LegacyDataDTO> data, int left, int mid, int right) {
        // let's create 2 sub lists of data
        // where we will merge them into the main list

        int n1 = mid - left + 1;
        int n2 = right - mid;

        // create the 2 sub lists
        List<LegacyDataDTO> leftList = data.subList(left, left + n1);
        List<LegacyDataDTO> rightList = data.subList(mid + 1, mid + 1 + n2);

        int i = 0, j = 0, k = left;

        // reorder the greater elements into the correct position
        while (i < leftList.size() && j < rightList.size()) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) < 0) {
                data.set(k, leftList.get(i));
                i++;
            } else {
                data.set(k, rightList.get(j));
                j++;
            }
            k++;
        }
        
        // finally, fill with the remainding elements
        while (i < leftList.size()) {
            data.set(k, leftList.get(i));
            i++;
            k++;
        }
        while (j < rightList.size()) {
            data.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
