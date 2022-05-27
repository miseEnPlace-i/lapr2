package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
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
    snsUser = new SNSUser("123456789ZZ1", "123456789", "name", c, 'M', "+351211111111", "email@email.com", "address");
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
}
