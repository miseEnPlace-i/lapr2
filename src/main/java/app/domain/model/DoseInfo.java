package app.domain.model;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class DoseInfo {
    private int dosage;
    private int timeSinceLastDose;

    public DoseInfo( int dosage, int timeSinceLastDose){
        validateDosage(dosage);
        validateTimeSinceLastDose(timeSinceLastDose);

        this.dosage = dosage;
        this.timeSinceLastDose = timeSinceLastDose;
    }

    public void validateDosage(int dosage){
        if(dosage < 0){
            throw new IllegalArgumentException("The dosage must be positive.");
        }
    }   
    
    public void validateTimeSinceLastDose(int timeSinceLastDose){
        if(timeSinceLastDose < 0){
            throw new IllegalArgumentException("The time since last dose must be positive.");
        }
    } 
  
    
}
