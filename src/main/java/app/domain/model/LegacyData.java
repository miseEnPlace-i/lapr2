package app.domain.model;

import java.util.Calendar;

/**
 * Legacy data used to import data from files exported from an old system.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class LegacyData {
    private SNSUser snsUser;
    private Vaccine vaccine;
    private int doseNumber;
    private String lotNumber;
    private Calendar arrivalDate;
    private Calendar scheduledDate;
    private Calendar administrationDate;
    private Calendar departuredDate;
    private VaccinationCenter center;

    public LegacyData(SNSUser snsUser, Vaccine vaccine, int doseNumber, String lotNumber, Calendar arrivalDate, Calendar scheduledDate,
            Calendar administrationDate, Calendar departuredDate, VaccinationCenter center) {
        this.snsUser = snsUser;
        this.vaccine = vaccine;
        this.doseNumber = doseNumber;
        this.lotNumber = lotNumber;
        this.arrivalDate = arrivalDate;
        this.scheduledDate = scheduledDate;
        this.administrationDate = administrationDate;
        this.departuredDate = departuredDate;
        this.center = center;
        validate();
    }

    private void validate() {
        if (snsUser == null || vaccine == null || doseNumber < 0 || lotNumber == null || arrivalDate == null || scheduledDate == null
                || administrationDate == null || departuredDate == null || center == null) {
            throw new IllegalArgumentException();
        }
    }

    // getters
    public SNSUser getSNSUser() {
        return snsUser;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public Calendar getArrivalDate() {
        return arrivalDate;
    }

    public Calendar getScheduledDate() {
        return scheduledDate;
    }

    public Calendar getAdministrationDate() {
        return administrationDate;
    }

    public Calendar getDeparturedDate() {
        return departuredDate;
    }

    public VaccinationCenter getCenter() {
        return center;
    }
}
