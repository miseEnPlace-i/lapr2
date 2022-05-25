package app.domain.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Appointment class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class Appointment {
  private String snsNumber;
  private Calendar date;
  private VaccinationCenter center;
  private VaccineType vaccineType;
  private boolean sms;

  /**
   * Constructor for Appointment.
   * 
   * @param snsNumber The SNS number of the user this appointment is related to.
   * @param date The date of the appointment.
   */
  public Appointment(String snsNumber, Calendar date, VaccinationCenter center,
      VaccineType vaccineType, boolean sms) {
    this.snsNumber = snsNumber;
    this.date = date;
    this.center = center;
    this.vaccineType = vaccineType;
    this.sms = sms;
  }

  /**
   * Compares the SNS number of this appointment with another number.
   */
  public boolean hasSnsNumber(String snsNumber) {
    return this.snsNumber.equals(snsNumber);
  }

  /**
   * Compares the SNS number of this appointment with another number.
   */
  public String getSnsNumber() {
    return snsNumber;
  }

  /**
   * Checks if the date of this appointment is in the next hour. TODO: the vaccination center will be the one who is going
   * to check if the appointment is in the next slot.
   */
  public boolean isInTheNextHour() {
    Calendar now = Calendar.getInstance();
    now.add(Calendar.HOUR, 1);
    return this.date.after(now);
  }

  public Calendar getDate() {
    return date;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    StringBuilder sb = new StringBuilder();
    sb.append("Appointment: \n");
    sb.append("\nSNS Number: " + snsNumber);
    sb.append("\nDate: " + sdf.format(date.getTime()));
    sb.append("\nVaccination Center: " + center.getName());
    sb.append("\nVaccine Type: " + vaccineType.getDescription());
    sb.append("\nSend SMS: " + (sms ? "Yes" : "No"));

    return sb.toString();
  }
}
