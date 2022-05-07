package app.ui.console;

import java.util.ArrayList;
import java.util.List;
import app.controller.RegisterVaccineController;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;

public class RegisterVaccineUI {
    
/**
 * Register Employee View
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */

    private RegisterVaccineController controller;

    public RegisterVaccineUI(){
        this.controller = new RegisterVaccineController();
    }

    public void run(){
        System.out.println("\nRegister Employee UI:");

        List<VaccineType> vacTypes = controller.getVacTypes(); //all available vaccine types
        displayVacTypes(vacTypes);

        VaccineType vacType = selectVacType(vacTypes); //asks to select the vaccine type
        String vacTypeId = vacType.getCode();

        insertVaccineData(vacTypeId); //asks to insert vaccine data and instantiate and validate a new vaccine

        //CREATE ADMIN PROCESS
        boolean confirmed = askCreateAdminProc(); //asks the user if he wants to add a new ap
        while(confirmed){
            //CREATE ADMIN PROCESS UI

            
            confirmed = askCreateAdminProc(); //asks the user if he wants to add a new ap
        }
        
    }

    //DISPLAY ALL AVAILABLE VACCINE TYPES
    private void displayVacTypes(List<VaccineType> vacTypes) {
        Utils.showList(vacTypes, "Employee Roles");
    }

    //RETURNS VACCINE TYPE ID SELECTED
    private VaccineType selectVacType(List<VaccineType> vacTypes) {
        int vacTypeId = Utils.selectsIndex(vacTypes);
        return vacTypes.get(vacTypeId);
    }

    private void insertVaccineData(String vacTypeId) {
        String designation = Utils.readLineFromConsole("Name: ");
        String brand = Utils.readLineFromConsole("Address: ");
        String id = Utils.readLineFromConsole("Phone Number: ");
        
        controller.createVaccine(designation, brand, id, vacTypeId);

        controller.validateVaccine();

    }

    private boolean askCreateAdminProc() { 
        List<String> options = new ArrayList<String>(); 
        options.add("y");
        options.add("n");
        Object input = Utils.showAndSelectOne(options, "Want to add an adminstration process? (y/n):  ");
        String inputStr = (String) input;
    
        return inputStr.equals("y");
      }

}
