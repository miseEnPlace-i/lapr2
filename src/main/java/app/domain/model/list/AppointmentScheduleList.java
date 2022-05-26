package app.domain.model.list;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import app.domain.model.Appointment;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.dto.AppointmentWithNumberDTO;
import app.dto.AppointmentWithoutNumberDTO;
import app.exception.AppointmentNotFoundException;

/**
 * AppointmentStore class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class AppointmentScheduleList {
  private VaccinationCenter vaccinationCenter;
  private Map<Calendar, Appointment[][]> appointments;
  private int slotsPerDay = 0;
  private int vaccinesPerSlot = 0;

  /**
   * Constructor for AppointmentStore.
   */
  public AppointmentScheduleList(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
    slotsPerDay = calculateNOfSlotsPerDay(vaccinationCenter);
    vaccinesPerSlot = vaccinationCenter.getMaxVacSlot();

    this.appointments = new HashMap<Calendar, Appointment[][]>();
  }

  private int calculateNOfSlotsPerDay(VaccinationCenter center) {
    String[] openingHours = center.getOpeningHours().split(":");
    String[] closingHours = center.getClosingHours().split(":");

    int openingMinutesOfDay =
        Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);
    int closingMinutesOfDay =
        Integer.parseInt(closingHours[0]) * 60 + Integer.parseInt(closingHours[1]);

    return ((closingMinutesOfDay - openingMinutesOfDay) / center.getSlotDuration());
  }

  private void listVaccinationSchedule(Appointment[][] list) {
    for (int i = 0; i < list.length; i++) {
      for (int j = 0; j < list[0].length; j++) {
        System.out.print("[");
        if (list[i][j] != null) System.out.print("x]");
        else System.out.print(" ]");
      }
      System.out.println("");
    }
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

  private int getAppointmentSlotIndex(Calendar date) {
    String[] openingHours = vaccinationCenter.getOpeningHours().split(":");
    String[] closingHours = vaccinationCenter.getClosingHours().split(":");

    int openingMinutesOfDay =
        Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);
    int closingMinutesOfDay =
        Integer.parseInt(closingHours[0]) * 60 + Integer.parseInt(closingHours[1]);

    int slotDuration = vaccinationCenter.getSlotDuration();
    int scheduleMinutesOfDay =
        (date.get(Calendar.HOUR_OF_DAY) * 60 + date.get(Calendar.MINUTE)) - openingMinutesOfDay;

    if (isValidSchedule(scheduleMinutesOfDay, openingMinutesOfDay, closingMinutesOfDay))
      return scheduleMinutesOfDay / slotDuration;
    return -1;
  }

  private boolean isValidSchedule(int scheduledMinutesOfDay, int openingMinutesOfDay,
      int closingMinutesOfDay) {
    if (scheduledMinutesOfDay < 0) return false;

    // subtract slot duration because the last slot cannot be used
    int workingHours = closingMinutesOfDay - openingMinutesOfDay;

    if (scheduledMinutesOfDay > workingHours) return false;
    return true;
  }

  /**
   * Validates an appointment.
   * 
   * @param appointmentDto
   */
  public void validateAppointment(Appointment appointment) {
    if (appointment == null) throw new IllegalArgumentException("Appointment is not valid.");
  }

  /**
   * Saves an appointment.
   * 
   * @param appointment the appointment
   */
  public void saveAppointment(Appointment appointment) {
    validateAppointment(appointment);

    Calendar key = generateKeyFromDate(appointment.getDate());
    int slotIndex = getAppointmentSlotIndex(appointment.getDate());

    if (slotIndex == -1) throw new IllegalArgumentException("Appointment schedule is not valid.");

    if (appointments.containsKey(key)) {
      Appointment[][] slots = appointments.get(key);

      int i = getAvailableIndexInSlot(slots[slotIndex]);

      if (i == -1) throw new IllegalArgumentException("No available slot");

      slots[slotIndex][i] = appointment;
    } else {
      Appointment[][] slots = new Appointment[slotsPerDay][vaccinationCenter.getMaxVacSlot()];

      slots[slotIndex][0] = appointment;
      appointments.put(key, slots);
    }

    listVaccinationSchedule(getAppointmentScheduleForDay(key));
  }

  public boolean checkSlotAvailability(Calendar date) {
    Calendar key = generateKeyFromDate(date);
    int slotIndex = getAppointmentSlotIndex(date);

    if (appointments.containsKey(key)) {
      Appointment[][] slots = appointments.get(key);

      int i = getAvailableIndexInSlot(slots[slotIndex]);

      if (i == -1) return false;
      else return true;
    } else {
      return true;
    }
  }

  public Appointment[][] getAppointmentScheduleForDay(Calendar date) {
    return appointments.get(generateKeyFromDate(date));
  }

  private int getAvailableIndexInSlot(Appointment[] slot) {
    for (int i = 0; i < slot.length; i++)
      if (slot[i] == null) return i;

    return -1;
  }

  private Calendar generateKeyFromDate(Calendar date) {
    Calendar key = Calendar.getInstance();
    key.setTime(date.getTime());
    key.set(Calendar.HOUR_OF_DAY, 0);
    key.set(Calendar.MINUTE, 0);
    key.set(Calendar.SECOND, 0);
    key.set(Calendar.MILLISECOND, 0);

    return key;
  }

  private boolean hasAppointmentInDay(Appointment[][] appointments, String snsNumber) {
    for (int i = 0; i < appointments.length; i++)
      for (int j = 0; j < appointments[i].length; j++)
        if (appointments[i][j] != null && appointments[i][j].hasSnsNumber(snsNumber)) return true;

    return false;
  }

  public Appointment hasAppointmentToday(String snsNumber) throws AppointmentNotFoundException {
    // get today's appointments
    Calendar key = this.generateKeyFromDate(Calendar.getInstance());
    Appointment[][] appointments = this.appointments.get(key);

    for (int i = 0; i < appointments.length; i++)
      for (int j = 0; j < appointments[i].length; j++)
        if (appointments[i][j] != null && appointments[i][j].hasSnsNumber(snsNumber))
          return appointments[i][j];

    throw new AppointmentNotFoundException("This SNS User does not have an appointment for today.");
  }
}
