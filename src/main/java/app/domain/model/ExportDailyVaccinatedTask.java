package app.domain.model;

import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;

public class ExportDailyVaccinatedTask extends TimerTask {

    String filePath;
    VaccinationCenterStore vacCenterSt;
    VaccineTypeStore vacTypeSt;

    public ExportDailyVaccinatedTask(String filePath, VaccinationCenterStore vacCenterSt, VaccineTypeStore vacTypeSt){
        this.filePath = filePath;
        this.vacCenterSt = vacCenterSt;
        this.vacTypeSt = vacTypeSt;
    }

    @Override
    public void run() {
        HashMap<VaccinationCenter, HashMap> dataMap = new HashMap<VaccinationCenter, HashMap>();

        List<VaccinationCenter> centerLst = vacCenterSt.getListOfVaccinationCenters();
        List<VaccineType> vacTypeLst = vacTypeSt.getListOfVaccineTypes(); 
        
        for (int i = 0; i < centerLst.size(); i++) {
            List<VaccineAdministration> vacAdminList = centerLst.get(i).getVaccineAdministrationFromYesterdayList();

            HashMap<VaccineType, Integer> centerDataMap = new HashMap<VaccineType, Integer>();
            
            for (int j = 0; j < vacAdminList.size(); j++) {
                Vaccine vaccine = vacAdminList.get(j).getVaccine();
                
                VaccineType vacType = vaccine.getVacType();

                centerDataMap.merge(vacType, 1, Integer::sum);                             
            }
            dataMap.put(centerLst.get(i), centerDataMap);
        }
    }
    
}
