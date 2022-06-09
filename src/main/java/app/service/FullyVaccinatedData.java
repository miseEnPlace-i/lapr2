package app.service;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;

/**
 * FullyVaccinatedData class
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class FullyVaccinatedData {

    private String filePath;
    private Calendar startDate;
    private Calendar endDate;
    private VaccinationCenter center;
    private Map<Calendar, Integer> data;
    private int snsUserAge;
    private SNSUser snsUser;
    private Vaccine vaccine;
    private boolean fullyVaccinated;

    /**
     * Constructor of the class
     * 
     * @param path the file path
     * @param start the start date of the statistics
     * @param end the end date of the statistics
     * @param center the coordinator center of the statistics
     */
    public FullyVaccinatedData(String path, Calendar start, Calendar end, VaccinationCenter center) {
        validatePath(path);
        validateDate(start);
        validateDate(end);
        validateCenter(center);

        this.filePath = path;
        this.startDate = start;
        this.endDate = end;
        this.center = center;
    }

    /**
     * Validate file path
     * 
     * @param path
     */
    private void validatePath(String path) {
        if (path == null || path == "") {
            throw new IllegalArgumentException("File path cannot be null or empty!");
        }
    }


    /**
     * Validate dates
     * 
     * @param date
     */
    private void validateDate(Calendar date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
    }

    /**
     * Validate coordinator center
     * 
     * @param center
     */
    private void validateCenter(VaccinationCenter center) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null.");
        }
    }


    /**
     * Creates a hashMap to get all data needed for each day and for each VaccineAdministration
     * 
     * @return hashMap of all the data needed to do the statistics
     */
    public Map<Calendar, Integer> getFullyVaccinatedUsersPerDayMap() {
        int dose;

        long nOfDaysBetween = getDaysBetweenTwoDates();

        Calendar currentDay = Calendar.getInstance();
        currentDay.setTime(startDate.getTime());

        for (int i = 0; i < nOfDaysBetween; i++) {

            int nOfFullyVaccinated = 0;

            List<VaccineAdministration> vacAdminList = vacAdminList(currentDay);

            for (int j = 0; j < vacAdminList.size(); j++) {

                snsUserAge = CalendarUtils.calculateAge(snsUser.getBirthDay());

                vaccine = vacAdminList.get(j).getVaccine();

                dose = vacAdminList.get(j).getDoseNumber();

                fullyVaccinated = vaccine.checkUserFullyVaccinated(snsUserAge, dose);

                if (fullyVaccinated) {
                    nOfFullyVaccinated += 1;
                }
            }

            data.put(currentDay, nOfFullyVaccinated);

            currentDay.add(Calendar.DAY_OF_MONTH, 1);
        }

        return data;
    }

    /**
     * Gets vaccine administration list
     * 
     * @param day the specific day to get the list of the center
     * @return a List of VaccineAdministrations of that day
     */
    public List<VaccineAdministration> vacAdminList(Calendar day) {
        return center.getVacAdminDayList(day);
    }

    /**
     * Gets number of days between two dates
     */
    public long getDaysBetweenTwoDates() {
        return ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
    }

}
