package app.domain.model.list;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Appointment;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;

public class AppointmentScheduleListTest {
  private VaccinationCenter vaccinationCenter;
  private AppointmentScheduleList appointments;
  private VaccineType vaccineType;
  private SNSUser user;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "000000000ZZ4", "ROLE");

    vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", "10:00", "11:00", 5,
        5, coordinator, "ages", "ars");
    appointments = vaccinationCenter.getAppointmentList();
    user = new SNSUser("000000000ZZ4", "123456789", "name", new Date(), 'M', "+351212345678", "email@email.com", "address");
  }

  @Test
  public void ensureThatAppointmentScheduleListIsCreated() {
    assertNotNull(appointments);
  }

  @Test
  public void ensureThatIsPossibleToScheduleAppointment() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));
      Calendar nextDay = DateUtils.toCalendar(DateUtils.addDays(sdf.parse("01/01/2022 10:00"), 1));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      assertNotNull(appointment);

      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);
      Appointment[][] emptyList = appointments.getAppointmentScheduleForDay(nextDay);

      assertNotNull(list);
      assertNull(emptyList);
    } catch (ParseException e) {
    }
  }

  @Test
  public void ensureThatScheduleListDimensionsAreCorrect() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:MM");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list.length, 12);
      assertEquals(list[0].length, 5);
    } catch (ParseException e) {
    }
  }

  @Test
  public void ensureThatScheduleIndexIsCorrect() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));
      Calendar secondAppointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:05"));
      Calendar thirdAppointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:30"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(appointment);

      Appointment secondAppointment = appointments.create(user, secondAppointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(secondAppointment);

      Appointment thirdAppointment = appointments.create(user, thirdAppointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(thirdAppointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[0][0], appointment);
      assertEquals(list[1][0], secondAppointment);
      assertEquals(list[6][0], thirdAppointment);
    } catch (ParseException e) {
    }
  }

  @Test
  public void ensureThatIsPossibleToScheduleMultipleAppointmentsToSameSlot() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));
      Calendar secondAppointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(appointment);

      Appointment secondAppointment = appointments.create(user, secondAppointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(secondAppointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[0][0], appointment);
      assertEquals(list[0][1], secondAppointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToScheduleAppointmentAfterLastSlot() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 11:01"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[11][0], appointment);
    } catch (ParseException e) {
    }
  }

  @Test()
  public void ensureThatIsPossibleToScheduleAppointmentToLastSlot() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:55"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);
      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[11][0], appointment);
    } catch (ParseException e) {
    }
  }

  @Test
  public void ensureThatIsPossibleToCreateAppointmentsUntilSlotIsFull() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[0][0], appointment);
      assertEquals(list[0][2], appointment);
      assertEquals(list[0][2], appointment);
      assertEquals(list[0][3], appointment);
      assertEquals(list[0][4], appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsWhenSlotIsFull() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsBeforeCenterOpen() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 08:00"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsAfterCenterClosed() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 11:30"));

      Appointment appointment = appointments.create(user, appointmentDate, vaccinationCenter, vaccineType, true);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }
}
