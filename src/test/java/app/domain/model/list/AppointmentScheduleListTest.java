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
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.shared.Gender;
import app.dto.VaccineTypeDTO;
import app.mapper.VaccineTypeMapper;
import app.utils.Time;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class AppointmentScheduleListTest {
  private VaccinationCenter vaccinationCenter;
  private AppointmentScheduleList appointments;
  private VaccineType vaccineType;
  private SNSUser user1;
  private SNSUser user2;
  private SNSUser user3;
  private SNSUser user4;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "00000000", "ROLE");
    vaccineType = new VaccineType("12345", "description", "technology");
    Time openingHours = new Time(10, 0);
    Time closingHours = new Time(11, 0);
    Slot slot = new Slot(5, 5);

    vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", openingHours,
        closingHours, slot, coordinator, "ages", "ars");
    appointments = vaccinationCenter.getAppointmentList();
    user1 = new SNSUser("00000000", "123456788", "name", new Date(), Gender.MALE, "+351212345675", "email1@email.com", "address");
    user2 = new SNSUser("18535290", "123456789", "name", new Date(), Gender.MALE, "+351212345678", "email2@email.com", "address");
    user3 = new SNSUser("19105246", "123456787", "name", new Date(), Gender.MALE, "+351212345671", "email3@email.com", "address");
    user4 = new SNSUser("33295275", "123456786", "name", new Date(), Gender.MALE, "+351212345670", "email4@email.com", "address");
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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);

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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);

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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);
      appointments.saveAppointment(appointment);

      Appointment secondAppointment = appointments.createAppointment(user2, secondAppointmentDate, dto, true);
      appointments.saveAppointment(secondAppointment);

      Appointment thirdAppointment = appointments.createAppointment(user3, thirdAppointmentDate, dto, true);
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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);
      appointments.saveAppointment(appointment);

      Appointment secondAppointment = appointments.createAppointment(user2, secondAppointmentDate, dto, true);
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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);
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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);
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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment1 = appointments.createAppointment(user1, appointmentDate, dto, true);
      Appointment appointment2 = appointments.createAppointment(user2, appointmentDate, dto, true);
      Appointment appointment3 = appointments.createAppointment(user3, appointmentDate, dto, true);
      Appointment appointment4 = appointments.createAppointment(user4, appointmentDate, dto, true);

      appointments.saveAppointment(appointment1);
      appointments.saveAppointment(appointment2);
      appointments.saveAppointment(appointment3);
      appointments.saveAppointment(appointment4);
      // appointments.saveAppointment(appointment);

      Appointment[][] list = appointments.getAppointmentScheduleForDay(appointmentDate);

      assertEquals(list[0][0], appointment1);
      assertEquals(list[0][1], appointment2);
      assertEquals(list[0][2], appointment3);
      assertEquals(list[0][3], appointment4);
      // assertEquals(list[0][4], appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsWhenSlotIsFull() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:00"));
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);

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
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsAfterCenterClosed() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 11:30"));
      VaccineTypeDTO dto = VaccineTypeMapper.toDto(vaccineType);

      Appointment appointment = appointments.createAppointment(user1, appointmentDate, dto, true);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }
}
