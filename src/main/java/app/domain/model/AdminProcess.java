package app.domain.model;

import app.domain.model.list.DoseInfoList;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class AdminProcess {
    
    private int minAge;
    private int maxAge;
    private int numberOfDoses;
    private DoseInfoList doseInfoList;

    public AdminProcess(int minAge, int maxAge, int numberOfDoses){
        //TODO
    }

    public void createDoseInfo(int dosage, int timeSinceLastDose){
        //TODO
    }
    
    public boolean validateAdminProc(AdminProcess adminProc){
        //TODO
        return true;
    }
}
