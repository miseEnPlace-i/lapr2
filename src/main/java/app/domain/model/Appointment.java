package app.domain.model;

import java.util.Calendar;

/**
 * Appointment class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class Appointment {
    private int snsNumber;
    private Calendar date;

    //// an appointment should have a vaccine type if it is in a healthcare center
    // TODO: and a center where it is being administered

    private VaccinationCenter center;

    /**
     * Constructor for Appointment.
     * 
     * @param snsNumber The SNS number of the user this appointment is related to.
     * @param date The date of the appointment.
     */
    public Appointment(int snsNumber, Calendar date) {
        this.snsNumber = snsNumber;
        this.date = date;
    }

    /**
     * Compares the SNS number of this appointment with another number.
     */
    public boolean hasSnsNumber(int snsNumber) {
        return this.snsNumber == snsNumber;
    }

    /**
     * Checks if the date of this appointment is in the next hour.
     */
    public boolean isInTheNextHour() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 1);
        return this.date.after(now);
    }
}
