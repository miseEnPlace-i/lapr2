package app.domain.model.list;

import static org.junit.Assert.assertNotNull;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.dto.AppointmentWithNumberDTO;
import app.domain.model.store.EmployeeRoleStore;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;

public class AppointmentScheduleListTest {
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
    AppointmentWithNumberDTO appointmentDTO = new AppointmentWithNumberDTO("123456789",
        Calendar.getInstance(), vaccinationCenter, vaccineType, true);

    Appointment appointment = appointments.create(appointmentDTO);

    assertNotNull(appointment);

    appointments.saveAppointment(appointment);

    Appointment[][] list = appointments.getAppointmentScheduleForDay(Calendar.getInstance());

    assertNotNull(list);
  }
}
