package app.ui.console;

import java.text.ParseException;
import java.util.List;
import app.controller.RegisterNewVaccineTypeController;
import app.ui.console.utils.Utils;

/**
 * Specify new vaccine type
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class RegisterNewVaccineTypeUI extends RegisterUI<RegisterNewVaccineTypeController> {

  public RegisterNewVaccineTypeUI() {
    super(new RegisterNewVaccineTypeController());
  }


  private void displayTechnology(List<String> technologyList) {
    Utils.showList(technologyList, "Vaccine Type Technologies");
  }

  private String selectTechnology(List<String> technologyList) {
    int technologyIndex = Utils.selectsIndex(technologyList);
    return technologyList.get(technologyIndex);
  }

  /**
   * Asks for the new vaccine type data
   */
  @Override
  public void insertData() throws IllegalArgumentException, ParseException {

    System.out.println("\nRegister a new Vaccine type: ");
    String code = Utils.readLineFromConsole("Code: ");
    String designation = Utils.readLineFromConsole("Designation: ");

    List<String> technologyList = super.ctrl.getVaccineTechnologyList();
    displayTechnology(technologyList);

    String tech = selectTechnology(technologyList);

    super.ctrl.create(code, designation, tech);
  }
}
