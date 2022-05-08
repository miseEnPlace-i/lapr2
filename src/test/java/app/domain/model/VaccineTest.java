package app.domain.model;

import org.junit.Test;
import app.domain.model.list.AdminProcList;
import app.domain.model.list.DoseInfoList;

public class VaccineTest {
  AdminProcList adminProcList;
  DoseInfoList doseInfoList;

  @Test(expected = IllegalArgumentException.class)
  public void NullVacArguments() {
    new Vaccine(null, null, null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void EmptyVacArguments() {
    new Vaccine("", "", "", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void VacNonExistingVacType() {
    VaccineType notExistingVacT = null;
    new Vaccine("Pfizer–BioNTech COVID-19", "Pfizer", "vac1", notExistingVacT);
  }

  // Administration Process
  @Test(expected = IllegalArgumentException.class)
  public void NullAdminProcArguments() {
    new AdminProcess(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeAdminProcArguments() {
    new AdminProcess(-10, -5, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void MaxAgeBiggerThanMinAge() {
    new AdminProcess(18, 2, 3);
  }

  // Dose Information
  @Test(expected = IllegalArgumentException.class)
  public void NullDoseInfoArguments() {
    new DoseInfo(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeDoseInfoArguments() {
    new DoseInfo(-10, -5);
  }
}
