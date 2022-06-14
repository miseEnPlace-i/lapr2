package app.service.sorting;

import java.util.List;
import app.dto.LegacyDataDTO;

/**
 * Sort strategy interface.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public interface ISortStrategy {
    void doSort(List<LegacyDataDTO> data);
}
