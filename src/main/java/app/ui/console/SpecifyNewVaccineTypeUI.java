package app.ui.console;

import java.text.ParseException;
import app.controller.SpecifyNewVaccineTypeController;

public class SpecifyNewVaccineTypeUI implements Runnable, IRegisterUI {
  private SpecifyNewVaccineTypeController ctrl;

  public SpecifyNewVaccineTypeUI() {
    ctrl = new SpecifyNewVaccineTypeController();
  }

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

  @Override
  public void insertData() throws IllegalArgumentException, ParseException {
    // TODO
  }

  @Override
  public boolean confirmData() {
    // TODO
    return false;
  }
}
