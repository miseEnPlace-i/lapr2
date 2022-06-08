package app.dto;

import java.util.List;

/**
 * User Vaccination Info DTO
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class UserVaccinationInfoDTO {
  private String name;
  private int age;
  private List<AdverseReactionDTO> adverseReactions;

  /**
   * Constructor for UserVaccinationInfoDTO
   * 
   * @param name the user name
   * @param age the user age
   * @param adverseReactions the adverse reactions
   */
  public UserVaccinationInfoDTO(String name, int age, List<AdverseReactionDTO> adverseReactions) {
    this.name = name;
    this.age = age;
    this.adverseReactions = adverseReactions;
  }

  /**
   * Get the user name
   * 
   * @return the user name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the user age
   * 
   * @return the user age
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Get the adverse reactions
   * 
   * @return the adverse reactions
   */
  public List<AdverseReactionDTO> getAdverseReactions() {
    return this.adverseReactions;
  }
}
