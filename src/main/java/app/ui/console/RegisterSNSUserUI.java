package app.ui.console;

import java.util.ArrayList;
import java.util.List;

import app.controller.RegisterSNSUserController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

/**
 * Register SNS User View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */

public class RegisterSNSUserUI implements Runnable {
    private RegisterSNSUserController ctrl;

    public RegisterSNSUserUI() {
        ctrl = new RegisterSNSUserController();
    }

    public void run() {
        insertSNSUserData();
        boolean confirmed = confirmData();

        if (confirmed) {
            ctrl.saveSNSUser();
            System.out.println("SNS User successfully registered!");
        }
    }

    /**
     * 
     * @return
     */
    private void insertSNSUserData() {
        System.out.println("\nRegister SNS User UI:");

        // TODO: catch exceptions that might occur
        String citizenCard = Utils.readLineFromConsole("Citizen Card: ");
        String snsNumber = Utils.readLineFromConsole("SNS Number: ");
        String name = Utils.readLineFromConsole("Name: ");
        String birthDay = Utils.readLineFromConsole("Birthday: ");
        String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
        String email = Utils.readLineFromConsole("Email: ");
        String address = Utils.readLineFromConsole("Address: ");
        char gender = 'm';

        // select gender
        boolean flag = false;
        List<String> validGenders = new ArrayList<String>();
        validGenders.add("m");
        validGenders.add("f");

        do {
            String input = Utils.readLineFromConsole("Select a gender (M/F): ");
            if (validGenders.contains(input)) {
                gender = input.charAt(0);
                flag = true;
            } else {
                System.out.println("\nInvalid gender.");
            }
        } while (!flag);

        ctrl.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
    }

    private boolean confirmData() {
        System.out.println("\nPlease confirm the data below.\n");
        String stringifiedSNSUser = ctrl.stringifySNSUser();
        System.out.println(stringifiedSNSUser);

        List<String> options = new ArrayList<String>();
        options.add("y");
        options.add("n");
        Object input = Utils.showAndSelectOne(options, "Is this information correct? (y/n):  ");
        String inputStr = (String) input;

        return inputStr.equals("y");
    }
}
