package app.dto;

public class AppointmentInsertDTO {
  private String snsNumber;
  private String date;
  private String centerName;
  private String vaccineType;
  private String smsPermission;

  public AppointmentInsertDTO(String snsNumber, String date, String centerName, String vaccineType, String sms) {
    this.snsNumber = snsNumber;
    this.date = date;
    this.centerName = centerName;
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
  public String getDate() {
    return date;
  }

  /**
   * @return VaccinationCenter return the center
   */
  public String getCenter() {
    return centerName;
  }

  /**
   * @return VaccineType return the vaccineType
   */
  public String getVaccineType() {
    return vaccineType;
  }

  /**
   * @return boolean return the sms
   */
  public String getSmsPermission() {
    return smsPermission;
  }

  @Override
  public String toString() {

    StringBuilder sb = new StringBuilder();
    sb.append("Appointment: \n");
    sb.append("\nSNS Number: " + getSnsNumber());
    sb.append("\nDate: " + getDate());
    sb.append("\nVaccination Center: " + getCenter());
    sb.append("\nVaccine Type: " + getVaccineType());
    sb.append("\nSend SMS: " + getSmsPermission());

    return sb.toString();
  }
}
