package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.controller.IRegisterController;
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

      boolean confirmed = confirmData(ctrl.stringifyData());

      if (confirmed) {
        ctrl.save();
        System.out.printf("%n%s successfully registered!", ctrl.getResourceName());
      }
    } catch (Exception e) {
      System.out.printf("%nError: %s%n", e.getMessage());
    }
  }

  public abstract void insertData() throws IllegalArgumentException, ParseException;

  public boolean confirmData(String data) {
    System.out.println("\nPlease confirm the data below.\n");

    System.out.println(data);

    List<String> options = new ArrayList<String>();
    options.add("y");
    options.add("n");
    Object input = Utils.showAndSelectOne(options, "Is this information correct? (y/n):  ");
    String inputStr = (String) input;

    return inputStr.equals("y");
  }
}
