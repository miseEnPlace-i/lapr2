package app.domain.shared;

public enum HelpText {
  NURSE("Select an option from the Menu to proceed. Click in \"Back To Menu\" to logout and go to the Initial Page."), COORDINATOR(
      "Select an option from the Menu to proceed. Click in \"Back To Menu\" to logout and go to the Initial Page."), RECEPTIONIST(
          "Select an option from the Menu to proceed. Click in \"Back To Menu\" to logout and go to the Initial Page."), ADMINISTRATOR(
              "Select an option from the Menu to proceed. Click in \"Back To Menu\" to logout and go to the Initial Page."), SNS_USER(
                  "Select an option from the Menu to proceed. Click in \"Back To Menu\" to logout and go to the Initial Page."), EMPLOYEE_WITH_CENTER(
                      "Select the Vaccination Center you are working at from the list, and then press \"Confirm\"."), ADVERSE_REACTIONS(
                          "To register an Adverse Reaction, type the SNS Number of the user who suffered the reaction. Enter the Adverse Reaction description (max. 300 characters), and press \"Continue\"."), ANALYSE_CENTER_PERFORMANCE(
                              "Select the day you want to analyse and enter the time interval, in minutes. Then, select \"Analyse\" to continue."), CENTER_STATISTICS(
                                  "To choose the desired interval, select the initial and final date using the Calendar box. Type the name of the file where the data will be exported, and then click \"Export statistics\""), IMPORT_LEGACY_DATA_1(
                                      "Select the file where the data is stored by clicking in \"Select file\". Then, press \"Open file\"."), IMPORT_LEGACY_DATA_2(
                                          "Confirm the data imported from the file. Sort the data using the buttons present in the \"Sort by\" area. To import all users, click \"Import\"."), VACCINE_ADMINISTRATION(
                                              "To register a Vaccine Administration, select an SNS User from the list. Next, select a vaccine from the list. Finally, type the Lot Number and press \"Confirm\"");

  private final String helpText;

  HelpText(String helpText) {
    this.helpText = helpText;
  }

  @Override
  public String toString() {
    return helpText;
  }
}
