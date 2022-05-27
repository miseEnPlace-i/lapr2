package app.mapper;

import app.domain.model.Appointment;
import app.dto.AppointmentListDTO;

/**
 * Appointment mapper
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentListMapper {
    // Private constructor to prevent instantiation from other classes
    private AppointmentListMapper() {}

    public static AppointmentListDTO toDto(Appointment appointment) {
        return new AppointmentListDTO(appointment.getSnsUser().getSnsNumber(), appointment.getSnsUser().getName(), appointment.getDate(),
                appointment.getVaccinationCenter().getName(), appointment.getVaccineType().getDescription());
    }
}
