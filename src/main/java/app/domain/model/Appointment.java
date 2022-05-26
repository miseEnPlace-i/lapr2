package app.domain.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Appointment class.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class Appointment {
  private SNSUser snsUser;
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
  public Appointment(SNSUser snsUser, Calendar date, VaccinationCenter center, VaccineType vaccineType, boolean sms) {
    this.snsUser = snsUser;
    this.date = date;
    this.center = center;
    this.vaccineType = vaccineType;
    this.sms = sms;
  }

  /**
   * Compares the SNS number of this appointment with another number.
   */
  public boolean hasSnsNumber(String snsNumber) {
    return this.snsUser.getSnsNumber().equals(snsNumber);
  }

  /**
   * Compares the SNS number of this appointment with another number.
   */
  public boolean hasVaccineType(VaccineType vaccineType) {
    return this.vaccineType.equals(vaccineType);
  }


  /**
   * Returns the SNS User.
   */
  public SNSUser getSnsUser() {
    return this.snsUser;
  }

  public Calendar getDate() {
    return date;
  }

  public VaccineType getVaccineType() {
    return vaccineType;
  }

  public boolean isSms() {
    return sms;
  }

  public VaccinationCenter getVaccinationCenter() {
    return center;
  }
  
  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    StringBuilder sb = new StringBuilder();
    sb.append("Appointment: \n");
    sb.append("\nSNS Number: " + this.snsUser.getSnsNumber());
    sb.append("\nDate: " + sdf.format(date.getTime()));
    sb.append("\nVaccination Center: " + center.getName());
    sb.append("\nVaccine Type: " + vaccineType.getDescription());
    sb.append("\nSend SMS: " + (sms ? "Yes" : "No"));

    return sb.toString();
  }
}
