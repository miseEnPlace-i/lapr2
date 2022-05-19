package app.domain.model.store;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.Appointment;

/**
 * AppointmentStore class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class AppointmentStore {
    private List<Appointment> appointments;

    /**
     * Constructor for AppointmentStore.
     */
    public AppointmentStore() {
        this.appointments = new ArrayList<Appointment>();
    }

    /**
     * Creates a new appointment.
     */
    public Appointment addAppointment(String snsNumber, Calendar date) {
        // TODO: validations once more in the attack
        Appointment appointment = new Appointment(snsNumber, date);
        appointments.add(appointment);
        return appointment;
    }

    /**
     * Finds an appointment by its SNS number.
     */
    public Appointment findAppointment(String snsNumber) {
        for (Appointment appointment : appointments) {
            // TODO: check center
            if (appointment.hasSnsNumber(snsNumber) && appointment.isInTheNextHour())
                return appointment;
        }
        return null;
    }
}
