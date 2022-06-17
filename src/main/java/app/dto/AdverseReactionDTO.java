package app.dto;

/**
 * Adverse Reaction DTO
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class AdverseReactionDTO {
  private String description;

  /**
   * Constructor for AdverseReactionDTO
   * 
   * @param description the adverse reaction description
   */
  public AdverseReactionDTO(String description) {
    this.description = description;
  }

  /**
   * Get the adverse reaction description
   * 
   * @return the adverse reaction description
   */
  public String getDescription() {
    return this.description;
  }

  @Override
  public String toString() {
    return this.description;
  }
}
