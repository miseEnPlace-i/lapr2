package app.domain.model.store;

import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.SNSUser;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class SNSUserStoreTest {

  AuthFacade authFacade;
  SNSUserStore store;
  SNSUser snsUser;

  @Before
  public void setUp() {
    authFacade = new AuthFacade();
    store = new SNSUserStore(authFacade);
    Calendar c = Calendar.getInstance();
    snsUser = new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351211111111",
        "email@email.com", "address");
  }

  /**
   * Test if it is possible to add a new SNS User object to the store.
   */
  @Test
  public void ensureAddSNSUserIsWorkingCorrectly() {
    assert store.size() == 0;
    store.saveSNSUser(snsUser);
    assert store.size() == 1;
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

  /**
   * Test that validate method is working for already inserted system users.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureValidateForInsertedSystemUserWorksAsExpected() {
    authFacade.addUser("test", "email@email.com", "123456");
    store.validateSNSUser(snsUser);
  }
}
