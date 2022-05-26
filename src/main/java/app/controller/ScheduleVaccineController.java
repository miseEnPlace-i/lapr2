package app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;
import app.dto.AppointmentWithNumberDTO;
import app.dto.AppointmentWithoutNumberDTO;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.mappers.VaccineTypeMapper;
import app.service.TimeUtils;
import pt.isep.lei.esoft.auth.UserSession;

public class ScheduleVaccineController implements IRegisterController {
  private Company company;
  private VaccinationCenterStore vaccinationCenterStore;
  private AppointmentScheduleList appointmentSchedule;
  private Appointment appointment;
  private VaccineTypeStore vaccineTypeStore;
  private VaccineStore vaccineStore;
  private UserSession userSession;
  private SNSUserStore snsUserStore;

  /**
   * Constructor for ScheduleVaccineController.
   * 
   * @param vaccinationCenterStore the vaccination center store
   * @param vaccineTypeStore the vaccine type store
   */
  public ScheduleVaccineController(Company company) {
    this.company = company;
    this.vaccinationCenterStore = company.getVaccinationCenterStore();
    this.vaccineTypeStore = company.getVaccineTypeStore();
    this.userSession = App.getInstance().getCurrentUserSession();
    this.snsUserStore = company.getSNSUserStore();
    this.vaccineStore = company.getVaccineStore();
  }

  /**
   * Creates an appointment instance.
   * 
   * @param dto the appointment dto, containing all the information about the appointment
   */
  public void createAppointment(AppointmentWithNumberDTO appointmentDto) {
    this.appointmentSchedule = appointmentDto.getCenter().getAppointmentList();
    appointment = appointmentSchedule.create(appointmentDto);
  }

  public void createAppointment(AppointmentWithoutNumberDTO dto) {
    this.appointmentSchedule = dto.getCenter().getAppointmentList();

    SNSUser snsUser = getSnsUserByUserSession();

    String snsNumber = snsUser.getSnsNumber();

    appointment = appointmentSchedule.create(dto, snsNumber);
  }

  /**
   * Gets the suggested vaccine type.
   * 
   * @return the suggested vaccine type
   */
  public VaccineType getSuggestedVaccineType() {
    VaccineType vaccineType =
        vaccineTypeStore.getVaccineTypeByCode(company.getOngoingOutbreakVaccineTypeCode());

    return vaccineType;
  }

  public List<VaccineTypeDTO> getListOfVaccineTypes() {
    List<VaccineTypeDTO> list = new ArrayList<VaccineTypeDTO>();
    List<VaccineType> vaccineTypes = vaccineTypeStore.getList();

    for (VaccineType vaccineType : vaccineTypes) {
      list.add(VaccineTypeMapper.toDto(vaccineType));
    }

    return list;
  }

  public List<VaccinationCenterListDTO> getListOfVaccinationCentersWithVaccineType(
      VaccineType vaccineType) {

    return vaccinationCenterStore.getListOfVaccinationCentersWithVaccineType(vaccineType);
  }

  private SNSUser getSnsUserByUserSession() {
    String userEmail = String.valueOf(userSession.getUserId());
    return snsUserStore.findSNSUserByEmail(userEmail);
  }

  public VaccineType getVaccineTypeByCode(String code) {
    return vaccineTypeStore.getVaccineTypeByCode(code);
  }

  public VaccinationCenter getVaccinationCenterByEmail(String email) {
    return vaccinationCenterStore.getVaccinationCenterByEmail(email);
  }

  @Override
  public String stringifyData() {
    return appointment.toString();
  }

  @Override
  public String getResourceName() {
    return "Appointment";
  }

  @Override
  public void save() {
    appointmentSchedule.saveAppointment(appointment);
  }

  public boolean existsUser(String snsNumber) {
    return this.company.getSNSUserStore().checkSNSUserExists(snsNumber);
  }

  public boolean checkIfUserHasTakenVaccineType(VaccineType vt) {
    SNSUser snsUser = getSnsUserByUserSession();
    if (snsUser.getLastTakenVaccineFromType(vt) == null) return false;
    else return true;
  }

  public boolean checkAdministrationProcessForVaccineType(VaccineType vt) {
    List<Vaccine> vaccinesList = vaccineStore.getVaccinesByType(vt);
    SNSUser snsUser = getSnsUserByUserSession();
    Date birthDay = snsUser.getBirthDay();

    int age = TimeUtils.calculateAge(birthDay);

    for (Vaccine vaccine : vaccinesList) {
      if (vaccine.hasAdministrationProcessForGivenAge(age)) {
        return true;
      }
    }

    return false;
  }

  public boolean isCenterOpenAt(VaccinationCenter vacCenter, String hours) {
    return vacCenter.isOpenAt(hours);
  }

  public boolean hasSlotAvailability(VaccinationCenter vacCenter, Calendar date) {
    return vacCenter.hasAvailabilityInSlot(date);
  }
}
