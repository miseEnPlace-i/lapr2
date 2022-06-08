package app.mapper;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.AdverseReaction;
import app.dto.AdverseReactionDTO;

/**
 * Adverse Reaction Mapper
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class AdverseReactionMapper {
  /**
   * Private constructor to prevent instantiation from other classes
   */
  private AdverseReactionMapper() {}

  /**
   * Map AdverseReaction to a AdverseReactionDTO
   * 
   * @param description the adverse reaction description
   * 
   * @return the AdverseReactionDTO
   */
  public static AdverseReactionDTO toDto(AdverseReaction adverseReaction) {
    return new AdverseReactionDTO(adverseReaction.getDescription());
  }

  public static List<AdverseReactionDTO> toDto(List<AdverseReaction> adverseReactions) {
    List<AdverseReactionDTO> list = new ArrayList<AdverseReactionDTO>();

    for (AdverseReaction adverseReaction : adverseReactions) {
      list.add(AdverseReactionMapper.toDto(adverseReaction));
    }

    return list;
  }
}
