package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.AppointmentInsertDTO;
import app.mapper.AppointmentInsertMapper;
import app.service.CalendarUtils;
import app.utils.Time;

public class ScheduleVaccineControllerTest {
  Company company = new Company("designation", "12345");
  ScheduleVaccineController controller = new ScheduleVaccineController(company);
  String resourceName = "Appointment";
  private VaccinationCenter vaccinationCenter;
  private VaccineType vaccineType;
  private SNSUser user;
  private AppointmentInsertDTO dto;
  private AppointmentInsertMapper mapper;
  private Appointment appointment;
  private Calendar calendar;
  private boolean sms = true;
  private Time open;
  private Time close;
  private Slot slot;
  private VaccinationCenterStore store;

  @Before
  public void setUp() throws ParseException {
    open = new Time("20:00");
    close = new Time("21:00");

    slot = new Slot(5, 5);
    slot.getDuration();
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "000000000ZZ4", "ROLE");

    vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", open, close, slot,
        coordinator, "ages", "ars");
    store = company.getVaccinationCenterStore();
    store.saveVaccinationCenter(vaccinationCenter);

    user = new SNSUser("000000000ZZ4", "123456789", "name", new Date(), 'M', "+351212345678", "email@email.com", "address");
    Date date = new Date();
    calendar = CalendarUtils.parseDateTime(date, "20:40");
    vaccineType = new VaccineType("12345", "TEST", "TEST_TECHNOLOGY");

    appointment = new Appointment(user, calendar, vaccinationCenter, vaccineType, sms);
    dto = mapper.toDto(appointment);
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
    assertEquals(controller.getVaccinationCenterByEmail(vaccinationCenter.getEmail()), vaccinationCenter);


  }
}
