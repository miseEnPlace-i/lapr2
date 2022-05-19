package app.domain.model;

import java.util.Calendar;

/**
 * Appointment class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class Appointment {
    private String snsNumber;
    private Calendar date;
    private VaccinationCenter center;

    // an appointment should have a vaccine type if it is in a healthcare center

    /**
     * Constructor for Appointment.
     * 
     * @param snsNumber The SNS number of the user this appointment is related to.
     * @param date The date of the appointment.
     */
    public Appointment(String snsNumber, Calendar date) {
        this.snsNumber = snsNumber;
        this.date = date;
        // TODO: implement centers
    }

    /**
     * Compares the SNS number of this appointment with another number.
     */
    public boolean hasSnsNumber(String snsNumber) {
        return this.snsNumber.equals(snsNumber);
    }

    /**
     * Checks if the date of this appointment is in the next hour.
     * TODO: check next slot instead
     */
    public boolean isInTheNextHour() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 1);
        return this.date.after(now);
    }
}
