package app.domain.model;

import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import app.service.CalendarUtils;

/**
 * FullyVaccinatedData class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class FullyVaccinatedData {
  private Calendar startDate;
  private Calendar endDate;
  private VaccinationCenter center;
  private int snsUserAge;
  private Vaccine vaccine;
  private boolean fullyVaccinated;

  /**
   * FullyVaccinatedData Constructor
   * 
   * @param fileName the file name
   * @param start the start date of the statistics
   * @param end the end date of the statistics
   * @param center the coordinator center of the statistics
   */
  public FullyVaccinatedData(String fileName, Calendar start, Calendar end, VaccinationCenter center) {
    validateName(fileName);
    validateDateInterval(start, end);
    validateCenter(center);

    this.startDate = start;
    this.endDate = end;
    this.center = center;
  }

  /**
   * Validate file path
   * 
   * @param fileName
   */
  private void validateName(String fileName) {
    if (fileName == null || fileName == "") throw new IllegalArgumentException("File name cannot be null or empty!");
  }

  /**
   * Validates the interval
   * 
   * @param start the start date of the statistics
   * @param end the end date of the statistics
   */
  private void validateDateInterval(Calendar start, Calendar end) {
    if (start == null && end == null) throw new IllegalArgumentException("Date cannot be null.");
    if (start.after(end)) throw new IllegalArgumentException("End date must be after start date.");
    if (end.after(Calendar.getInstance()))
      throw new IllegalArgumentException("It is not possible to export center statistics that contains days in the future.");
  }

  /**
   * Validate selected center
   * 
   * @param center the center to validate
   */
  private void validateCenter(VaccinationCenter center) {
    if (center == null) throw new IllegalArgumentException("Center cannot be null.");
  }

  /**
   * Creates a hashMap to get all data needed for each day and for each VaccineAdministration
   * 
   * @return hashMap of all the data needed to do the statistics
   */
  public LinkedHashMap<Calendar, Integer> getFullyVaccinatedUsersPerDayMap() {
    long nOfDaysBetween = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant()) + 1;
    LinkedHashMap<Calendar, Integer> result = new LinkedHashMap<>();

    for (int i = 0; i < nOfDaysBetween; i++) {
      Calendar currentDay = Calendar.getInstance();
      currentDay.setTime(startDate.getTime());
      currentDay.add(Calendar.DATE, i);

      int nOfFullyVaccinated = 0;

      List<VaccineAdministration> vacAdminList = center.getVacAdminDayList(currentDay);

      for (int j = 0; j < vacAdminList.size(); j++) {
        fullyVaccinated = checkUserFullyVaccinated(vacAdminList, j);

        if (fullyVaccinated) nOfFullyVaccinated += 1;
      }
      result.put(currentDay, nOfFullyVaccinated);
    }

    return result;
  }

  /**
   * Checks if SNS User is fully vaccinated
   * 
   * @param vacAdminList the vaccine administration list
   * @param vacAdminNumber the vaccine administration number from the list
   * @return "true" if user fully vaccinated, "false" otherwise
   */
  public boolean checkUserFullyVaccinated(List<VaccineAdministration> vacAdminList, int vacAdminNumber) {
    snsUserAge = CalendarUtils.calculateAge(vacAdminList.get(vacAdminNumber).getSnsUser().getBirthDay());

    vaccine = vacAdminList.get(vacAdminNumber).getVaccine();

    int dose = vacAdminList.get(vacAdminNumber).getDoseNumber();

    fullyVaccinated = vaccine.checkUserFullyVaccinated(snsUserAge, dose);

    return fullyVaccinated;
  }

  /**
   * Gets data ready to go to a CSV file
   * 
   * @param data
   * @return String with the data formatted to CSV file
   */
  public String toExportFileString(Map<Calendar, Integer> data) {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    StringBuilder sb = new StringBuilder();

    sb.append("Date;NumberOfFullyVaccinatedUsers\n");
    for (Map.Entry<Calendar, Integer> entry : data.entrySet()) {
      sb.append(format.format(entry.getKey().getTime()) + ";" + entry.getValue());
      sb.append("\n");
    }

    return sb.toString();
  }
}
