package app.mappers;

import app.domain.model.VaccinationCenter;
import app.domain.model.dto.VaccinationCenterListDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccinationCenterMapper
    implements IMapper<VaccinationCenter, VaccinationCenterListDTO> {
  // Private constructor to prevent instantiation from other classes
  private VaccinationCenterMapper() {}

  public VaccinationCenterListDTO toDto(VaccinationCenter vaccinationCenter) {
    return new VaccinationCenterListDTO(vaccinationCenter.getName(), vaccinationCenter.getAddress(),
        vaccinationCenter.getEmail(), vaccinationCenter.getPhone());
  }
}
