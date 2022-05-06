package app.controller;

import java.util.List;
import app.domain.model.AdminProcess;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;


/**
 * Create Vaccine Controller
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class RegisterVaccineController {
    private App app;
    private Company company;
    private VaccineStore vaccineStore;
    private VaccineTypeStore vaccineTypeStore;
    private Vaccine vac;
    private AdminProcess ap;
    private DoseInfo di;

    /**
    * Constructor for CreateVaccineController
    */
    public RegisterVaccineController() {
        this.app = App.getInstance();
        this.company = this.app.getCompany();
        this.vaccineStore = this.company.getVaccineStore();
        this.vaccineTypeStore = this.company.getVaccineTypeStore();
        this.vac = null;
        this.ap = null;
        this.di = null;
    }


    public List<VaccineType> getVacTypes() {
        return vaccineTypeStore.getList();
    }

    public void createVaccine(String designation, String id, String brand, String vacTypeId) {
        VaccineType vacType = vaccineTypeStore.getVacTypeById(vacTypeId);
        this.vac = vaccineStore.createVaccine(designation, id, brand, vacType);
    }

    public void saveVaccine() {
        vaccineStore.saveVaccine(vac);
    }

    public void createAdminProc(int minAge, int maxAge, int numberOfDoses) {
        ap = vac.createAdminProc(minAge, maxAge, numberOfDoses);
    }
    
    public void saveAdminProc() {
        vac.getAdminProcList().addAdminProc(ap);;
    }

    public void createDoseInfo(int dosage, int timeSinceLastDose) {
        di = ap.createDoseInfo(dosage, timeSinceLastDose);
    }

    public void saveDoseInfo() {
        ap.getDoseInfoList().addDoseInfo(di);
    }

}
