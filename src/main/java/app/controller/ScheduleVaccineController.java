package app.controller;

import java.text.ParseException;
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
import app.service.AppointmentValidator;
import app.service.CalendarUtils;

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
   * Creates an appointment instance. (Used in US01)
   */
  public void createAppointment(Date date, String time, VaccinationCenterListDTO centerDto, VaccineTypeDTO vaccineTypeDto, boolean sms) {
    VaccinationCenter center = vaccinationCenterStore.getVaccinationCenterWithEmail(centerDto.getEmail());

    this.appointmentSchedule = center.getAppointmentList();

    String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
    SNSUser snsUser = snsUserStore.findSNSUserByEmail(email);

    try {
      Calendar dateAndTime = CalendarUtils.parseDateTime(date, time);

      this.appointment = appointmentSchedule.createAppointment(snsUser, dateAndTime, vaccineTypeDto, sms);
    } catch (ParseException ex) {
      throw new IllegalArgumentException("Date or time invalid.");
    }

    AppointmentValidator appointmentValidator = new AppointmentValidator(this.vaccineStore);

    appointmentValidator.validateAppointment(this.appointment);
  }

  /**
   * Creates an appointment instance. (Used in US02)
   */
  public void createAppointment(String snsNumber, Date date, String time, VaccinationCenterListDTO centerDto, VaccineTypeDTO vaccineTypeDto, boolean sms) {
    VaccinationCenter center = vaccinationCenterStore.getVaccinationCenterWithEmail(centerDto.getEmail());

    this.appointmentSchedule = center.getAppointmentList();

    SNSUser snsUser = snsUserStore.findSNSUserByNumber(snsNumber);

    if (snsUser == null) throw new IllegalArgumentException("SNS User Number not found.");

    try {
      Calendar dateAndTime = CalendarUtils.parseDateTime(date, time);

      this.appointment = appointmentSchedule.createAppointment(snsUser, dateAndTime, vaccineTypeDto, sms);
    } catch (ParseException ex) {
      throw new IllegalArgumentException("Date or time invalid.");
    }

    AppointmentValidator appointmentValidator = new AppointmentValidator(this.vaccineStore);

    appointmentValidator.validateAppointment(this.appointment);
  }

  /**
   * Gets the suggested vaccine type.
   * 
   * @return the suggested vaccine type dto
   */
  public VaccineTypeDTO getSuggestedVaccineType() {
    VaccineType vaccineType = vaccineTypeStore.getVaccineTypeByCode(company.getOngoingOutbreakVaccineTypeCode());

    VaccineTypeDTO vaccineTypeDto = VaccineTypeMapper.toDto(vaccineType);

    return vaccineTypeDto;
  }

  public List<VaccineTypeDTO> getListOfVaccineTypes() {
    return vaccineTypeStore.getVaccineTypes();
  }

  public List<VaccinationCenterListDTO> getListOfVaccinationCentersWithVaccineType(VaccineTypeDTO vaccineType) {
    return vaccinationCenterStore.getListOfVaccinationCentersWithVaccineType(vaccineType);
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
  }

  @Override
  public AppointmentInsertDTO getRegisteredObject() {
    return AppointmentInsertMapper.toDto(appointment);
  }
}
