package app.domain.shared;

public enum FieldToValidate {

  CC("Citizen Card"), PHONE_NUMBER("Phone Number"), FAX("Fax"), EMAIL("Email"), WEBSITE(
      "Website"), SNS_NUMBER("SNS Number"), VAC_CODE("Vaccine Code");

  private String value;

  private FieldToValidate(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
