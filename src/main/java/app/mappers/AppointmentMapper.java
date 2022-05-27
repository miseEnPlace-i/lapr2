package app.mappers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import app.domain.model.Appointment;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.dto.AppointmentDto;

/**
 * Appointment mapper
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentMapper {
    // Private constructor to prevent instantiation from other classes
    private AppointmentMapper() {}

    public static AppointmentDto toDto(Appointment appointment) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String number = appointment.getSnsNumber();
        Calendar date = appointment.getDate();
        VaccinationCenter center = appointment.getVaccinationCenter();
        VaccineType type = appointment.getVaccineType();
        boolean sms = appointment.isSms();

        String smsPermission = sms ? "Yes" : "No";

        return new AppointmentDto(number, format.format(date.getTime()), center.getName(),
                type.getDescription(), smsPermission);
    }
}
