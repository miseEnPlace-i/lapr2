package app.domain.model;

import app.domain.model.list.AdminProcList;
import app.domain.model.store.VaccineTypeStore;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class Vaccine {

    private String designation;
    private String brand;
    private String id;
    private VaccineType vacType;

    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        validateDesignation(designation);
        this.designation = designation;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        validateBrand(brand);
        this.brand = brand;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        validateId(id);
        this.id = id;
    }

    public VaccineType getVacType() {
        return this.vacType;
    }

    public void setVacType(VaccineType vacType){
        this.vacType = vacType;
    }

    private AdminProcList adminProcList;

    public Vaccine(String designation, String id, String brand, VaccineType vacType){
        validateDesignation(designation);
        validateBrand(brand);
        validateId(id);

        this.designation = designation;
        this.brand = brand;
        this.id = id;
        this.vacType = vacType;
    }

    public void validateDesignation(String designation){
        if(designation == "" || designation == null){
            throw new IllegalArgumentException("Designation field is required.");
        }
    }

    public void validateBrand(String brand){
        if(brand == "" || brand == null){
            throw new IllegalArgumentException("Brand field is required.");
        }
    }

    public void validateId(String id){
        if(id == "" || id == null){
            throw new IllegalArgumentException("Id field is required.");
        }
    }

    public void validateVacType(VaccineType vacType){
        if(vacType == null){
            throw new IllegalArgumentException("The vaccine type must exist.");
        }
    }


    public void createAdminProc(int minAge, int maxAge, int numberOfDoses){
        AdminProcess ap = new AdminProcess(minAge, maxAge, numberOfDoses);
        adminProcList.addAdminProc(ap);
    }

    

}
