package app.ui.console;

import java.text.ParseException;
import app.controller.RegisterNewVaccineTypeController;
import app.ui.console.utils.Utils;

/**
 * Specify new vaccine type
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */

public class RegisterNewVaccineTypeUI extends RegisterUI {
  private RegisterNewVaccineTypeController ctrl;

  public RegisterNewVaccineTypeUI() {
    super(new RegisterNewVaccineTypeController());
    this.ctrl = (RegisterNewVaccineTypeController) super.ctrl;
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
