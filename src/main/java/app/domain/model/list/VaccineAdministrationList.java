package app.domain.model.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import app.dto.AdverseReactionDTO;
import app.dto.VaccineDTO;
import app.domain.model.AdverseReaction;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.mapper.AdverseReactionMapper;
import app.mapper.VaccineMapper;
import app.domain.model.VaccineAdministration;

public class VaccineAdministrationList implements Serializable {
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
   * Validates a vaccine administration object.
   * 
   * @param vaccineAdministration the vaccine administration object to be validated
   */
  public void validateVaccineAdministration(VaccineAdministration vaccineAdministration) {

  }

  /**
   * Saves a vaccine administration object.
   * 
   * @param vaccineAdministration the vaccine administration object to be saved
   */
  public void saveVaccineAdministration(VaccineAdministration vaccineAdministration) {
    vaccineAdministrations.add(vaccineAdministration);

    VaccinationCenter vaccinationCenter = vaccineAdministration.getVaccinationCenter();

    vaccinationCenter.addVaccineAdministrationToList(vaccineAdministration);

    // vaccineAdministration.setSMSSending();
  }

  /**
   * Gets the list of adverse reactions.
   * 
   * @return a list of AdverseReactionDTO
   */
  public List<AdverseReactionDTO> getAdverseReactions() {
    List<AdverseReaction> adverseReactions = new ArrayList<>();

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.getAdverseReaction() != null) {
        adverseReactions.add(vaccineAdministration.getAdverseReaction());
      }
    }

    return AdverseReactionMapper.toDto(adverseReactions);
  }

  /**
   * Get the last taken vaccine of a given vaccine type by a user.
   * 
   * @param VaccineType the vaccine type
   * 
   * @return the last taken vaccine of a given vaccine type by a user
   */
  public VaccineDTO getLastTakenVaccineByVaccineType(VaccineType vaccineType) {
    Collections.sort(vaccineAdministrations);

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.vaccineHasVaccineType(vaccineType)) {
        return VaccineMapper.toDto(vaccineAdministration.getVaccine());
      }
    }

    return null;
  }

  /**
   * Get the next dose number to administer to a user.
   * 
   * @param Vaccine the vaccine to check the next dose number
   * 
   * @return the next dose number to administer to a user
   */
  public int getNextDoseNumberOfVaccine(Vaccine vaccine) {
    Collections.sort(vaccineAdministrations);

    for (VaccineAdministration vaccineAdministration : vaccineAdministrations) {
      if (vaccineAdministration.getVaccine().equals(vaccine)) {
        return vaccineAdministration.getDoseNumber() + 1;
      }
    }

    return 1;
  }
}
