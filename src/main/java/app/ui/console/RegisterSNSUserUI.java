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

public class RegisterSNSUserUI extends RegisterUI {
  private static RegisterSNSUserController ctrl = new RegisterSNSUserController();

  public RegisterSNSUserUI() {
    super(ctrl);
  }

  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
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

    ctrl.create(citizenCard, snsNumber, name, birthDay, gender, phoneNumber, email, address);
  }
}
