package app.domain.model.list;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.VaccinationCenter;

public class AvailableVaccinationCentersList {
    private List<VaccinationCenter> centers;

    public AvailableVaccinationCentersList() {
        centers = new ArrayList<>();
    }

    public void addVaccinationCenters(VaccinationCenter center) {
        centers.add(center);
    }

    public List<VaccinationCenter> getList() {
        return centers;
    }
}

