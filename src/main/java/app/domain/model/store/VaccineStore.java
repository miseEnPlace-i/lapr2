package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledEditorKit.BoldAction;
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
        Vaccine vac = new Vaccine(designation, id, brand, vacType);
        vaccines.add(vac);
    }

    
    public boolean exist(String designation){
        for (Vaccine vac : vaccines)
            if (vac.getDesignation() == designation) return true;
        return false;
    }
  
}
