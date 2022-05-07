package app.ui.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.ui.console.utils.Utils;

/**
 * Interface for all the classes that will be used as UI for a register action Ex.: Specify a New Vaccine Type; Register
 * a new SNS User
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public abstract class RegisterUI<T> {
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
