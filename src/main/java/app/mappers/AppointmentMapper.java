package app.mappers;

import app.domain.model.Appointment;
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
        return new AppointmentDto(appointment.getSnsNumber(), appointment.getDate(),
                appointment.getVaccinationCenter(), appointment.getVaccineType(),
                appointment.isSms());
    }
}
