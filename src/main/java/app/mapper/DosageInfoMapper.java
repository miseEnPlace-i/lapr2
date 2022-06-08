package app.mapper;

import app.dto.DosageInfoDTO;

/**
 * Dosage Info Mapper
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class DosageInfoMapper {
  // Private constructor to prevent instantiation from other classes
  private DosageInfoMapper() {}

  /**
   * Maps a DosageInfo to a DosageInfoDTO.
   * 
   * @param doseNumber the dose number
   * @param dosage the dosage
   * 
   * @return the DosageInfoDTO
   */
  public static DosageInfoDTO toDto(int doseNumber, int dosage) {
    return new DosageInfoDTO(doseNumber, dosage);
  }
}
