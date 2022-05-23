package app.domain.shared;

public enum FieldToValidate {

  CC("Citizen Card"), PHONE_NUMBER("Phone Number"), FAX("Fax Number"), EMAIL("Email"), WEBSITE(
      "URL"), SNS_NUMBER("SNS Number"), VAC_CODE("Vaccine Code"), HOURS(
          "Hours"), SLOT_DURATION("Slot Duration"), MAX_VAC_PER_SLOT("Max Vac Per Slot");

  private String value;

  private FieldToValidate(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
