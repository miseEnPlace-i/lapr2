package app.domain.model;

import java.util.Calendar;

public class Appointment {
    private int snsNumber;
    private Calendar date;

    public Appointment(int snsNumber, Calendar date) {
        this.snsNumber = snsNumber;
        this.date = date;
    }
}
