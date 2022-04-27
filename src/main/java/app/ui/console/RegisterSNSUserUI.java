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
        SNSUser snsUser = insertSNSUserData();
        boolean confirmed = confirmData(snsUser);

        if (confirmed) {
            ctrl.saveSNSUser(snsUser);
        }
    }

    /**
     * 
     * @return
     */
    private SNSUser insertSNSUserData() {
        System.out.println("\nRegister SNS User UI:");

        String citizenCard = Utils.readLineFromConsole("Citizen Card: ");
        String snsNumber = Utils.readLineFromConsole("SNS Number: ");
        String name = Utils.readLineFromConsole("Name: ");
        String birthDay = Utils.readLineFromConsole("Birthday: ");
        String phoneNumber = Utils.readLineFromConsole("Phone Number: ");
        String email = Utils.readLineFromConsole("Email: ");
        String address = Utils.readLineFromConsole("Address: ");
        char gender = 'm';

        // select gender
        List<String> genders = new ArrayList<String>();
        genders.add("m");
        genders.add("f");
        Object input = Utils.showAndSelectOne(genders, "Select a gender: ");
        String inputStr = (String) input;
        gender = inputStr.charAt(0);

        SNSUser instance = ctrl.createSNSUser(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email,
                address);
        return instance;
    }

    private boolean confirmData(SNSUser snsUser) {
        System.out.println("Please confirm the data below.\n");
        System.out.printf("Citizen Card: %s\n", snsUser.getCitizenCard());
        System.out.printf("SNS Number: %s\n", snsUser.getSnsNumber());
        System.out.printf("Name: %s\n", snsUser.getName());
        System.out.printf("Gender: %s\n", snsUser.getGender());
        System.out.printf("Birthday: %s\n", snsUser.getBirthDay());
        System.out.printf("Phone Number: %s\n", snsUser.getPhoneNumber());
        System.out.printf("Email: %s\n", snsUser.getEmail());
        System.out.printf("Address: %s\n", snsUser.getAddress());

        System.out.print("\nIs this information correct? (y/n): ");

        List<String> options = new ArrayList<String>();
        options.add("y");
        options.add("n");
        Object input = Utils.showAndSelectOne(options, "Select a gender: ");
        String inputStr = (String) input;

        return inputStr.equals("y");
    }
}
