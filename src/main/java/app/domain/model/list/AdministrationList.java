package app.domain.model.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.Administration;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;

/**
 * 
 * 
 */
public class AdministrationList {
    private List<Administration> list;

    public AdministrationList() {
        this.list = new ArrayList<Administration>();
    }

    public Administration create(Vaccine vaccine, int doseNumber, String lotNumber, VaccinationCenter center, Calendar administrationDate) {
        return new Administration(vaccine, doseNumber, lotNumber, center, administrationDate);
    }

    public void add(Administration administration) {
        this.list.add(administration);
    }

}
