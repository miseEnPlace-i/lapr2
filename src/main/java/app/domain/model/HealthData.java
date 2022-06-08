package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.dto.AdverseReactionDTO;
import app.mapper.AdverseReactionMapper;

public class HealthData {
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

  public boolean hasAppointmentForVaccineType(VaccineType vaccineType) {
    for (Appointment appointment : appointments) {
      if (appointment.hasSnsNumber(snsUser.getSnsNumber()) && appointment.hasVaccineType(vaccineType)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get the VaccineAdministrationList.
   * 
   * @return the vaccineAdministrationList
   */
  public VaccineAdministrationList getVaccineAdministrationList() {
    return vaccineAdministrationList;
  }

  public boolean hasAppointmentForVaccineType(VaccineType vaccineType, String number) {
    for (Appointment appointment : appointments) {
      if (appointment.hasSnsNumber(number) && appointment.hasVaccineType(vaccineType)) {
        return true;
      }
    }
    return false;
  }
}
