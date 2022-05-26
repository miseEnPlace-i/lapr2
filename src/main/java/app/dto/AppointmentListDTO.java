package app.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Appointment List DTO.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class AppointmentListDTO {
  private String snsNumber;
  private String snsName;
  private Calendar date;
  private String centerName;
  private String vaccineType;

  public AppointmentListDTO(String snsNumber, String snsName, Calendar date, String centerName, String vaccineType) {
    this.snsNumber = snsNumber;
    this.snsName = snsName;
    this.date = date;
    this.centerName = centerName;
    this.vaccineType = vaccineType;
  }

  /**
   * @return String the SNS User number.
   */
  public String getSnsNumber() {
    return this.snsNumber;
  }

  /**
   * @return String the SNS User name.
   */
  public String getSnsName() {
    return this.snsName;
  }

  /**
   * @return Date the appointment date.
   */
  public Calendar getDate() {
    return this.date;
  }

  /**
   * @return String the center name.
   */
  public String getCenterName() {
    return this.centerName;
  }

  /**
   * @return String the vaccine type designation.
   */
  public String getVaccineType() {
    return this.vaccineType;
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    StringBuilder sb = new StringBuilder();
    sb.append("Appointment\n");
    sb.append(String.format("\tSNS User: %s (%s)\n", this.snsName, this.snsNumber));
    sb.append(String.format("\tDate: %s\n", sdf.format(this.date.getTime())));
    sb.append(String.format("\tCenter: %s\n", this.centerName));
    sb.append(String.format("\tVaccine type: %s", this.vaccineType));

    return sb.toString();
  }
}
