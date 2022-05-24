package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import app.controller.App;
import app.controller.RegisterSNSUserController;
import app.domain.shared.FieldToValidate;
import app.ui.console.utils.Utils;

/**
 * Register SNS User View
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserUI extends RegisterUI<RegisterSNSUserController> {
  public RegisterSNSUserUI() {
    super(new RegisterSNSUserController(App.getInstance().getCompany()));
  }

  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
    System.out.println("\nRegister SNS User UI:");

    String citizenCard = Utils.readLineFromConsoleWithValidation("Citizen Card (xxxxxxxxxLLx): ",
        FieldToValidate.CITIZEN_CARD);
    String snsNumber = Utils.readLineFromConsoleWithValidation("SNS Number (xxxxxxxxx): ",
        FieldToValidate.SNS_NUMBER);
    String name = Utils.readLineFromConsole("Name: ");
    Date birthDay = Utils.readDateFromConsole("Birthday (dd/MM/yyyy): ");
    String phoneNumber = Utils.readLineFromConsoleWithValidation("Phone Number (+351xxxxxxxxx): ",
        FieldToValidate.PHONE_NUMBER);
    String email = Utils.readLineFromConsoleWithValidation("Email: ", FieldToValidate.EMAIL);
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
