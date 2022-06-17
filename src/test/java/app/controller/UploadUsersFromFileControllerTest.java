package app.controller;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.store.SNSUserStore;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileControllerTest {
  Company company = new Company("designation", "12345");
  private UploadUsersFromFileController ctrl = new UploadUsersFromFileController(company);

  SNSUserStore store = company.getSNSUserStore();

  // Check is not possible to createCSVReader with null file path
  @Test(expected = IllegalArgumentException.class)
  public void ensureNullPathNotAllowed() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
    String path = null;
    ctrl.createCsvReader(path);
  }

  // Check is not possible to createCSVReader with empty file path
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyPathNotAllowed() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
    String path = "";
    ctrl.createCsvReader(path);
  }

  // Check that it is possible to read and upload from a list with a repeated user in the middle
  // There are 2 valid users, so it should read 2 users and not stop when failing to register
  @Test
  public void ensureReadAndUploadFromListWhitRepeatedUser() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException,
      FileNotFoundException, NoSuchMethodException, InvocationTargetException {
    ctrl.createCsvReader("src/test/resources/SNSUserFilesToTests/UsersFileWithRepeatedUser.csv");

    ctrl.readAndUpload();

    assertEquals(2, store.size());
  }

  // Check that it is possible to read and upload from a list with a invalid user in the middle
  // There are 2 valid users, so it should read 2 users and not stop when failing to register
  @Test
  public void ensureReadAndUploadFromListWhitInvalidUser() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException,
      FileNotFoundException, NoSuchMethodException, InvocationTargetException {
    ctrl.createCsvReader("src/test/resources/SNSUserFilesToTests/UsersFileWithRepeatedUser.csv");

    ctrl.readAndUpload();

    assertEquals(2, store.size());
  }


  // Check that it is possible to read and upload from a list with only valid user in the middle
  @Test
  public void ensureReadAndUploadFromListWhitValidUser() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException,
      FileNotFoundException, NoSuchMethodException, InvocationTargetException {
    ctrl.createCsvReader("src/test/resources/SNSUserFilesToTests/UsersFileWithRepeatedUser.csv");

    ctrl.readAndUpload();

    assertEquals(2, store.size());
  }
}
