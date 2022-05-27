package app.domain.shared;

public enum FieldToValidate {
  CITIZEN_CARD("Citizen Card"), PHONE_NUMBER("Phone Number"), FAX("Fax Number"), EMAIL("Email"), WEBSITE("URL"), SNS_NUMBER("SNS Number"), VAC_CODE(
      "Vaccine Code"), HOURS("Hours"), DATE_IN_FUTURE("Date In Future");

  // A DATE NAO É PARA APAGAR !!!!!!!!!!!!!!!!!!!!!!

  private String value;

  private FieldToValidate(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
