package app.domain.model;

import app.domain.model.list.DoseInfoList;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class AdminProcess {
    
    private int minAge;
    private int maxAge;
    private int numberOfDoses;

    public int getMinAge() {
        return this.minAge;
    }

    public int getMaxAge() {
        return this.maxAge;
    }

    public void setAgeInterval(int minAge, int maxAge) {
        validateMaxAge(maxAge);
        validateMinAge(minAge);
        validateInterval(minAge, maxAge);
        this.maxAge = maxAge;
        this.minAge = minAge;
    }

    public int getNumberOfDoses() {
        return this.numberOfDoses;
    }

    public void setNumberOfDoses(int numberOfDoses) {
        validateNumberOfDoses(numberOfDoses);
        this.numberOfDoses = numberOfDoses;
    }

    private DoseInfoList doseInfoList;

    public AdminProcess(int minAge, int maxAge, int numberOfDoses){
        validateInterval(minAge, maxAge);
        validateMaxAge(maxAge);
        validateMinAge(minAge);
        validateNumberOfDoses(numberOfDoses);

        this.minAge = minAge;
        this.maxAge = maxAge;
        this.numberOfDoses = numberOfDoses;
        doseInfoList = new DoseInfoList();
    }

    public void validateMinAge(int minAge){
        if(minAge < 0){
            throw new IllegalArgumentException("The minimum age must be positive.");
        }
    }

    public void validateMaxAge(int maxAge){
        if(maxAge < 0){
            throw new IllegalArgumentException("The maximum age must be positive.");
        }
    }

    public void validateNumberOfDoses(int numberOfDoses){
        if(numberOfDoses < 0){
            throw new IllegalArgumentException("The number of doses must be positive.");
        }
    }

    public void validateInterval(int minAge, int maxAge){
        if(minAge > maxAge){
            throw new IllegalArgumentException("The minimum age must be smaller than the maximum.");
        }
    }

    public void createDoseInfo(int dosage, int timeSinceLastDose){
        DoseInfo di = new DoseInfo(dosage, timeSinceLastDose);
        doseInfoList.addDoseInfo(di);
    }
    
}
