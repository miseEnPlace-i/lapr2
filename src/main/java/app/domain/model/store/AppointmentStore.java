package app.domain.model.store;

import java.util.Calendar;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.dto.AppointmentDTO;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentStore {

  // Appointments list
  private List<Appointment> appointments;

  Appointment appointment;

  public Appointment create(AppointmentDTO appointmentDTO) {
    String snsNumber = appointmentDTO.getSnsNumber();
    Calendar date = appointmentDTO.getDate();
    String time = appointmentDTO.getTime();
    VaccinationCenter center = appointmentDTO.getCenter();
    VaccineType vacType = appointmentDTO.getVaccineType();
    boolean sms = appointmentDTO.isSms();

    appointment = new Appointment(snsNumber, date, time, center, vacType, sms);

    return appointment;
  }

  public void validateAppointment(AppointmentDTO appointmentDto) {
    if (appointmentDto == null) {
      throw new IllegalArgumentException("\nAppointment is not valid.");
    }
    checkDuplicates(appointment);
  }

  private void checkDuplicates(Appointment appointment) {
    if (appointments.contains(appointment)) {
      throw new IllegalArgumentException("\nDuplicated appointment.");
    }
  }

  /**
   * Saves an appointment.
   * 
   * @param appointment the appointment
   */
  public void saveVaccinationCenter(Appointment appointment) {
    appointments.add(appointment);
  }

  /**
   * Asks for the size of the List appointments
   * 
   * @return number of appointments registered in the system
   */
  public int size() {
    return appointments.size();
  }
}
