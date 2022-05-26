package app.mapper;

import app.domain.model.Appointment;
import app.dto.AppointmentDTO;

/**
 * Appointment mapper
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentMapper {
    // Private constructor to prevent instantiation from other classes
    private AppointmentMapper() {}

    public static AppointmentDTO toDto(Appointment appointment) {
        return new AppointmentDTO(appointment.getSnsNumber(), appointment.getDate(),
                appointment.getVaccinationCenter(), appointment.getVaccineType(),
                appointment.isSms());
    }
}
