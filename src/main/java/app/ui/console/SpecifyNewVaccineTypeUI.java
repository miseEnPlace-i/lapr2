package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;

/**
 * Specify new vaccine type
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeUI implements Runnable, IRegisterUI {
  private SpecifyNewVaccineTypeController ctrl;

  public SpecifyNewVaccineTypeUI() {
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

    boolean confirmed = confirmData();

    if (confirmed) {
      ctrl.saveVaccineType();
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

    ctrl.addVaccineType(code, designation, technology);

  }

  /**
   * Confirms all the data
   * 
   * @return return "true" if correct or "false" if incorrect
   */

  @Override
  public boolean confirmData() {
    System.out.println("\nPlease confirm the data below.\n");
    String vaccineTypeData = ctrl.toString();
    System.out.println(vaccineTypeData);

    List<String> options = new ArrayList<String>();
    options.add("y");
    options.add("n");
    Object input = Utils.showAndSelectOne(options, "Is this information correct? (y/n):  ");
    String inputStr = (String) input;

    return inputStr.equals("y");
  }
}
