package app.mapper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import app.domain.model.Appointment;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
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
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    String number = appointment.getSnsUser().getSnsNumber();
    Calendar date = appointment.getDate();
    VaccinationCenter center = appointment.getVaccinationCenter();
    VaccineType type = appointment.getVaccineType();
    boolean sms = appointment.isSms();

    String smsPermission = sms ? "Yes" : "No";

    return new AppointmentInsertDTO(number, format.format(date.getTime()), center.getName(), type.getDescription(), smsPermission);
  }
}
