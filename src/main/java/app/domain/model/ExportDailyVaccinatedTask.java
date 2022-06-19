package app.domain.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;
import app.service.FileUtils;

/**
 * ExportDailyVaccinatedTask
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class ExportDailyVaccinatedTask extends TimerTask {

  String filePath;
  VaccinationCenterStore vacCenterSt;
  VaccineTypeStore vacTypeSt;
  char separator;

  /**
   * ExportDailyVaccinatedTask
   * 
   * @param filePath the file path
   * @param separator the separator
   * @param vacCenterSt the vaccinationCenter store
   * @param vacTypeSt the vaccineType store
   */
  public ExportDailyVaccinatedTask(String filePath, char separator, VaccinationCenterStore vacCenterSt, VaccineTypeStore vacTypeSt) {
    this.filePath = filePath;
    this.vacCenterSt = vacCenterSt;
    this.vacTypeSt = vacTypeSt;
    this.separator = separator;
  }

  @Override
  public void run() {
    HashMap<VaccinationCenter, HashMap> dataMap = new HashMap<VaccinationCenter, HashMap>();

    List<VaccinationCenter> centerLst = vacCenterSt.getListOfVaccinationCenters();
    List<VaccineType> vacTypeLst = vacTypeSt.getListOfVaccineTypes();

    for (int i = 0; i < centerLst.size(); i++) {
      List<VaccineAdministration> vacAdminList = centerLst.get(i).getVacAdminFromTodayList();

      HashMap<VaccineType, Integer> centerDataMap = new HashMap<VaccineType, Integer>();

      for (int j = 0; j < vacAdminList.size(); j++) {
        Vaccine vaccine = vacAdminList.get(j).getVaccine();

        VaccineType vacType = vaccine.getVacType();

        centerDataMap.merge(vacType, 1, Integer::sum);
      }
      dataMap.put(centerLst.get(i), centerDataMap);
    }

    Calendar today = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    String filepath = this.filePath + format.format(today.getTime()) + ".csv";
    String content = "Number of Vaccinated people in day " + format.format(today.getTime()) + "\n" + convertToString(centerLst, vacTypeLst, dataMap);
    FileUtils.writeToFile(filepath, content);
  }

  /**
   * 
   * @param centers List of Vaccination Centers.
   * @param types List of Vaccine Types.
   * @param data Map of Vaccination Centers and their Vaccine Types and their respective counts.
   * @return Readable String with the data in CSV format.
   */
  public String convertToString(List<VaccinationCenter> centers, List<VaccineType> types, HashMap<VaccinationCenter, HashMap> data) {
    String result = "Center;";

    // HEADER
    for (int i = 0; i < types.size(); i++)
      result += types.get(i).getDescription() + this.separator;

    // removes last ; and changes line
    result = result.substring(0, result.length() - 1) + "\n";


    for (int i = 0; i < centers.size(); i++) {
      result += centers.get(i).getName() + this.separator;
      for (int j = 0; j < types.size(); j++) {

        int value = data.get(centers.get(i)).get(types.get(j)) != null ? (int) data.get(centers.get(i)).get(types.get(j)) : 0;

        result += String.valueOf(value) + this.separator;
      }
      // removes last ; and changes line
      result = result.substring(0, result.length() - 1) + "\n";
    }
    return result;
  }
}
