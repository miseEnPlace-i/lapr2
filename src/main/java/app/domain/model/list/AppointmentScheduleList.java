package app.domain.model.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.dto.AppointmentWithNumberDTO;
import app.domain.model.dto.AppointmentWithoutNumberDTO;

/**
 * AppointmentStore class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class AppointmentScheduleList {
  private List<Appointment[][]> appointments;

  /**
   * Constructor for AppointmentStore.
   */
  public AppointmentScheduleList() {
    this.appointments = new ArrayList<Appointment[][]>();
  }

  /**
   * Creates a new appointment.
   *
   * @param appointmentDTO
   * @return Appointment
   */
  public Appointment create(AppointmentWithNumberDTO appointmentDTO) {
    String snsNumber = appointmentDTO.getSnsNumber();
    Calendar date = appointmentDTO.getDate();
    VaccinationCenter center = appointmentDTO.getCenter();
    VaccineType vacType = appointmentDTO.getVaccineType();
    boolean sms = appointmentDTO.getSmsPermission();

    Appointment appointment = new Appointment(snsNumber, date, center, vacType, sms);

    return appointment;
  }

  public Appointment create(AppointmentWithoutNumberDTO appointmentDTO, String snsNumber) {
    Calendar date = appointmentDTO.getDate();
    VaccinationCenter center = appointmentDTO.getCenter();
    VaccineType vacType = appointmentDTO.getVaccineType();
    boolean sms = appointmentDTO.getSmsPermission();

    Appointment appointment = new Appointment(snsNumber, date, center, vacType, sms);

    return appointment;
  }

  private int getAppointmentDayIndex(Calendar date) {
    return 0;
  }

  private int getAppointmentSlotIndex(Appointment appointment) {
    return 0;
  }

  /**
   * Validates an appointment.
   * 
   * @param appointmentDto
   */
  public void validateAppointment(Appointment appointment) {
    if (appointment == null) throw new IllegalArgumentException("Appointment is not valid.");

    checkDuplicates(appointment);
  }

  /**
   * Finds an appointment by its SNS number.
   */
  public Appointment findAppointment(String snsNumber) {
    // TODO FIX
    // for (Appointment appointment : appointments) {
    // TODO: check center
    // if (appointment.hasSnsNumber(snsNumber) && appointment.isInTheNextHour())
    // return appointment;
    // }
    return null;
  }

  /**
   * Saves an appointment.
   * 
   * @param appointment the appointment
   */
  public void saveAppointment(Appointment appointment) {
    validateAppointment(appointment);

    // appointments.add(appointment);
  }

  /**
   * Checks if an appointment is duplicated.
   * 
   * @param appointment
   */
  private void checkDuplicates(Appointment appointment) {
    if (appointments.contains(appointment))
      throw new IllegalArgumentException("\nDuplicated appointment.");

  }
}
