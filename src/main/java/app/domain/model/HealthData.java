package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.controller.App;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.dto.VaccineDTO;
import app.service.CalendarUtils;

public class HealthData implements Serializable {
  SNSUser snsUser;
  VaccineAdministrationList vaccineAdministrationList;
  List<Appointment> appointments;
  SNSUserStore store;

  public HealthData(SNSUser snsUser) {
    this.vaccineAdministrationList = new VaccineAdministrationList();
    this.appointments = new ArrayList<>();
    this.snsUser = snsUser;
  }

  // public void addVaccine(Vaccine vaccine) {
  // this.vaccinesTaken.add(vaccine);
  // }

  // public boolean hasTakenVaccine(Vaccine vaccine) {
  // return this.vaccinesTaken.contains(vaccine);
  // }

  // public Vaccine getLastVaccineTakenWithType(VaccineType vaccineType) {
  // for (int i = this.vaccinesTaken.size() - 1; i >= 0; i--)
  // if (this.vaccinesTaken.get(i).getVacType().equals(vaccineType)) return this.vaccinesTaken.get(i);

  // return null;
  // }

  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
  }

  public boolean hasAppointmentForVaccineTypeInFuture(VaccineType vaccineType) {
    for (Appointment appointment : appointments) {
      if (appointment.hasSnsNumber(snsUser.getSnsNumber()) && appointment.hasVaccineType(vaccineType) && isAppointmentInFuture(appointment.getDate())) {
        return true;
      }
    }
    return false;
  }

  public boolean hasAppointmentForVaccineTypeToday(VaccineType vaccineType) {
    for (Appointment appointment : appointments) {
      if (appointment.hasSnsNumber(snsUser.getSnsNumber()) && appointment.hasVaccineType(vaccineType) && isAppointmentToday(appointment.getDate())) {
        return true;
      }
    }
    return false;
  }

  private boolean isAppointmentInFuture(Calendar appointmentDate) {
    return CalendarUtils.compareDates(appointmentDate, Calendar.getInstance()) > 0;
  }

  private boolean isAppointmentToday(Calendar appointmentDate) {
    return CalendarUtils.compareDates(appointmentDate, Calendar.getInstance()) == 0;
  }

  /**
   * Get the VaccineAdministrationList.
   * 
   * @return the vaccineAdministrationList
   */
  public VaccineAdministrationList getVaccineAdministrationList() {
    return vaccineAdministrationList;
  }

  public boolean hasTakenAllDoses(VaccineType vaccineType) {
    VaccineAdministration lastVaccineAdministration = vaccineAdministrationList.getLastVaccineAdministrationByVaccineType(vaccineType);
    if (lastVaccineAdministration == null) {
      return false;
    }

    return lastVaccineAdministration.getDoseNumber() == lastVaccineAdministration.getVaccine().getAdministrationProcessForGivenAge(snsUser.getAge())
        .getNumberOfDoses();
  }
}
