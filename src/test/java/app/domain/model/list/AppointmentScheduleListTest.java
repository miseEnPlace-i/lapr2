package app.domain.model.list;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.dto.AppointmentDto;
import app.mappers.AppointmentMapper;

public class AppointmentScheduleListTest {
  private AppointmentMapper mapper;
  private VaccinationCenter vaccinationCenter;
  private AppointmentScheduleList appointments;
  private VaccineType vaccineType;

  @Before
  public void setup() {
    Company company = new Company("designation", "12345");
    VaccinationCenterStore centerStore = company.getVaccinationCenterStore();
    EmployeeStore employeeStore = company.getEmployeeStore();
    EmployeeRoleStore employeeRoleStore = company.getEmployeeRoleStore();
    VaccineTypeStore vaccineTypeStore = company.getVaccineTypeStore();
    VaccineTechnologyStore technologyStore = company.getVaccineTechnologyStore();

    technologyStore.addVaccineTechnology("technology");

    vaccineType = vaccineTypeStore.addVaccineType("12345", "description", "technology");
    vaccineTypeStore.saveVaccineType(vaccineType);

    employeeRoleStore.addEmployeeRole("COORDINATOR", "COORDINATOR");

    Employee coordinator = employeeStore.createEmployee("name", "+35122345678", "email@email.com",
        "address", "000000000ZZ4", "COORDINATOR");

    vaccinationCenter = centerStore.createHealthCareCenter("name", "address", "email@emai.com",
        "+351212345678", "+351212345678", "https://www.wedb.com", "10:00", "11:00", 5, 5,
        coordinator, "ages", "ags");
    centerStore.saveVaccinationCenter(vaccinationCenter);
    appointments = vaccinationCenter.getAppointmentList();
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


      AppointmentDto appointmentDTO = mapper.toDto("123456789");


      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

      appointments.saveAppointment(appointment);

      AppointmentWithNumberDTO secondAppointmentDTO = new AppointmentWithNumberDTO("111111111",
          secondAppointmentDate, vaccinationCenter, vaccineType, true);

      Appointment secondAppointment = appointments.create(secondAppointmentDTO);

      appointments.saveAppointment(secondAppointment);

      AppointmentWithNumberDTO thirdAppointmentDTO = new AppointmentWithNumberDTO("999999999",
          thirdAppointmentDate, vaccinationCenter, vaccineType, true);

      Appointment thirdAppointment = appointments.create(thirdAppointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

      appointments.saveAppointment(appointment);

      AppointmentWithNumberDTO secondAppointmentDTO = new AppointmentWithNumberDTO("111111111",
          secondAppointmentDate, vaccinationCenter, vaccineType, true);

      Appointment secondAppointment = appointments.create(secondAppointmentDTO);

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
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 10:58"));

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

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

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureThatIsNotPossibleToCreateAppointmentsAfterCenterClosed() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    try {
      Calendar appointmentDate = DateUtils.toCalendar(sdf.parse("01/01/2022 11:30"));

      AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
          appointmentDate, vaccinationCenter, vaccineType, true);

      Appointment appointment = appointments.create(appointmentDTO);

      appointments.saveAppointment(appointment);
    } catch (ParseException e) {
    }
  }
}
