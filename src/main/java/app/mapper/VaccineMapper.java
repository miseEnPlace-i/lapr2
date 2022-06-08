package app.mapper;

import app.domain.model.Vaccine;
import app.dto.VaccineDTO;

/**
 * User Vaccination Info Mapper
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineMapper {
  // Private constructor to prevent instantiation from other classes
  private VaccineMapper() {}

  public static VaccineDTO toDto(Vaccine vaccine) {
    return new VaccineDTO(vaccine.getId(), vaccine.getBrand(), vaccine.getId(), vaccine.getVacType());
  }
}
