package app.domain.shared;

public enum HelpText {
  NURSE("Nurse help text"), COORDINATOR("Coordinator help text"), RECEPTIONIST("Receptionist help text"), ADMINISTRATOR("Administrator help text"), SNS_USER(
      "SNS User help text"), EMPLOYEE_WITH_CENTER("Employee with center help text");

  private final String helpText;

  HelpText(String helpText) {
    this.helpText = helpText;
  }

  @Override
  public String toString() {
    return helpText;
  }
}
