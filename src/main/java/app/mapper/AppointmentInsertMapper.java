package app.mapper;

import app.domain.model.Appointment;
import app.dto.AppointmentInsertDTO;

/**
 * Appointment mapper
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentInsertMapper {
    // Private constructor to prevent instantiation from other classes
    private AppointmentInsertMapper() {}

    public static AppointmentInsertDTO toDto(Appointment appointment) {
        return new AppointmentInsertDTO(appointment.getSnsUser().getSnsNumber(), appointment.getDate(),
                appointment.getVaccinationCenter(), appointment.getVaccineType(),
                appointment.isSms());
    }
}
