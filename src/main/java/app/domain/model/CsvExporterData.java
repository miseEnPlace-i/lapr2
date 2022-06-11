package app.domain.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import app.service.FormatVerifier;
import app.service.TimeUtils;


public class CsvExporterData {

    private String filePath;
    private Date startDate;
    private Date endDate;
    private VaccinationCenter center;
    private Map<String, Integer> data;
    private int snsUserAge;
    private SNSUser snsUser;
    private Vaccine vaccine;

    public CsvExporterData(String path, Date start, Date end, VaccinationCenter center) {
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


    private void validateDate(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
    }

    private void validateCenter(VaccinationCenter center) {
        if (center == null) {
            throw new IllegalArgumentException("Center cannot be null.");
        }
    }


    public void getAllData() {
        for (int i = 0; i < endDate.getTime() - startDate.getTime(); i++) {
            Date date = new Date();
            // TODO: vacAdminList();
            // for (int j = 0; j < vacAdminList.size(); j++) {
            snsUserAge = (int) (date.getTime() - snsUser.getBirthDay().getTime());
            // }
        }
    }

    // public List<VaccineAdministration> vacAdminList() {}



}
