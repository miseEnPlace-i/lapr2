package app.service;

import java.util.Calendar;
import app.domain.model.Appointment;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.VaccineStore;
import app.dto.VaccineDTO;
import app.utils.Time;

public class AppointmentValidator {
  private VaccineStore vaccineStore;

  public AppointmentValidator(VaccineStore vaccineStore) {
    this.vaccineStore = vaccineStore;
  }

  public void validateAppointment(Appointment appointment) {
    if (appointment == null) throw new IllegalArgumentException("Appointment is not valid.");

    Time hours = new Time(appointment.getDate());

    VaccinationCenter vaccinationCenter = appointment.getVaccinationCenter();

    if (!vaccinationCenter.isOpenAt(hours))
      throw new IllegalArgumentException("Vaccination center is closed or does not accept appointments at selected time.");

    if (!vaccinationCenter.hasAvailabilityInSlot(appointment.getDate()))
      throw new IllegalArgumentException("Vaccination center does not accept any more appointments at selected time.");

    SNSUser snsUser = appointment.getSnsUser();

    if (snsUser.hasAppointmentForVaccineType(appointment.getVaccineType()))
      throw new IllegalArgumentException("SNS User has already an appointment for the selected vaccine type.");

    if (!vaccineStore.areVaccinesWithValidAdminProcessWithVaccineType(snsUser.getAge(), appointment.getVaccineType()))
      throw new IllegalArgumentException("There are no vaccines with a valid administration process for SNS User's age.");

    if (snsUser.hasTakenAllDoses(appointment.getVaccineType())) {
      throw new IllegalArgumentException("SNS User has already taken all doses for the selected vaccine type.");
    }

    if (!isAppointmentWithinTimeSinceLastDose(appointment.getVaccineType(), appointment.getDate(), snsUser))
      throw new IllegalArgumentException("Time since last dose must past before scheduling a new appointment.");
  }

  public boolean isAppointmentWithinTimeSinceLastDose(VaccineType vaccineType, Calendar appointmentDate, SNSUser snsUser) {
    VaccineAdministrationList vaccineAdministrationList = snsUser.getUserHealthData().getVaccineAdministrationList();

    VaccineDTO lastTakenVaccine = vaccineAdministrationList.getLastTakenVaccineByVaccineType(vaccineType);

    if (lastTakenVaccine == null) {
      return true;
    }

    Calendar lastTakenVaccineDate = vaccineAdministrationList.getLastVaccineAdministrationByVaccineType(vaccineType).getDate();
    int doseNumber = vaccineAdministrationList.getLastVaccineAdministrationByVaccineType(vaccineType).getDoseNumber();

    Vaccine vaccine = vaccineStore.findVaccineById(lastTakenVaccine.getId());

    return vaccine.isDateWithinTimeSinceLastDose(lastTakenVaccineDate, appointmentDate, doseNumber, snsUser.getAge());
  }
}
