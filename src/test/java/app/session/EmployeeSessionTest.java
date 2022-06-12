package app.session;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.utils.Time;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class EmployeeSessionTest {
  private EmployeeSession session;
  private VaccinationCenter center;

  @Before
  public void setup() {
    session = new EmployeeSession();
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "00000000", "COORDINATOR");

    center = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.google.com", new Time("20:00"),
        new Time("21:00"), new Slot(5, 5), coordinator, "ages", "ars");
  }

  @Test
  public void ensureHasCenterIsWorking() {
    assertFalse(session.hasCenter());
    session.setVaccinationCenter(center);
    assertTrue(session.hasCenter());
  }

  @Test
  public void ensureGetCenterIsWorking() {
    session.setVaccinationCenter(center);
    assertEquals(center, session.getVaccinationCenter());
  }
}
