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

  /**
   * Constructor for RegisterNewVaccineTypeUI
   */
  public RegisterNewVaccineTypeUI() {
    super(new RegisterNewVaccineTypeController());
  }

  /**
   * Displays in the screen all the available technology types
   * 
   * @param technologyList the list of vaccine technology types
   */
  private void displayTechnology(List<String> technologyList) {
    Utils.showList(technologyList, "\nVaccine Type Technologies:");
  }

  /**
   * Selects a vaccine technology type
   * 
   * @param technologyList the list of vaccine technology types
   * @return the index of the type selected
   */
  private String selectTechnology(List<String> technologyList) {
    int technologyIndex = Utils.selectsIndex(technologyList);
    return technologyList.get(technologyIndex);
  }

  /**
   * Asks user for the new vaccine type data
   */
  @Override
  public void insertData() throws IllegalArgumentException, ParseException {

    System.out.println("\nRegister a new Vaccine type: ");
    String code = Utils.readLineFromConsole("Code (xxxxx): ");
    String designation = Utils.readLineFromConsole("Designation: ");

    List<String> technologyList = super.ctrl.getVaccineTechnologyList();
    displayTechnology(technologyList);

    String tech = selectTechnology(technologyList);

    super.ctrl.create(code, designation, tech);
  }
}
