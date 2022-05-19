package app.service;

public enum FieldsToValidate {

  CC("Citizen Card"), PHONE_NUMBER("Phone Number"), FAX("Fax"), EMAIL("Email"), WEBSITE(
      "Website"), SNS_NUMBER("SNS Number"), VAC_CODE("Vaccine Code");

  private String value;

  private FieldsToValidate(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
