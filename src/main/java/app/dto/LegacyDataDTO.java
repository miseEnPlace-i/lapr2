package app.dto;

import java.util.Calendar;

/**
 * Legacy Data DTO.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class LegacyDataDTO {
    private String snsNumber;
    private String vaccineName;
    private int dose;
    private String lotNumber;
    private Calendar scheduledDate;
    private Calendar arrivalDate;
    private Calendar administrationDate;
    private Calendar departureDate;

    public LegacyDataDTO(String snsNumber, String vaccineName, int dose, String lotNumber, Calendar scheduledDate, Calendar arrivalDate,
            Calendar administrationDate, Calendar departureDate) {
        this.snsNumber = snsNumber;
        this.vaccineName = vaccineName;
        this.dose = dose;
        this.lotNumber = lotNumber;
        this.scheduledDate = scheduledDate;
        this.arrivalDate = arrivalDate;
        this.administrationDate = administrationDate;
        this.departureDate = departureDate;
    }

    public String getSnsNumber() {
        return snsNumber;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public int getDose() {
        return dose;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public Calendar getScheduledDate() {
        return scheduledDate;
    }

    public Calendar getArrivalDate() {
        return arrivalDate;
    }

    public Calendar getAdministrationDate() {
        return administrationDate;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }
}
