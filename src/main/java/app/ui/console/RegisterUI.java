package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.controller.IRegisterController;
import app.exception.AppointmentNotFoundException;
import app.ui.console.utils.Utils;

/**
 * Interface for all the classes that will be used as UI for a register action Ex.: Specify a New Vaccine Type; Register
 * a new SNS User
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public abstract class RegisterUI<T extends IRegisterController> implements Runnable {
  T ctrl;

  public RegisterUI(T ctrl) {
    this.ctrl = ctrl;
  }

  @Override
  public void run() {
    try {
      insertData();

      boolean confirmed;

      try {
        confirmed = confirmData(ctrl.stringifyData());
      } catch (Exception ex) {
        confirmed = false;
      }

      if (confirmed) {
        try {
          ctrl.save();
        } catch (Exception e) {
          System.out.println("\nError: " + e.getMessage());
        }
        System.out.printf("%n%s successfully registered!%n", ctrl.getResourceName());
      } else
        System.out.printf("%nOperation canceled! %s not registered!%n", ctrl.getResourceName());
    } catch (Exception e) {
      System.out.printf("%nError: %s%n", e.getMessage());
    }
  }

  public abstract void insertData()
      throws IllegalArgumentException, ParseException, AppointmentNotFoundException;

  public boolean confirmData(String data) {
    System.out.println("\nPlease confirm the data below.\n");

    System.out.println(data);

    List<String> options = new ArrayList<String>();
    options.add("Yes, it is correct.");
    options.add("No, it is not correct.");
    int index = Utils.showAndSelectIndex(options, "\nIs this information correct?: (1 or 2)  ");

    return index == 0;
  }
}
