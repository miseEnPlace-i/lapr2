package app.domain.model.list;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;

public class AppointmentScheduleList {
  @Before
  public void setup() {
    Company company = new Company("designation", "ongoingOutbreakVaccineTypeCode");
    VaccinationCenterStore center = company.getVaccinationCenter();
  }

  @Test
  public void ensureThatAppointmentScheduleListIsCreated() {
    AppointmentScheduleList appointmentScheduleList =
        new AppointmentScheduleList(new VaccinationCenter());
    assertNotNull(appointmentScheduleList);
  }
}
