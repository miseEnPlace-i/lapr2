package app.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Address;
import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.shared.Gender;
import app.dto.ArrivalDTO;
import app.exception.NotAuthorizedException;
import app.session.EmployeeSession;
import app.utils.Time;

public class ListUsersInWaitingRoomControllerTest {
  private VaccinationCenter center;
  private SNSUser snsUser;
  private VaccineType vaccineType;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "00000000", "COORDINATOR");

    center = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.sss.com", new Time("10:00"),
        new Time("11:00"), new Slot(5, 5), coordinator, "ages", "ars");

    snsUser =
        new SNSUser("00000000", "123456789", "name", new Date(), Gender.MALE, "+351212345678", "email@email.com", new Address("street", 1, "11-11", "city"));
    vaccineType = new VaccineType("12345", "description", "technology");
  }

  @Test(expected = NotAuthorizedException.class)
  public void ensureNonAuthenticatedEmployeeCannotListWaitingRoom() throws NotAuthorizedException {
    EmployeeSession nurseSession = new EmployeeSession();
    new ListUsersInWaitingRoomController(nurseSession);
  }

  @Test
  public void ensureAuthenticatedCenterIsReflectedInSession() throws NotAuthorizedException {
    EmployeeSession session = new EmployeeSession();
    session.setVaccinationCenter(center);
    new ListUsersInWaitingRoomController(session);

    assertTrue(session.hasCenter());
    assertEquals(session.getVaccinationCenter(), center);
  }

  @Test
  public void ensureAuthenticatedEmployeeCanListWaitingRoom() throws NotAuthorizedException {
    EmployeeSession session = new EmployeeSession();
    session.setVaccinationCenter(center);
    ListUsersInWaitingRoomController ctrl = new ListUsersInWaitingRoomController(session);


    List<ArrivalDTO> list = ctrl.getWaitingRoomListFromNurseCenter();
    assertEquals(list.size(), 0);

    Appointment appointment = new Appointment(snsUser, Calendar.getInstance(), center, vaccineType, true);
    Arrival arrival = new Arrival(appointment, Calendar.getInstance());
    center.getWaitingRoom().saveArrival(arrival);

    list = ctrl.getWaitingRoomListFromNurseCenter();

    assertEquals(list.size(), 1);
  }
}
