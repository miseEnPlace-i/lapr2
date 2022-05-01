package app.domain.model;

import app.domain.model.list.AdminProcList;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class Vaccine {

    private String designation;
    private String brand;
    private String id;
    private VaccineType vacType;
    private AdminProcList adminProcList;

    public Vaccine(String designation, String id, String brand, String string){
        //TODO
    }

    public void createAdminProc(int minAge, int maxAge, int numberOfDoses){
        //TODO
    }

    
}
