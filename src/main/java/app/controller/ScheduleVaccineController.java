package app.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import pt.isep.lei.esoft.auth.domain.model.Email;

public class ScheduleVaccineController {
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
  public ScheduleVaccineController(VaccinationCenterStore vaccinationCenterStore,
      VaccineTypeStore vaccineTypeStore, UserSession userSession, SNSUserStore snsUserStore) {
    this.vaccinationCenterStore = vaccinationCenterStore;
    this.vaccineTypeStore = vaccineTypeStore;
    this.userSession = userSession;
    this.snsUserStore = snsUserStore;
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
    return this.company.getSuggestedVaccineType();
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

  public void saveAppointment() {
    scheduleList.saveAppointment(appointment);
  }
}
