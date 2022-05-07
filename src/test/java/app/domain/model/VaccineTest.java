package app.domain.model;

import org.junit.Before;
import org.junit.Test;
import app.domain.model.list.AdminProcList;
import app.domain.model.list.DoseInfoList;

public class VaccineTest {

  AdminProcList adminProcList;
  DoseInfoList doseInfoList;

  @Before
  public void setUp() {
    adminProcList = new AdminProcList();
    doseInfoList = new DoseInfoList();
  }

  // Vaccine

  @Test(expected = IllegalArgumentException.class)
  public void NullVacArguments() {
    Vaccine instance = new Vaccine(null, null, null, null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void EmptyVacArguments() {
    Vaccine instance = new Vaccine("", "", "", "");
  }



  @Test(expected = IllegalArgumentException.class)
  public void VacNonExistingVacType() {
    Vaccine instance =
        new Vaccine("Pfizer–BioNTech COVID-19", "Pfizer", "vac1", "Not existing vacTypeID");
  }

  // Administration Process

  @Test(expected = IllegalArgumentException.class)
  public void NullAdminProcArguments() {
    AdminProcess adminProcess = new AdminProcess(0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeAdminProcArguments() {
    AdminProcess adminProcess = new AdminProcess(-10, -5, 3);
  }


  @Test(expected = IllegalArgumentException.class)
  public void MaxAgeBiggerThanMinAge() {
    AdminProcess adminProcess = new AdminProcess(18, 2, 3);
  }

  // Dose Information


  @Test(expected = IllegalArgumentException.class)
  public void NullDoseInfoArguments() {
    DoseInfo doseInfo = new DoseInfo(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void NegativeDoseInfoArguments() {
    DoseInfo doseInfo = new DoseInfo(-10, -5);
  }


}
