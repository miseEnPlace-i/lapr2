package app.controller;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.dto.AppointmentWithNumberDTO;
import app.domain.model.dto.AppointmentWithoutNumberDTO;
import app.domain.model.dto.VaccinationCenterListDTO;
import app.domain.model.dto.VaccineTypeDTO;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;
import app.mappers.VaccineTypeMapper;
import pt.isep.lei.esoft.auth.UserSession;

public class ScheduleVaccineController implements IRegisterController {
  private Company company;
  private VaccinationCenterStore vaccinationCenterStore;
  private AppointmentScheduleList scheduleList;
  private Appointment appointment;
  private VaccineTypeStore vaccineTypeStore;
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
    this.userSession = company.getUserSession();
    this.snsUserStore = company.getSNSUserStore();
  }

  /**
   * Creates an appointment instance.
   * 
   * @param dto the appointment dto, containing all the information about the appointment
   */
  public void createAppointment(AppointmentWithNumberDTO dto) {
    scheduleList.create(dto);
  }

  public void createAppointment(AppointmentWithoutNumberDTO dto) {
    String userEmail = String.valueOf(userSession.getUserId());

    SNSUser snsUser = snsUserStore.findSNSUserByEmail(userEmail);
    String snsNumber = snsUser.getSnsNumber();

    scheduleList.create(dto, snsNumber);
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

  public VaccineType getVaccineTypeByCode(String code) {
    return vaccineTypeStore.getVaccineTypeByCode(code);
  }

  public VaccinationCenter getVaccinationCenterByEmail(String email) {
    return vaccinationCenterStore.getVaccinationCenterByEmail(email);
  }

  @Override
  public String stringifyData() {
    return null;
  }

  @Override
  public String getResourceName() {
    return "Appointment";
  }

  @Override
  public void save() {
    scheduleList.saveAppointment(appointment);
  }

  public boolean existsUser(String snsNumber) {
    return this.company.getSNSUserStore().checkSNSUserExists(snsNumber);
  }
}
