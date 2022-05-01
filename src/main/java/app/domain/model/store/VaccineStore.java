package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class VaccineStore {

    private List<Vaccine> vaccines;

    
    public VaccineStore(){
        this.vaccines = new ArrayList<Vaccine>();
    }

    public void createVaccine(String designation, String id, String brand, VaccineType vacType){
        //TODO
    }

    public boolean validateVaccine(Vaccine vac){
        //TODO
        return true;
    }
    
  
}
