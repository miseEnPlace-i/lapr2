package app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
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
    private AppointmentInsertMapper mapper;
    private Appointment appointment;
    private Calendar calendar;
    private boolean sms = true;

    @Before
    public void setUp() throws ParseException {
        Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "000000000ZZ4", "ROLE");

        vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", "10:00",
                "11:00", 5, 5, coordinator, "ages", "ars");
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

    /** */
    @Test(expected = IllegalArgumentException.c)
    public void ensureItIsPossibleToCreateAppointment() {
        controller.createAppointment(this.user.getSnsNumber(), this.calendar, this.vaccinationCenter, this.vaccineType, this.sms);
    }
}
