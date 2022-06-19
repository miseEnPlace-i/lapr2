package app.ui.console;

import java.util.List;
import app.controller.App;
import app.controller.UploadUsersFromFileController;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

/**
 * UploadUsersFromFileUI UI
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileUI implements Runnable {

  private UploadUsersFromFileController ctrl;

  public UploadUsersFromFileUI() {
    App app = App.getInstance();
    Company comp = app.getCompany();
    ctrl = new UploadUsersFromFileController(comp);
  }

  @Override
  public void run() {
    System.out.println("\nRegister Vaccine UI:");

    String filePath = insertData();

    String message = "Are you sure you want to upload users from file: ";

    String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);;

    message += (fileName + " (y/n)");

    if (!Utils.confirm(message)) return;

    List<SNSUser> userList = null;

    try {
      userList = ctrl.readAndUpload();
    } catch (Exception e) {
      System.out.println("File not found!");
    }

    if (userList == null) {
      System.out.println("No users registered!");
    } else {
      displayRegisteredUsers(userList);
    }
  }

  private void displayRegisteredUsers(List<SNSUser> userList) {
    System.out.println("\nRegistered Users Info:\n");

    int index = 0;
    for (Object o : userList) {
      index++;

      if (o == null) {
        System.out.println(index + ". Failed to register\n  ");
      } else {
        System.out.println(index + ". " + o.toString());
      }

    }
    System.out.println("");

  }

  /**
   * Asks to insert the file Path
   * 
   * @return returns the file path entered
   */
  public String insertData() {
    String filePath = Utils.readLineFromConsole("File Path: ");

    ctrl.createCsvReader(filePath);

    return filePath;
  }

}

