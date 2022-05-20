package app.mappers;

public interface Mapper<T, DTO> {
  public DTO toDto(T t);

  // public T toEntity(DTO dto);
}
