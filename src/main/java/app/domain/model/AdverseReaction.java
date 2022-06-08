package app.domain.model;

public class AdverseReaction {
  private String description;

  /**
   * Constructor for AdverseReaction
   * 
   * @param description the adverse reaction description
   */
  public AdverseReaction(String description) {
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
}
