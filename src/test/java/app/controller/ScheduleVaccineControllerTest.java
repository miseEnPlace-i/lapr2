package app.controller;

import static org.junit.Assert.assertEquals;
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
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Gender;
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
  private VaccinationCenterStore vacStore;
  private VaccineTypeStore vaccineTypeStore;
  private SNSUserStore snsUserStore;
  private AppointmentScheduleList appointmentSchedule;

  @Before
  public void setUp() throws ParseException {
    EmployeeStore employeeStore = company.getEmployeeStore();
    Employee coordinator = employeeStore.createEmployee("name", "+351212345678", "email@email.com", "address", "000000000ZZ4", "COORDINATOR");
    employeeStore.saveEmployee(coordinator);

    vacStore = company.getVaccinationCenterStore();
    vaccinationCenter = vacStore.createHealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.com", "20:00",
        "21:00", 5, 5, coordinator, "ages", "ags");
    vacStore.saveVaccinationCenter(vaccinationCenter);

    snsUserStore = company.getSNSUserStore();
    user = new SNSUser("000000000ZZ4", "123456789", "name", new Date(), Gender.MALE, "+351212345678", "email@email.com", "address");
    snsUserStore.saveSNSUser(user);

    calendar = CalendarUtils.parseDateTime(new Date(), "20:40");

    vaccineTypeStore = company.getVaccineTypeStore();
    vaccineType = new VaccineType("12345", "TEST", "TEST_TECHNOLOGY");
    vaccineTypeStore.saveVaccineType(vaccineType);

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
    // controller.createAppointment(this.user.getSnsNumber(), this.calendar, this.vaccinationCenter, this.vaccineType,
    // this.sms);
  }
}
