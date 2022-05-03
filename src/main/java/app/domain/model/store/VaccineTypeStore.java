package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.VaccineType;


/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class VaccineTypeStore {
    
    private List<VaccineType> vaccineTypes;

    
    public VaccineTypeStore(){
        this.vaccineTypes = new ArrayList<VaccineType>();
    }

    public static VaccineType getVacTypeById(String vacTypeId){
        //TODO
        return null;
    }

    

}
