package app.dto;

import java.util.Comparator;

public class DepartureCompare implements Comparator<LegacyDataDTO> {
    @Override
    public int compare(LegacyDataDTO o1, LegacyDataDTO o2) {
        return o1.getDepartureDate().compareTo(o2.getDepartureDate());
    }
}
