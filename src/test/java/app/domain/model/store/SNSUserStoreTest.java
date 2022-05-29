package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.shared.Gender;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>~
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class SNSUserStoreTest {
  private SNSUserStore store;
  private AuthFacade authFacade;
  private SNSUser snsUser;

  @Before
  public void setUp() {
    Company company = new Company("abc", "12345");
    authFacade = company.getAuthFacade();
    store = company.getSNSUserStore();
    Date c = new Date();
    snsUser = new SNSUser("123456789ZZ1", "123456789", "name", c, Gender.MALE, "+351211111111", "email@email.com", "address");
  }

  /**
   * Test if it is possible to add a new SNS User object to the store.
   */
  @Test
  public void ensureAddSNSUserIsWorkingCorrectly() {
    assertEquals(store.size(), 0);
    store.saveSNSUser(snsUser);
    assertEquals(store.size(), 1);
  }

  /**
   * Test that checkDuplicates method is working.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCheckDuplicatesIsWorkingCorrectly() {
    store.saveSNSUser(snsUser);
    store.validateSNSUser(snsUser);
  }

  /**
   * Test that validate method is working for null objects.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = Error.class)
  public void ensureValidateForNullWorksAsExpected() {
    store.validateSNSUser(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureValidateForExistingEmailInAuthFacade() {
    authFacade.addUser("name", "email@email.com", "123456");
    store.validateSNSUser(snsUser);
  }



  //Check that it is possible to upload a list with a repeated user in the middle
  //There are 2 valid users, so it should read 2 users and not stop when failing to register
  @Test
  public void ensureReadAndUploadFromListWhitRepeatedUser() throws ParseException {
    int size = store.size();

    List<String[]> list = new ArrayList<>();
    String[] user1Data = {"joao","Male","14/12/2003","rua das flores","+351919993999","joao@gmail.com","123263189","155424041ZY0"};
    list.add(user1Data);
    list.add(user1Data);
    String[] user2Data = {"paulo","Male","14/12/2003","rua das flores","+351919999999","paulito@gmail.com","123789456","096824379ZX8"};
    list.add(user2Data);

    store.registerListOfUsers(list);

    assertEquals(store.size() - size, 2);

  }

  //Check that it is possible to upload a list with a invalid user in the middle
  //There are 2 valid users, so it should read 2 users and not stop when failing to register
  @Test
  public void ensureReadAndUploadFromListWhitInvalidUser() throws ParseException {
    int size = store.size();

    List<String[]> list = new ArrayList<>();
    String[] user1Data = {"joao","Male","14/12/2003","rua das flores","+351919993999","joao@gmail.com","123263189","155424041ZY0"};
    list.add(user1Data);
    String[] user2Data = {"","","","","","","",""};
    list.add(user2Data);
    String[] user3Data = {"paulo","Male","14/12/2003","rua das flores","+351919999999","paulito@gmail.com","123789456","096824379ZX8"};
    list.add(user3Data);

    store.registerListOfUsers(list);

    assertEquals(store.size() - size, 2);

  }


  //Check that it is possible to upload a list with only valid user in the middle
  @Test
  public void ensureReadAndUploadFromListWhitValidUser() throws ParseException {
    int size = store.size();

    List<String[]> list = new ArrayList<>();
    String[] user1Data = {"joao","Male","14/12/2003","rua das flores","+351919993999","joao@gmail.com","123263189","155424041ZY0"};
    list.add(user1Data);
    String[] user2Data = {"paulo","Male","14/12/2003","rua das flores","+351919999999","paulito@gmail.com","123789456","096824379ZX8"};
    list.add(user2Data);

    store.registerListOfUsers(list);

    assertEquals(store.size() - size, 2);

  }


}
