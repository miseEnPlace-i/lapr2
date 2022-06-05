package app.ui.console;


import java.util.ArrayList;
import java.util.List;
import app.controller.VaccinationCenterController;
import app.domain.model.Employee;
import app.ui.console.utils.Utils;

/**
 * Register a new Vaccination Center
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterUI implements Runnable {
    private VaccinationCenterController ctrl;


    public void insertVaccinationCenterData() {
        // TODO: catch exceptions that might occur
        String name = Utils.readLineFromConsole("Name: ");
        String address = Utils.readLineFromConsole("Address: ");
        String email = Utils.readLineFromConsole("Email: ");
        String phone = Utils.readLineFromConsole("Phone Number: ");
        String fax = Utils.readLineFromConsole("Fax Number: ");
        String website = Utils.readLineFromConsole("Website Address: ");
        String openHours = Utils.readLineFromConsole("Opening hours: ");
        String closHours = Utils.readLineFromConsole("Closing hours: ");
        int slotDur = Utils.readIntegerFromConsole("Slot duration: ");
        int maxVac = Utils.readIntegerFromConsole("Maximum vaccines per slot: ");
        Employee coordinator;

        // select coordinator
        boolean flag = false;
        List<Employee> coordinators = new ArrayList<Employee>();
        coordinators = ctrl.getCoordinators();

        do {
            Utils.readLineFromConsole("Select a employee from the list:\n");
            coordinator = (Employee) Utils.showAndSelectOne(coordinators, "Coordinators");
        } while (!flag);

        ctrl.createVaccinationCenter(name, address, email, phone, fax, website, openHours, closHours, slotDur, maxVac, coordinator);
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        insertVaccinationCenterData();
        boolean confirmed = confirmData();

        if (confirmed) {
            ctrl.saveVaccinationCenter();
            System.out.println("Vaccination Center successfully registered!");
        }

    }

    public boolean confirmData() {
        System.out.println("\nPlease confirm the data below.\n");

        List<String> options = new ArrayList<String>();
        options.add("y");
        options.add("n");
        Object input = Utils.showAndSelectOne(options, "Is this information correct? (y/n):  ");
        String inputStr = (String) input;

        return inputStr.equals("y");
    }
}
