package app.domain.model;

import org.junit.Test;
import app.controller.App;
import app.domain.model.store.EmployeeStore;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.junit.Before;

public class ListEmployeeTest {
  EmployeeStore store;

  @Before
  public void setUp() {
    App app = App.getInstance();
    Company company = app.getCompany();
    this.store = company.getEmployeeStore();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullArguments() {
    store.getEmployeesByRole(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNonExistingRole() {
    store.getEmployeesByRole("NonExistingRole");
  }
}
