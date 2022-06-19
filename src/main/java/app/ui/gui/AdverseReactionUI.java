package app.ui.gui;

import java.util.Optional;
import app.controller.App;
import app.controller.RegisterAdverseReactionController;
import app.domain.model.Company;
import app.domain.shared.HelpText;
import app.session.EmployeeSession;
import app.ui.gui.utils.Utils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdverseReactionUI extends ChildUI<NurseUI> {
  private EmployeeSession employeeSession;
  private RegisterAdverseReactionController ctrl;

  @FXML
  private Label lblCenter;

  @FXML
  private TextField txtSnsUserNumber;

  @FXML
  private TextArea txtAdverseReaction;

  @FXML
  private Button btnConfirm;

  /**
   * Initializes Adverse Reaction scene.
   */
  void init(NurseUI parentUI) {
    this.setParentUI(parentUI);
    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterAdverseReactionController(company);

    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());
  }

  @FXML
  void enableConfirm(Event event) {
    if (txtSnsUserNumber.getText().trim().isEmpty() || txtAdverseReaction.getText().trim().isEmpty()) {
      btnConfirm.setDisable(true);
    } else {
      btnConfirm.setDisable(false);
    }
  }

  @FXML
  void handleConfirm(ActionEvent event) {
    try {
      this.ctrl.createAdverseReaction(txtSnsUserNumber.getText(), txtAdverseReaction.getText());
    } catch (IllegalArgumentException e) {
      Utils.showError("SNS User not found.", "Please insert a valid SNS User Number.");
      txtSnsUserNumber.clear();
      txtSnsUserNumber.requestFocus();
      return;
    }

    showDataAndAskToConfirm();

    super.toRoleScene();
  }

  void showDataAndAskToConfirm() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirm the data");
    alert.setHeaderText("Do you want to register the following Adverse Reaction?");
    alert.setContentText(this.ctrl.stringifyData());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      this.ctrl.save();
      Alert alertSuccess = new Alert(AlertType.CONFIRMATION);
      alertSuccess.setTitle("Success");
      alertSuccess.setHeaderText("Adverse Reaction registered successfully.");
      alertSuccess.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
      alertSuccess.showAndWait();
    }
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Coordinator Help", HelpText.ADVERSE_REACTIONS);
  }
}
