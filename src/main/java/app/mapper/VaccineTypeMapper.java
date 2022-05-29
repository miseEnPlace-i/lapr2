package app.mapper;

import app.domain.model.VaccineType;
import app.dto.VaccineTypeDTO;

/**
 * Vaccine type Mapper.
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class VaccineTypeMapper {
  // Private constructor to prevent instantiation from other classes
  private VaccineTypeMapper() {}

  public static VaccineTypeDTO toDto(VaccineType vaccineType) {
    return new VaccineTypeDTO(vaccineType.getCode(), vaccineType.getDescription(), vaccineType.getTechnology());
  }

  public static VaccineType toModel(VaccineTypeDTO vaccineTypeDto) {
    return new VaccineType(vaccineTypeDto.getCode(), vaccineTypeDto.getDescription(), vaccineTypeDto.getTechnology());
  }
}
