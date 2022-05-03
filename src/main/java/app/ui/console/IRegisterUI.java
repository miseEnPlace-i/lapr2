package app.ui.console;

import java.text.ParseException;

/**
 * Interface for all the classes that will be used as UI for a register action Ex.: Specify a New Vaccine Type; Register
 * a new SNS User
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public interface IRegisterUI {
  void insertData() throws IllegalArgumentException, ParseException;

  boolean confirmData();
}
