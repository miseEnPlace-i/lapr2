package app.domain.model.store;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.Employee;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class EmployeeStoreTest {
  AuthFacade authFacade;
  EmployeeStore store;
  EmployeeRoleStore roleStore;
  Employee employee;

  @Before
  public void setUp() {
    Company company = new Company("abc", "12345");
    roleStore = company.getEmployeeRoleStore();
    store = company.getEmployeeStore();
    authFacade = company.getAuthFacade();

    employee = new Employee("00000001", "Joana Maria", "+351123456789", "email@email.com", "Av. da Liberdade", "123456789ZZ1", "NURSE");
  }

  /**
   * Test if it is possible to add a new Employee object to the store.
   */
  @Test
  public void ensureAddEmployeeIsWorkingCorrectly() {
    assertEquals(store.size(), 0);
    store.saveEmployee(employee);
    assertEquals(store.size(), 1);
  }

  /**
   * Test that checkDuplicates method is working.
   * 
   * @throws IllegalArgumentException
   */
  @Test(expected = IllegalArgumentException.class)
  public void ensureCheckDuplicatesIsWorkingCorrectly() {
    store.saveEmployee(employee);
    store.validateEmployee(employee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNullDuplicateIsWorking() {
    store.validateEmployee(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureDuplicateCheckInAuthFacadeIsWorking() {
    authFacade.addUser("name", "email@email.com", "123456");
    store.validateEmployee(employee);
  }
}
