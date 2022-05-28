package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.AppointmentInsertDTO;
import app.mapper.AppointmentInsertMapper;
import app.service.CalendarUtils;

public class ScheduleVaccineControllerTest {
  Company company = new Company("designation", "12345");
  ScheduleVaccineController controller = new ScheduleVaccineController(company);
  String resourceName = "Appointment";
  private VaccinationCenter vaccinationCenter;
  private VaccineType vaccineType;
  private SNSUser user;
  private AppointmentInsertDTO dto;
  private Appointment appointment;
  private Calendar calendar;
  private boolean sms = true;
  private VaccinationCenterStore store;

  @Before
  public void setUp() throws ParseException {
    EmployeeStore employeeStore = company.getEmployeeStore();
    Employee coordinator = employeeStore.createEmployee("name", "+351212345678", "email@email.com", "address", "000000000ZZ4", "COORDINATOR");
    employeeStore.saveEmployee(coordinator);

    store = company.getVaccinationCenterStore();
    vaccinationCenter = store.createHealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.com", "20:00", "21:00",
        5, 5, coordinator, "ages", "ags");
    store.saveVaccinationCenter(vaccinationCenter);

    user = new SNSUser("000000000ZZ4", "123456789", "name", new Date(), 'M', "+351212345678", "email@email.com", "address");
    Date date = new Date();
    calendar = CalendarUtils.parseDateTime(date, "20:40");
    vaccineType = new VaccineType("12345", "TEST", "TEST_TECHNOLOGY");

    appointment = new Appointment(user, calendar, vaccinationCenter, vaccineType, sms);
    dto = AppointmentInsertMapper.toDto(appointment);
  }

  /**
   * Check getResourceName method is working properly
   */
  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assertEquals(resourceName, controller.getResourceName());
  }

  /**
   * Check that it is possible to create an appointment
   */
  @Test
  public void ensureItIsPossibleToCreateAppointment() {
    controller.createAppointment(this.user.getSnsNumber(), this.calendar, this.vaccinationCenter, this.vaccineType, this.sms);
  }

  /**
   * Check that getVaccinationCenterByEmail method is working properly
   */
  @Test
  public void ensureItIsPossibleToGetCenterByEmail() {
    VaccinationCenter center = controller.getVaccinationCenterByEmail("email@email.com");

    assertEquals(center, vaccinationCenter);
  }

  @Test
  public void ensureGetCenterByEmailWithoutResultWorks() {
    VaccinationCenter center = controller.getVaccinationCenterByEmail("non_existing@email.com");

    assertNull(center);
  }
}
