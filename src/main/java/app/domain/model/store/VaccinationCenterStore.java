package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.VaccinationCenter;

public class VaccinationCenterStore {

    // Vaccination Centers list
    private List<VaccinationCenter> vacCenters;

    public VaccinationCenterStore() {
        this.vacCenters = new ArrayList<VaccinationCenter>();
    }

    public VaccinationCenter createVaccinationCenter(String name, String address, String emailAddress, int phoneNum,
            int faxNum,
            String webAddress, String openingHours, String closingHours, int slotDuration, int maxVacSlot,
            String coordinator) {

        // TO DO
        try {
        } catch (Exception e) {
        }

        VaccinationCenter center = new VaccinationCenter(name, address, emailAddress, phoneNum, faxNum, webAddress,
                openingHours, closingHours, slotDuration, maxVacSlot, coordinator);
        return center;
    }

    public void validateVaccinationCenter(VaccinationCenter center) {
        if (center == null) {
            throw new Error("Vaccination center is null");
        }
        checkDuplicates(center);
    }

    public void checkDuplicates(VaccinationCenter center) {
        if (vacCenters.contains(center)) {
            throw new IllegalArgumentException("Duplicate Vaccination Center");
        }
    }
}
