package app.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;


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

    private void validatePath(String path) {
        if (path == null || path == "") {
            throw new IllegalArgumentException("File path cannot be null or empty!");
        }
    }


    private void validateDate(Calendar date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
    }

    private void validateCenter(VaccinationCenter center) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null.");
        }
    }


    public Map<Calendar, Integer> getFullyVaccinatedUsersPerDayMap() {
        int dose;

        long nOfDaysBetween = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());

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

    public List<VaccineAdministration> vacAdminList(Calendar day) {
        return center.getVacAdminDayList(day);
    }

}
