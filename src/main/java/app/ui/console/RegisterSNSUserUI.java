package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.controller.RegisterSNSUserController;
import app.ui.console.utils.Utils;

/**
 * Register SNS User View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */

public class RegisterSNSUserUI extends RegisterUI<RegisterSNSUserController> {
  public RegisterSNSUserUI() {
    super(new RegisterSNSUserController());
  }

  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
    System.out.println("\nRegister SNS User UI:");

    String citizenCard = Utils.readLineFromConsole("Citizen Card (xxxxxxxxxLLx): ");
    String snsNumber = Utils.readLineFromConsole("SNS Number (xxxxxxxxx): ");
    String name = Utils.readLineFromConsole("Name: ");
    String birthDay = Utils.readLineFromConsole("Birthday (DD/MM/YYYY): ");
    String phoneNumber = Utils.readLineFromConsole("Phone Number (+351xxxxxxxxx): ");
    String email = Utils.readLineFromConsole("Email: ");
    String address = Utils.readLineFromConsole("Address: ");
    char gender = 'm';

    // select gender
    boolean flag = false;
    List<String> validGenders = new ArrayList<String>();
    validGenders.add("m");
    validGenders.add("f");

    do {
      String input = Utils.readLineFromConsole("Select a gender (M/F): ").toLowerCase();
      if (validGenders.contains(input)) {
        gender = input.charAt(0);
        flag = true;
      } else {
        System.out.println("\nInvalid gender.");
      }
    } while (!flag);

    super.ctrl.create(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }
}
