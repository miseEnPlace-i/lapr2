package app.ui.console;

import java.text.ParseException;
import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;

/**
 * Specify new vaccine type
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */

public class RegisterNewVaccineTypeUI extends RegisterUI implements Runnable {
  private SpecifyNewVaccineTypeController ctrl;

  public RegisterNewVaccineTypeUI() {
    ctrl = new SpecifyNewVaccineTypeController();
  }

  /**
   * Calls all the methods to successfully create a new vaccine type
   */

  @Override
  public void run() {
    try {
      insertData();
    } catch (Exception e) {
      System.out.println(String.format("Error: %s\n", e.getMessage()));
    }

    boolean confirmed = confirmData(ctrl.stringifyData());

    if (confirmed) {
      ctrl.save();
      System.out.println("SNS User successfully registered!");
    }
  }

  /**
   * Asks for the new vaccine type data
   */

  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
    System.out.println("\nRegister a new Vaccine type: ");
    String code = Utils.readLineFromConsole("Code: ");
    String designation = Utils.readLineFromConsole("Designation: ");
    String technology = Utils.readLineFromConsole("Technology: ");

    ctrl.create(code, designation, technology);
  }
}
