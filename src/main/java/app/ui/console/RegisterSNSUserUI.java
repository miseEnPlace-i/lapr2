package app.ui.console;

import java.text.ParseException;
import java.util.Date;
import app.controller.App;
import app.controller.RegisterSNSUserController;
import app.domain.shared.FieldToValidate;
import app.domain.shared.Gender;
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
    System.out.println("\nRegister SNS User:");

    String citizenCard = Utils.readLineFromConsoleWithValidation("Citizen Card (xxxxxxxxxLLx): ", FieldToValidate.CITIZEN_CARD);
    String snsNumber = Utils.readLineFromConsoleWithValidation("SNS Number (xxxxxxxxx): ", FieldToValidate.SNS_NUMBER);
    String name = Utils.readLineFromConsole("Name: ");
    Date birthDay = Utils.readDateFromConsole("Birthday (dd/MM/yyyy): ");
    String phoneNumber = Utils.readLineFromConsoleWithValidation("Phone Number (+351xxxxxxxxx): ", FieldToValidate.PHONE_NUMBER);
    String email = Utils.readLineFromConsoleWithValidation("Email (example@example.com): ", FieldToValidate.EMAIL);

    System.out.println("Address:");
    String addressStreet = Utils.readLineFromConsole("Street: ");
    int addressNumber = Utils.readIntegerFromConsole("Number: ");
    String postalCode = Utils.readLineFromConsoleWithValidation("Postal Code: ", FieldToValidate.POSTAL_CODE);
    String addressCity = Utils.readLineFromConsole("City: ");

    Gender g = (Gender) Utils.showAndSelectOneEnum(Gender.values(), "\nSelect a gender:");

    super.ctrl.create(citizenCard, snsNumber, name, birthDay, g, phoneNumber, email, addressStreet, addressNumber, postalCode, addressCity);
  }
}
