package app.ui.console;

import java.text.ParseException;
import java.util.List;
import app.controller.App;
import app.controller.UploadUsersFromFileController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileUI implements Runnable {

  private UploadUsersFromFileController ctrl;

  public UploadUsersFromFileUI() {
    ctrl = new UploadUsersFromFileController(App.getInstance().getCompany());
  }

  @Override
  public void run() {
    System.out.println("\nRegister Vaccine UI:");

    insertData(); // asks to insert file path and instantiates CSVReader

    List<SNSUser> userList = null;
  
      try {
        userList = ctrl.readAndUpload();
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    

    if (userList == null) {
      System.out.println("No users registered!");
    } else {
      displayRegisteredUsers(userList);
    }
  }

  private void displayRegisteredUsers(List<SNSUser> userList) {
    System.out.println("\nRegistered Users:\n");

    int index = 0;
    for (Object o : userList) {
      if(o==null){
        System.out.println(index + ". vailed to regist");
      }else{
      index++;

      System.out.println(index + ". " + o.toString());
      }
    }
    System.out.println("");
    System.out.println("0 - Cancel");
  }

  public void insertData() {
    String filePath = Utils.readLineFromConsole("File Path: ");

    ctrl.createCsvReader(filePath);
  }

}

