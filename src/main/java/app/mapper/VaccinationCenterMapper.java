package app.mapper;

import app.controller.App;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.HealthCareCenter;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.VaccinationCenterStore;
import app.dto.VaccinationCenterListDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccinationCenterMapper {
  // Private constructor to prevent instantiation from other classes
  private VaccinationCenterMapper() {}

  public static VaccinationCenterListDTO toDto(VaccinationCenter vaccinationCenter) {
    String type = "";

    if (vaccinationCenter instanceof HealthCareCenter) type = "Health Care Center";
    else if (vaccinationCenter instanceof CommunityMassVaccinationCenter) type = "Community Mass Vaccination Center";

    String name = vaccinationCenter.getName();
    String address = vaccinationCenter.getAddress();
    String email = vaccinationCenter.getEmail();
    String phone = vaccinationCenter.getPhone();
    String openingHours = vaccinationCenter.getOpeningHours().toString();
    String closingHours = vaccinationCenter.getClosingHours().toString();

    return new VaccinationCenterListDTO(type, name, address, email, phone, openingHours, closingHours);
  }

  public static VaccinationCenter toModel(VaccinationCenterListDTO vaccinationCenterDTO) {
    VaccinationCenterStore vaccinationCenterStore = App.getInstance().getCompany().getVaccinationCenterStore();
    VaccinationCenter vaccinationCenter = vaccinationCenterStore.getVaccinationCenterByEmail(vaccinationCenterDTO.getEmail());

    return vaccinationCenter;
  }
}
