package app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.Address;
import app.domain.model.AdminProcess;
import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.DoseInfo;
import app.domain.model.Employee;
import app.domain.model.HealthCareCenter;
import app.domain.model.SNSUser;
import app.domain.model.Slot;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccineStore;
import app.domain.shared.Gender;
import app.dto.ArrivalDTO;
import app.dto.DosageInfoDTO;
import app.dto.UserVaccinationInfoDTO;
import app.dto.VaccineDTO;
import app.mapper.ArrivalMapper;
import app.mapper.VaccineMapper;
import app.session.EmployeeSession;
import app.utils.Time;

/**
 * Vaccine Administration Controller Tests
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class RegisterVaccineAdministrationControllerTest {
  private Company company = new Company("designation", "12345");
  private VaccinationCenter vaccinationCenter;
  private VaccineType vaccineType;
  private VaccineAdministrationList vaccineAdministrationList;
  private VaccineStore vaccineStore;
  private SNSUserStore snsUserStore;
  private Vaccine vaccine;
  private VaccineDTO vaccineDTO;
  private SNSUser user1;
  private ArrivalDTO arrivalDTO;
  private RegisterVaccineAdministrationController controller;

  @Before
  public void setup() {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "00000000", "ROLE");

    vaccineType = new VaccineType("12345", "description", "technology");
    vaccine = new Vaccine("pfizer", "123456", "pfizer", vaccineType);
    vaccineStore = company.getVaccineStore();
    AdminProcess adminProcess = new AdminProcess(0, 10, 2);
    DoseInfo doseInfo = new DoseInfo(12, 30);
    adminProcess.addDoseInfo(doseInfo);
    vaccine.addAdminProc(adminProcess);
    vaccineStore.saveVaccine(vaccine);

    vaccineDTO = VaccineMapper.toDto(vaccine);

    user1 = new SNSUser("00000000", "123456789", "name", DateUtils.addDays(new Date(), -400), Gender.MALE, "+351212345675", "email1@email.com",
        new Address("street", 1, "11-11", "city"));
    snsUserStore = company.getSNSUserStore();
    snsUserStore.saveSNSUser(user1);

    Time openingHours = new Time(10, 0);
    Time closingHours = new Time(11, 0);
    Slot slot = new Slot(5, 5);

    vaccinationCenter = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "http://www.site.com", openingHours,
        closingHours, slot, coordinator, "ages", "ars");

    Appointment appointment = new Appointment(user1, Calendar.getInstance(), vaccinationCenter, vaccineType, true);

    Arrival arrival = new Arrival(appointment, Calendar.getInstance());

    arrivalDTO = ArrivalMapper.toDto(arrival);

    EmployeeSession session = new EmployeeSession();
    session.setVaccinationCenter(vaccinationCenter);

    controller = new RegisterVaccineAdministrationController(company, session);
  }

  @Test
  public void ensureIsPossibleToCreateVaccineAdministration() {
    controller.createVaccineAdministration(arrivalDTO, vaccineDTO, "12345-12", 1);
  }

  @Test
  public void ensureGetResourceNameIsWorkingCorrectly() {
    assertEquals("Vaccine Administration", controller.getResourceName());
  }

  @Test
  public void ensureGetRegisteredObjectIsWorking() {
    controller.createVaccineAdministration(arrivalDTO, vaccineDTO, "12345-12", 1);
    assertNotNull(controller.getRegisteredObject());
  }

  @Test
  public void ensureSaveIsWorkingCorrectly() {
    controller.createVaccineAdministration(arrivalDTO, vaccineDTO, "12345-12", 1);
    controller.save();
  }

  @Test
  public void ensureGetLastTakenVaccineFromArrivalIsWorking() {
    controller.createVaccineAdministration(arrivalDTO, vaccineDTO, "12345-12", 1);
    controller.save();

    VaccineDTO vaccineDto = controller.getLastTakenVaccineFromArrival(arrivalDTO);
    Vaccine vaccineExpected = vaccineStore.findVaccineById(vaccineDto.getId());

    assertEquals(vaccine, vaccineExpected);
  }

  @Test
  public void ensureGetListOfVaccinesWithVaccineTypeOfArrival() {
    List<VaccineDTO> list = controller.getListOfVaccinesWithVaccineTypeOfArrival(arrivalDTO);

    assertEquals(list.size(), 1);
  }

  @Test
  public void ensureGetDosageInfoIsWorking() {
    DosageInfoDTO di = controller.getDosageInfoFromVaccineBySnsUser(vaccineDTO, arrivalDTO);

    assertNotNull(di);
  }

  @Test
  public void ensureGetUserVaccinationInfoIsWorking() {
    UserVaccinationInfoDTO va = controller.getUserVaccinationInfoFromArrival(arrivalDTO);

    assertNotNull(va);
  }
}
