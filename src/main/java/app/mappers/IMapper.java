package app.mappers;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public interface IMapper<T, DTO> {
  public DTO toDto(T t);

  // public T toEntity(DTO dto);
}
