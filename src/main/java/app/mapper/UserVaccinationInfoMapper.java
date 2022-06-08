package app.mapper;

import java.util.List;
import app.dto.AdverseReactionDTO;
import app.dto.UserVaccinationInfoDTO;

/**
 * User Vaccination Info Mapper
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt
 */
public class UserVaccinationInfoMapper {
  /**
   * Private constructor to prevent instantiation from other classes
   */
  private UserVaccinationInfoMapper() {}

  /**
   * Map UserVaccinationInfo to a UserVaccinationInfoDTO
   * 
   * @param name the user name
   * @param age the user age
   * @param adverseReactions the adverse reactions
   * 
   * @return the UserVaccinationInfoDTO
   */
  public static UserVaccinationInfoDTO toDto(String name, int age, List<AdverseReactionDTO> adverseReactions) {
    return new UserVaccinationInfoDTO(name, age, adverseReactions);
  }
}
