package app.domain.model;

import java.io.Serializable;

public class AdverseReaction implements Serializable {
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
