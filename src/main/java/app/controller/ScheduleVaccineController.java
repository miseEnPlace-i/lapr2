package app.controller;

import java.util.Calendar;
import java.util.List;
import app.domain.model.Appointment;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.dto.AppointmentDTO;
import app.domain.model.list.AppointmentScheduleList;

public class ScheduleVaccineController {
  private Company company;
  private VaccinationCenter vaccinationCenter;
  private AppointmentScheduleList scheduleList;
  private Appointment appointment;

  public ScheduleVaccineController(VaccinationCenter vaccinationCenter) {
    this.vaccinationCenter = vaccinationCenter;
  }

  public void createAppointment(AppointmentDTO dto) {
    scheduleList.create(dto);
  }

  public VaccineType getSuggestedVaccineType() {
    return this.company.getSuggestedVaccineType();
  }

  public List<VaccineType> getListOfVaccineTypes() {
    return null;
  }

  public List<VaccinationCenter> getListOfVaccinationCentersWithVaccineType(
      VaccineType vaccineType) {

    return vaccinationCenter.getListOfVaccinationCentersWithVaccineType();
  }

  public void saveAppointment() {
    scheduleList.saveVaccinationCenter(appointment);
  }
}
