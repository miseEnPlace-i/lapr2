package app.dto;

import java.util.Calendar;
import app.service.TimeUtils;

/**
 * Legacy Data DTO.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class LegacyDataDTO implements Comparable<LegacyDataDTO> {
    private String snsNumber;
    private String vaccineName;
    private String vaccineType;
    private int dose;
    private String lotNumber;
    private Calendar scheduledDate;
    private Calendar arrivalDate;
    private Calendar administrationDate;
    private Calendar departureDate;

    public LegacyDataDTO(String snsNumber, String vaccineName, int dose, String lotNumber, Calendar scheduledDate, Calendar arrivalDate,
            Calendar administrationDate, Calendar departureDate, String vaccineType) {
        this.snsNumber = snsNumber;
        this.vaccineName = vaccineName;
        this.dose = dose;
        this.lotNumber = lotNumber;
        this.scheduledDate = scheduledDate;
        this.arrivalDate = arrivalDate;
        this.administrationDate = administrationDate;
        this.departureDate = departureDate;
        this.vaccineType = vaccineType;
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

    public String getVaccineType() {
        return vaccineType;
    }

    @Override
    public int compareTo(LegacyDataDTO o) {
        return this.getArrivalDate().compareTo(o.getArrivalDate());
    }

    public int compareToByDeparture(LegacyDataDTO o) {
        return this.getDepartureDate().compareTo(o.getDepartureDate());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SNS User number: %s\n", this.snsNumber));
        sb.append(String.format("Vaccine: %s", this.vaccineName));
        sb.append(String.format(" for %s disease\n", this.vaccineType));
        sb.append(String.format("Dose: %s\n", this.dose));
        sb.append(String.format("Lot number: %s\n", this.lotNumber));
        sb.append(String.format("Arrival time: %s\n", TimeUtils.timeToString(arrivalDate)));
        sb.append(String.format("Scheduled time: %s\n", TimeUtils.timeToString(scheduledDate)));
        sb.append(String.format("Vaccine Administration time: %s\n", TimeUtils.timeToString(administrationDate)));
        sb.append(String.format("Departure time: %s\n", TimeUtils.timeToString(departureDate)));

        return sb.toString();
    }
}
