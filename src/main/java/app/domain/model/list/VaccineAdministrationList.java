package app.domain.model.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.model.VaccineAdministration;

public class VaccineAdministrationList {
  private List<VaccineAdministration> vaccineAdministrations;

  /**
   * Constructor for VaccineAdministrationList.
   */
  public VaccineAdministrationList() {
    this.vaccineAdministrations = new ArrayList<VaccineAdministration>();
  }

  /**
   * Creates a new vaccine administration object.
   * 
   * @param snsUser the snsUser who got the vaccine
   * @param vaccine the vaccine that was administered
   * @param lotNumber the lot number of the vaccine
   * @param doseNumber the dose number of the vaccine
   * @param vaccinationCenter the vaccination center where the vaccine was administered
   * @param date the date the vaccine was administered
   */
  public VaccineAdministration createVaccineAdministration(SNSUser snsUser, Vaccine vaccine, String lotNumber, int doseNumber,
      VaccinationCenter vaccinationCenter, Calendar date) {
    VaccineAdministration vaccineAdministration = new VaccineAdministration(snsUser, vaccine, lotNumber, doseNumber, vaccinationCenter, date);

    return vaccineAdministration;
  }

  /**
   * Saves a vaccine administration object.
   * 
   * @param vaccineAdministration the vaccine administration object to be saved
   */
  public void saveVaccineAdministration(VaccineAdministration vaccineAdministration) {
    vaccineAdministrations.add(vaccineAdministration);
  }


}
