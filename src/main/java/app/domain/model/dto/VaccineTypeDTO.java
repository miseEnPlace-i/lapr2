package app.domain.model.dto;

public class VaccineTypeDTO {
  private String code;
  private String description;
  private String technology;

  public VaccineTypeDTO(String code, String description, String technology) {
    this.code = code;
    this.description = description;
    this.technology = technology;
  }

  public String getCode() {
    return this.code;
  }

  public String getDescription() {
    return this.description;
  }

  public String getTechnology() {
    return this.technology;
  }
}
