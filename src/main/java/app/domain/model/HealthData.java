package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class HealthData {
  List<Vaccine> vaccinesTaken;

  public HealthData() {
    this.vaccinesTaken = new ArrayList<>();
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
}
