package app.domain.model.dto;


import java.util.Calendar;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class AppointmentWithNumberDTO {
  private String snsNumber;
  private Calendar date;
  // private String time; // Calendar has time
  private VaccinationCenter center;
  private VaccineType vaccineType;
  private boolean smsPermission;

  public AppointmentWithNumberDTO(String snsNumber, Calendar date, VaccinationCenter center,
      VaccineType vaccineType, boolean sms) {
    this.snsNumber = snsNumber;
    this.date = date;
    // this.time = time;
    this.center = center;
    this.vaccineType = vaccineType;
    this.smsPermission = sms;
  }

  /**
   * @return String return the snsNumber
   */
  public String getSnsNumber() {
    return snsNumber;
  }

  /**
   * @return Date return the date
   */
  public Calendar getDate() {
    return date;
  }

  /**
   * @return String return the time
   */
  // public String getTime() {
  // return time;
  // }

  /**
   * @return VaccinationCenter return the center
   */
  public VaccinationCenter getCenter() {
    return center;
  }

  /**
   * @return VaccineType return the vaccineType
   */
  public VaccineType getVaccineType() {
    return vaccineType;
  }

  /**
   * @return boolean return the sms
   */
  public boolean getSmsPermission() {
    return smsPermission;
  }

}
