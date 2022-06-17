package app.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.HealthData;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccineStore;
import app.dto.AdverseReactionDTO;
import app.dto.ArrivalDTO;
import app.dto.DosageInfoDTO;
import app.dto.UserVaccinationInfoDTO;
import app.dto.VaccineDTO;
import app.mapper.DosageInfoMapper;
import app.mapper.UserVaccinationInfoMapper;
import app.session.EmployeeSession;

/**
 * Register Vaccine Administration Controller
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class RegisterVaccineAdministrationController
    implements IRegisterController<VaccineAdministration> {
  private Company company;
  private EmployeeSession nurseSession;
  private VaccineStore vaccineStore;
  private SNSUserStore snsUserStore;
  private VaccineAdministration vaccineAdministration;
  private VaccineAdministrationList vaccineAdministrationList;
  private SNSUser snsUser;
  private HealthData userHealthData;

  /**
   * Constructor for RegisterVaccineAdministrationController.
   * 
   * @param company Company
   */
  public RegisterVaccineAdministrationController(Company company,
      EmployeeSession nurseSession) {
    this.company = company;
    this.nurseSession = nurseSession;
    this.vaccineStore = this.company.getVaccineStore();
    this.snsUserStore = this.company.getSNSUserStore();
  }

  /**
   * Gets the User Vaccination Info from a given ArrivalDTO.
   * 
   * @param arrivalDTO ArrivalDTO
   * 
   * @return the UserVaccinationInfoDTO containing the User Vaccination Info
   */
  public UserVaccinationInfoDTO getUserVaccinationInfoFromArrival(
      ArrivalDTO arrivalDTO) {
    snsUser = getSNSUserFromArrival(arrivalDTO);

    String name = snsUser.getName();
    int age = snsUser.getAge();

    vaccineAdministrationList =
        getVaccineAdministrationListFromArrival(arrivalDTO);

    userHealthData = snsUser.getUserHealthData();

    List<AdverseReactionDTO> adverseReactionsDto =
        userHealthData.getAdverseReactions();

    return UserVaccinationInfoMapper.toDto(name, age, adverseReactionsDto);
  }

  /**
   * Get the last taken vaccine from a given ArrivalDTO.
   * 
   * @param arrivalDTO ArrivalDTO
   * 
   * @return the VaccineDTO containing the last taken vaccine
   */
  public VaccineDTO getLastTakenVaccineFromArrival(ArrivalDTO arrivalDto) {
    vaccineAdministrationList =
        getVaccineAdministrationListFromArrival(arrivalDto);

    Appointment appointment = arrivalDto.getAppointment();

    VaccineType vaccineType = appointment.getVaccineType();

    return vaccineAdministrationList
        .getLastTakenVaccineByVaccineType(vaccineType);
  }

  /**
   * Get a list of all vaccines that have the vaccine type of the arrival, and that have a administration process for
   * user's age.
   * 
   * @param arrivalDto ArrivalDTO
   * 
   * @return the list of VaccineDTO
   */
  public List<VaccineDTO> getListOfVaccinesWithVaccineTypeOfArrival(
      ArrivalDTO arrivalDto) {
    Appointment appointment = arrivalDto.getAppointment();

    VaccineType vaccineType = appointment.getVaccineType();

    snsUser = getSNSUserFromArrival(arrivalDto);
    int age = snsUser.getAge();

    return vaccineStore
        .getVaccinesByVaccineTypeWithAdminProcessForAge(vaccineType, age);
  }

  /**
   * Get the dosage info from a given VaccineDTO.
   * 
   * @param vaccineDTO VaccineDTO
   * 
   * @return the DosageInfoDTO containing the dosage info
   */
  public DosageInfoDTO getDosageInfoFromVaccineBySnsUser(VaccineDTO vaccineDTO,
      ArrivalDTO arrivalDTO) {
    String id = vaccineDTO.getId();

    Vaccine vaccine = vaccineStore.findVaccineById(id);

    snsUser = getSNSUserFromArrival(arrivalDTO);
    int age = snsUser.getAge();

    vaccineAdministrationList =
        getVaccineAdministrationListFromArrival(arrivalDTO);
    int doseNumber =
        vaccineAdministrationList.getNextDoseNumberOfVaccine(vaccine);

    int dosage = vaccine.getDosageByDoseNumberAndAge(doseNumber, age);

    return DosageInfoMapper.toDto(doseNumber, dosage);
  }

  /**
   * Creates a new Vaccine Administration.
   * 
   * @param arrivalDTO ArrivalDTO
   * @param vaccineDTO VaccineDTO
   * @param lotNumber String
   * @param doseNumber int
   */
  public void createVaccineAdministration(ArrivalDTO arrivalDTO,
      VaccineDTO vaccineDTO, String lotNumber, int doseNumber) {
    vaccineAdministrationList =
        getVaccineAdministrationListFromArrival(arrivalDTO);

    String id = vaccineDTO.getId();

    Vaccine vaccine = vaccineStore.findVaccineById(id);

    VaccinationCenter vaccinationCenter = nurseSession.getVaccinationCenter();

    Calendar date = Calendar.getInstance();

    vaccineAdministration =
        vaccineAdministrationList.createVaccineAdministration(snsUser, vaccine,
            lotNumber, doseNumber, vaccinationCenter, date);

    vaccineAdministrationList
        .validateVaccineAdministration(vaccineAdministration);
  }

  /**
   * Get the resource name.
   * 
   * @return the resource name
   */
  @Override
  public String getResourceName() {
    return "Vaccine Administration";
  }

  /**
   * Get the registered object.
   * 
   * @return the registered object
   */
  @Override
  public VaccineAdministration getRegisteredObject() {
    return this.vaccineAdministration;
  }

  /**
   * Get the Vaccine Administration data stringified.
   * 
   * @return the data stringified
   */
  @Override
  public String stringifyData() {
    return this.vaccineAdministration.toString();
  }

  /**
   * Saves the Vaccine Administration.
   * 
   * @param VaccineAdministration the Vaccine Administration to be saved
   */
  @Override
  public void save() {
    this.vaccineAdministrationList
        .saveVaccineAdministration(this.vaccineAdministration);
  }

  private VaccineAdministrationList getVaccineAdministrationListFromArrival(
      ArrivalDTO arrivalDto) {
    snsUser = getSNSUserFromArrival(arrivalDto);
    userHealthData = snsUser.getUserHealthData();
    return userHealthData.getVaccineAdministrationList();
  }

  private SNSUser getSNSUserFromArrival(ArrivalDTO arrivalDto) {
    String snsNumber = arrivalDto.getSnsUserNumber();
    return snsUserStore.findSNSUserByNumber(snsNumber);
  }
}
