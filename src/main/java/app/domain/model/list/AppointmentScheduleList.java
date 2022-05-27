package app.domain.model.list;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import app.domain.model.Appointment;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.exception.AppointmentNotFoundException;

/**
 * AppointmentStore class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ip.pt>
 */
public class AppointmentScheduleList {
  private VaccinationCenter vaccinationCenter;
  private Map<Calendar, Appointment[][]> appointments;
  private int slotsPerDay = 0;
  private int vaccinesPerSlot = 0;

  /**
   * Constructor for AppointmentScheduleList.
   */
  public AppointmentScheduleList(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
    slotsPerDay = calculateNOfSlotsPerDay();
    vaccinesPerSlot = vaccinationCenter.getMaxVacSlot();

    this.appointments = new HashMap<Calendar, Appointment[][]>();
  }

  /**
   * 
   * @param center the vaccination center
   * @return the number of slots the center can have per day
   */
  private int calculateNOfSlotsPerDay() {
    String[] openingHours = vaccinationCenter.getOpeningHours().split(":");
    String[] closingHours = vaccinationCenter.getClosingHours().split(":");

    int openingMinutesOfDay = Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);
    int closingMinutesOfDay = Integer.parseInt(closingHours[0]) * 60 + Integer.parseInt(closingHours[1]);

    return ((closingMinutesOfDay - openingMinutesOfDay) / vaccinationCenter.getSlotDuration());
  }

  /**
   * Prints a list representing the schedule of appointments.
   * 
   * Used for developer purposes
   */
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
   * Creates a new appointment
   * 
   * @param snsUser the SNSUser
   * @param date the date
   * @param center the vaccination center
   * @param vaccineType the vaccine type
   * @param sms if the user wants to receive a SMS
   * @return
   */
  public Appointment create(SNSUser snsUser, Calendar date, VaccinationCenter center, VaccineType vaccineType, boolean sms) {
    Appointment appointment = new Appointment(snsUser, date, center, vaccineType, sms);

    return appointment;
  }

  /**
   * 
   * @param date the date of the appointment
   * @return the index of the appointment in the center schedule list
   */
  private int getAppointmentSlotIndex(Calendar date) {
    String[] openingHours = vaccinationCenter.getOpeningHours().split(":");
    String[] closingHours = vaccinationCenter.getClosingHours().split(":");

    int openingMinutesOfDay = Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);
    int closingMinutesOfDay = Integer.parseInt(closingHours[0]) * 60 + Integer.parseInt(closingHours[1]);

    int slotDuration = vaccinationCenter.getSlotDuration();
    int scheduleMinutesOfDay = (date.get(Calendar.HOUR_OF_DAY) * 60 + date.get(Calendar.MINUTE)) - openingMinutesOfDay;

    if (isValidSchedule(scheduleMinutesOfDay, openingMinutesOfDay, closingMinutesOfDay)) return scheduleMinutesOfDay / slotDuration;
    return -1;
  }

  /**
   * Get the real closing hours of the center. The real closing hours represent the maximum schedule the staff can stay in
   * the center.
   * 
   * It is calculated adding the closing hours of the center with the slot duration.
   */
  public String getRealClosingHours() {
    String[] openingHours = vaccinationCenter.getOpeningHours().split(":");
    int openingMinutesOfDay = Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);

    int realClosingMinutesOfDay = openingMinutesOfDay + (calculateNOfSlotsPerDay() * vaccinationCenter.getSlotDuration());

    int hours = realClosingMinutesOfDay / 60;
    int minutes = realClosingMinutesOfDay % 60;

    return String.valueOf(hours) + ":" + String.valueOf(minutes);
  }

  /**
   * 
   * @param scheduledMinutesOfDay the minutes of the scheduled appointment
   * @param openingMinutesOfDay the opening schedule, in minutes, of the center
   * @param closingMinutesOfDay the closing schedule, in minutes, of the center
   * @return
   */
  private boolean isValidSchedule(int scheduledMinutesOfDay, int openingMinutesOfDay, int closingMinutesOfDay) {
    if (scheduledMinutesOfDay < 0) return false;

    int workingHours = closingMinutesOfDay - openingMinutesOfDay;

    if (scheduledMinutesOfDay > workingHours) return false;
    return true;
  }

  /**
   * Validates the appointment.
   * 
   * @param appointment the appointment to be added
   */
  public void validateAppointment(Appointment appointment) {
    // TODO validate sns user with health data
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
      Appointment[][] slots = new Appointment[slotsPerDay][vaccinesPerSlot];

      slots[slotIndex][0] = appointment;
      appointments.put(key, slots);
    }
  }

  /**
   * 
   * @param date the date of the appointment
   * @return true if the slot of the given date is available
   */
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

  /**
   * 
   * @param date the date of the appointment
   * @return the appointment matrix representing the schedule of the given date in the center
   */
  public Appointment[][] getAppointmentScheduleForDay(Calendar date) {
    return appointments.get(generateKeyFromDate(date));
  }

  /**
   * 
   * @param slot the slot of the appointment
   * @return the index of the first available index in the given slot
   */
  private int getAvailableIndexInSlot(Appointment[] slot) {
    for (int i = 0; i < slot.length; i++)
      if (slot[i] == null) return i;

    return -1;
  }

  /**
   * 
   * @param date the date of the appointment
   * @return the key of the appointment in the map of the center's appointments
   */
  private Calendar generateKeyFromDate(Calendar date) {
    Calendar key = Calendar.getInstance();
    key.setTime(date.getTime());
    key.set(Calendar.HOUR_OF_DAY, 0);
    key.set(Calendar.MINUTE, 0);
    key.set(Calendar.SECOND, 0);
    key.set(Calendar.MILLISECOND, 0);

    return key;
  }

  /**
   * 
   * @param snsNumber the sns number of the user
   * @return the appointment of the user with the given sns number
   * @throws AppointmentNotFoundException if the user has no appointment
   */

  public Appointment hasAppointmentToday(String snsNumber) throws AppointmentNotFoundException {
    // get today's appointments
    Calendar key = this.generateKeyFromDate(Calendar.getInstance());
    Appointment[][] appointments = this.appointments.get(key);

    if (appointments != null) for (int i = 0; i < appointments.length; i++)
      for (int j = 0; j < appointments[i].length; j++)
        if (appointments[i][j] != null && appointments[i][j].hasSnsNumber(snsNumber)) return appointments[i][j];

    throw new AppointmentNotFoundException("This SNS User does not have an appointment for today.");
  }
}
