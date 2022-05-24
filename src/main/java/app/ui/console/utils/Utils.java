package app.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import app.domain.shared.FieldToValidate;
import app.service.FormatVerifier;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

  static public String readLineFromConsole(String prompt) {
    try {
      System.out.println("\n" + prompt);

      InputStreamReader converter = new InputStreamReader(System.in);
      BufferedReader in = new BufferedReader(converter);

      return in.readLine();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  static public String readLineFromConsoleWithValidation(String prompt, FieldToValidate field) {
    boolean valid = false;
    String answer = "";

    do {
      try {
        System.out.println("\n" + prompt);

        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);

        answer = in.readLine();

        Method validator = FormatVerifier.getValidationMethodForField(field.toString());

        try {
          valid = (Boolean) validator.invoke(Class.class, answer);
        } catch (Exception e) {
          System.out.println(e);
        }
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }

      if (!valid) System.out.printf("%s is not valid!%n", field);
    } while (!valid);

    return answer;
  }

  static public int readIntegerFromConsole(String prompt) {
    do {
      try {
        String input = readLineFromConsole(prompt);

        int value = Integer.parseInt(input);

        return value;
      } catch (NumberFormatException ex) {
        System.out.println("Invalid input! Please try again.\n");
        // Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      }
    } while (true);
  }

  static public double readDoubleFromConsole(String prompt) {
    do {
      try {
        String input = readLineFromConsole(prompt);

        double value = Double.parseDouble(input);

        return value;
      } catch (NumberFormatException ex) {
        System.out.println("Invalid input! Please try again.\n");
        // Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      }
    } while (true);
  }

  static public Date readDateFromConsole(String prompt) {
    do {
      try {
        String strDate = readLineFromConsole(prompt);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date date = df.parse(strDate);

        return date;
      } catch (ParseException ex) {
        System.out.println("Invalid input! Please try again.\n");
        // Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
      }
    } while (true);
  }

  static public boolean confirm(String message) {
    String input;
    do {
      input = Utils.readLineFromConsole("\n" + message + "\n");
    } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

    return input.equalsIgnoreCase("s");
  }

  static public Object showAndSelectOne(List list, String header) {
    showList(list, header);
    return selectsObject(list);
  }

  static public int showAndSelectIndex(List list, String header) {
    showList(list, header);
    return selectsIndex(list);
  }

  static public void showList(List list, String header) {
    System.out.println(header);

    int index = 0;
    for (Object o : list) {
      index++;

      System.out.println(index + ". " + o.toString());
    }
    System.out.println("");
    System.out.println("0 - Cancel");
  }

  static public Object selectsObject(List list) {
    String input;
    Integer value;
    do {
      input = Utils.readLineFromConsole("Type your option: ");
      value = Integer.valueOf(input);
    } while (value < 0 || value > list.size());

    if (value == 0) {
      return null;
    } else {
      return list.get(value - 1);
    }
  }

  static public int selectsIndex(List list) {
    int value = -1;
    do {
      value = Utils.readIntegerFromConsole("Type your option: ");
    } while (value < 0 || value > list.size());

    return value - 1;
  }
}
