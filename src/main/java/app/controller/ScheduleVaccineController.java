package app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineStore;
import app.domain.model.store.VaccineTypeStore;
import app.dto.AppointmentInsertDTO;
import app.dto.VaccinationCenterListDTO;
import app.dto.VaccineTypeDTO;
import app.mapper.AppointmentInsertMapper;
import app.mapper.VaccineTypeMapper;
import app.service.TimeUtils;
import app.ui.console.utils.Utils;
import app.utils.Time;

/**
 * ScheduleVaccineController class.
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class ScheduleVaccineController implements IRegisterController<AppointmentInsertDTO> {
  private Company company;
  private VaccinationCenterStore vaccinationCenterStore;
  private AppointmentScheduleList appointmentSchedule;
  private Appointment appointment;
  private VaccineTypeStore vaccineTypeStore;
  private VaccineStore vaccineStore;
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
    this.snsUserStore = company.getSNSUserStore();
    this.vaccineStore = company.getVaccineStore();
  }

  /**
   * Creates an appointment instance.
   * 
   * @param dto the appointment dto, containing all the information about the appointment
   */
  public void createAppointment(String snsNumber, Calendar date, VaccinationCenter center, VaccineType vaccineType, boolean sms) {
    this.appointmentSchedule = center.getAppointmentList();
    SNSUser user = snsUserStore.findSNSUserByNumber(snsNumber);
    this.appointment = appointmentSchedule.create(user, date, center, vaccineType, sms);
  }

  /**
   * Gets the suggested vaccine type.
   * 
   * @return the suggested vaccine type
   */
  public VaccineType getSuggestedVaccineType() {
    VaccineType vaccineType = vaccineTypeStore.getVaccineTypeByCode(company.getOngoingOutbreakVaccineTypeCode());

    return vaccineType;
  }

  public String getSNSUserNumberWithEmail(String email) {
    SNSUser user = snsUserStore.findSNSUserByEmail(email);
    return user.getSnsNumber();
  }

  public List<VaccineTypeDTO> getListOfVaccineTypes() {
    List<VaccineTypeDTO> list = new ArrayList<VaccineTypeDTO>();
    List<VaccineType> vaccineTypes = vaccineTypeStore.getList();

    for (VaccineType vaccineType : vaccineTypes) {
      list.add(VaccineTypeMapper.toDto(vaccineType));
    }

    return list;
  }

  public List<VaccinationCenterListDTO> getListOfVaccinationCentersWithVaccineType(VaccineType vaccineType) {

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
    AppointmentInsertDTO dto = AppointmentInsertMapper.toDto(appointment);
    return dto.toString();
  }

  @Override
  public String getResourceName() {
    return "Appointment";
  }

  @Override
  public void save() {
    appointmentSchedule.saveAppointment(appointment);

    SNSUser snsUser = snsUserStore.findSNSUserByNumber(appointment.getSnsUser().getSnsNumber());
    snsUser.addAppointmentToList(appointment);
  }

  public boolean existsUser(String snsNumber) {
    return this.company.getSNSUserStore().checkSNSUserExists(snsNumber);
  }

  public boolean userHasTakenAnyVaccineFromVaccineType(VaccineType vt, String SnsNumber) {
    SNSUser user = snsUserStore.findSNSUserByNumber(SnsNumber);
    return user.hasTakenAnyVaccineFromVaccineType(vt);
  }

  public boolean checkAdministrationProcessForVaccineType(VaccineType vt, String number) {
    SNSUser snsUser = snsUserStore.findSNSUserByNumber(number);
    Date birthDay = snsUser.getBirthDay();

    int age = TimeUtils.calculateAge(birthDay);

    return vaccineStore.areVaccinesWithValidAdminProcessWithVaccineType(age, vt);
  }

  public boolean isCenterOpenAt(VaccinationCenter vacCenter, String hours) {
    Time t = new Time(hours);
    return vacCenter.isOpenAt(t);
  }

  public boolean hasSlotAvailability(VaccinationCenter vacCenter, Calendar date) {
    return vacCenter.hasAvailabilityInSlot(date);
  }

  public boolean userHasAppointmentForVaccineType(VaccineType vaccineType, String number) {
    SNSUser snsUser = snsUserStore.findSNSUserByNumber(number);
    return snsUser.hasAppointmentForVaccineType(vaccineType, number);
  }

  @Override
  public AppointmentInsertDTO getRegisteredObject() {
    return AppointmentInsertMapper.toDto(appointment);
  }

  /**
   * Shows suggested vaccine type and asks to select one option
   * 
   * @param vt the vaccine type suggested by the system
   * @return "true" if accepted, "false" otherwise
   */
  public boolean showSuggestedVaccineType(VaccineType vt) {
    System.out.println("\nSuggested Vaccine Type:\n");

    System.out.println(vt.getDescription());

    List<String> options = new ArrayList<String>();
    options.add("Yes, accept suggestion.");
    options.add("No, choose other vaccine type.");
    int index = Utils.showAndSelectIndex(options, "\nSelect an option: (1 or 2)  ");

    return index == 0;
  }


  /**
   * Checks if user is eligible for a certain vaccine type. It checks if it has already taken any vaccine from certain
   * vaccine type: If it has taken, BY NOW THIS DOES NOTHING If it has not taken, checks if there is any vaccine with an
   * administration process that includes his age.
   * 
   * @param vaccineType the vaccine type to be checked
   * @return "true" if user is eligible, "false" otherwise
   */
  public boolean isUserEligibleForVaccine(VaccineType vaccineType, String number) {
    if (userHasTakenAnyVaccineFromVaccineType(vaccineType, number)) {
      // ctrl.getVaccinesByType(vaccineType);
      // ctrl.checkAdministrationProcessForNextDose();
      // vacCenter = selectVaccinationCenterWithVaccineType(vaccineType);
      return false;
    } else {
      if (checkAdministrationProcessForVaccineType(vaccineType, number)) {
        return true;
      } else {
        return false;
      }
    }
  }
}
