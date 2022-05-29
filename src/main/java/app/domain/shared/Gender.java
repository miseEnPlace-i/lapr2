package app.domain.shared;

public enum Gender {
  MALE("Male"), FEMALE("Female"), N_A("Not Specified");

  private String value;

  private Gender(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
