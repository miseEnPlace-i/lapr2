package app.mapper;

import app.controller.App;
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
    return new VaccinationCenterListDTO(vaccinationCenter.getName(), vaccinationCenter.getAddress(), vaccinationCenter.getEmail(), vaccinationCenter.getPhone(),
        vaccinationCenter.getOpeningHours().toString(), vaccinationCenter.getClosingHours().toString());
  }

  public static VaccinationCenter toModel(VaccinationCenterListDTO vaccinationCenterDTO) {
    VaccinationCenterStore vaccinationCenterStore = App.getInstance().getCompany().getVaccinationCenterStore();
    VaccinationCenter vaccinationCenter = vaccinationCenterStore.getVaccinationCenterByEmail(vaccinationCenterDTO.getEmail());

    return vaccinationCenter;
  }
}
