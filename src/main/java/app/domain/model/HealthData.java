package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class HealthData {
  SNSUser snsUser;
  List<Vaccine> vaccinesTaken;
  List<Appointment> appointments;

  public HealthData(SNSUser snsUser) {
    this.vaccinesTaken = new ArrayList<>();
    this.appointments = new ArrayList<>();
    this.snsUser = snsUser;
  }

  public void addVaccine(Vaccine vaccine) {
    this.vaccinesTaken.add(vaccine);
  }

  public boolean hasTakenVaccine(Vaccine vaccine) {
    return this.vaccinesTaken.contains(vaccine);
  }

  public Vaccine getLastVaccineTakenWithType(VaccineType vaccineType) {
    for (int i = this.vaccinesTaken.size() - 1; i >= 0; i--)
      if (this.vaccinesTaken.get(i).getVacType().equals(vaccineType))
        return this.vaccinesTaken.get(i);

    return null;
  }

  public void addAppointment(Appointment appointment) {
    this.appointments.add(appointment);
  }

  public boolean hasAppointmentForVaccineType(VaccineType vaccineType) {
    for (Appointment appointment : appointments) {
      if (appointment.hasSnsNumber(snsUser.getSnsNumber())
          && appointment.hasVaccineType(vaccineType)) {
        return true;
      }
    }
    return false;
  }
}
