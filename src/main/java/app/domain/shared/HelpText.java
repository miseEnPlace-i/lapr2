package app.domain.shared;

public enum HelpText {
  NURSE("Nurse help text"), COORDINATOR("Coordinator help text"), RECEPTIONIST("Receptionist help text"), ADMINISTRATOR("Administrator help text"), SNS_USER(
      "SNS User help text"), EMPLOYEE_WITH_CENTER(
          "Employee with center help text"), ADVERSE_REACTIONS("Adverse Reactions help text"), ANALYSE_CENTER_PERFORMANCE(
              "Analyse center performance help text"), CENTER_STATISTICS("Center statistics help text"), IMPORT_LEGACY_DATA_1(
                  "Import legacy data 1 help text"), IMPORT_LEGACY_DATA_2("Import legacy data 2 help text"), VACCINE_ADMINISTRATION("Vaccine administration");

  private final String helpText;

  HelpText(String helpText) {
    this.helpText = helpText;
  }

  @Override
  public String toString() {
    return helpText;
  }
}
