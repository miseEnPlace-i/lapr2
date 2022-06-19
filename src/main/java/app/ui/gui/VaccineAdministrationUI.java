package app.ui.gui;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.controller.App;
import app.controller.ListUsersInWaitingRoomController;
import app.controller.RegisterVaccineAdministrationController;
import app.domain.model.AdverseReaction;
import app.domain.model.Company;
import app.domain.shared.HelpText;
import app.dto.AdverseReactionDTO;
import app.dto.ArrivalDTO;
import app.dto.DosageInfoDTO;
import app.dto.UserVaccinationInfoDTO;
import app.dto.VaccineDTO;
import app.exception.NotAuthorizedException;
import app.mapper.AdverseReactionMapper;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VaccineAdministrationUI extends ChildUI<NurseUI> {
  // Global attributes
  private RegisterVaccineAdministrationController ctrl;
  private ListUsersInWaitingRoomController ctrlList;
  private EmployeeSession employeeSession;
  @FXML
  private Label lblCenter;

  // Select User FXML attributes
  private ArrivalDTO selectedUser;
  @FXML
  private ListView<ArrivalDTO> listUsers;
  @FXML
  private Button btnContinueUsers;
  @FXML
  private Label lblInstructionUsers;

  // User Vaccination Info FXML attributes
  @FXML
  private Label lblUserName;
  @FXML
  private Label lblUserAge;
  @FXML
  private ListView<AdverseReactionDTO> listAdverseReactions;

  // Select Vaccine FXML attributes
  private VaccineDTO selectedVaccine;
  @FXML
  private Button btnContinueVaccines;
  @FXML
  private ListView<VaccineDTO> listVaccines;

  // Vaccine Info FXML attributes
  @FXML
  private Label lblVaccineBrand;
  @FXML
  private Label lblDosage;
  @FXML
  private Label lblDoseNumber;
  @FXML
  private Label lblVaccineDesignation;

  // Lot Number FXML attributes
  @FXML
  private TextField txtLotNumber;
  @FXML
  private Button btnContinueLotNumber;



  // Select User Scene

  /**
   * Initializes Select User scene.
   */
  void init(NurseUI parentUI) {
    this.setParentUI(parentUI);
    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterVaccineAdministrationController(company, this.employeeSession);
    try {
      this.ctrlList = new ListUsersInWaitingRoomController(this.employeeSession);
    } catch (NotAuthorizedException ex) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }


    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());

    List<ArrivalDTO> users = ctrlList.getWaitingRoomListFromNurseCenter();

    if (users.size() > 0) {
      listUsers.getItems().addAll(users);
    } else {
      listUsers.setVisible(false);
      lblInstructionUsers.setText("There are no users in the waiting room.");
    }
  }

  @FXML
  void checkEnableButtonUsers(Event event) {
    btnContinueUsers.setDisable(false);

    selectedUser = listUsers.getSelectionModel().getSelectedItem();
  }

  @FXML
  void handleContinueUsers(ActionEvent event) {
    try {
      VaccineAdministrationUI ui = (VaccineAdministrationUI) super.getParentUI().mainApp.replaceSceneContent("/fxml/RegVacAdminUserData.fxml");
      ui.setParentUI(this.getParentUI());
      ui.initUserInfo(this.selectedUser);
    } catch (Exception e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  // User Info Scene

  /**
   * Initializes User Info scene.
   * 
   * @param selectedUser the user selected in last scene
   */
  void initUserInfo(ArrivalDTO selectedUser) {
    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());

    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterVaccineAdministrationController(company, this.employeeSession);

    this.selectedUser = selectedUser;
    UserVaccinationInfoDTO userInfo = ctrl.getUserVaccinationInfoFromArrival(this.selectedUser);

    this.lblUserName.setText("Name: " + userInfo.getName());
    this.lblUserAge.setText("Age: " + String.valueOf(userInfo.getAge()));

    if (userInfo.getAdverseReactions().size() > 0) {
      this.listAdverseReactions.getItems().addAll(userInfo.getAdverseReactions());
    } else {
      this.listAdverseReactions.getItems().add(AdverseReactionMapper.toDto(new AdverseReaction("No adverse reactions were found.")));
    }
  }

  @FXML
  void handleContinueUserInfo(ActionEvent event) {
    if (ctrl.getLastTakenVaccineFromArrival(this.selectedUser) == null) {
      try {
        VaccineAdministrationUI ui = (VaccineAdministrationUI) super.getParentUI().mainApp.replaceSceneContent("/fxml/RegVacAdminSelectVaccine.fxml");
        ui.setParentUI(this.getParentUI());
        ui.initSelectVaccine(this.selectedUser);
      } catch (Exception e) {
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
      }
    } else {
      try {
        VaccineAdministrationUI ui = (VaccineAdministrationUI) super.getParentUI().mainApp.replaceSceneContent("/fxml/RegVacAdminVaccineData.fxml");
        ui.setParentUI(this.getParentUI());
        ui.initVaccineInfo(this.selectedUser, ctrl.getLastTakenVaccineFromArrival(this.selectedUser));
      } catch (Exception e) {
        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
      }
    }
  }

  // Select Vaccine Scene

  /**
   * Initializes Select Vaccine scene.
   */
  void initSelectVaccine(ArrivalDTO selectedUser) {
    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());

    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterVaccineAdministrationController(company, this.employeeSession);

    this.selectedUser = selectedUser;

    List<VaccineDTO> vaccines = ctrl.getListOfVaccinesWithVaccineTypeOfArrival(selectedUser);
    listVaccines.getItems().addAll(vaccines);
  }

  @FXML
  void checkEnableButtonVaccines(Event event) {
    btnContinueVaccines.setDisable(false);

    selectedVaccine = listVaccines.getSelectionModel().getSelectedItem();
  }

  @FXML
  void handleContinueVaccines(ActionEvent event) {
    try {
      VaccineAdministrationUI ui = (VaccineAdministrationUI) super.getParentUI().mainApp.replaceSceneContent("/fxml/RegVacAdminVaccineData.fxml");
      ui.setParentUI(this.getParentUI());
      ui.initVaccineInfo(this.selectedUser, this.selectedVaccine);
    } catch (Exception e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  // Vaccine Info Scene

  /**
   * Initializes Vaccine Info scene.
   */
  void initVaccineInfo(ArrivalDTO selectedUser, VaccineDTO selectedVaccine) {
    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());

    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterVaccineAdministrationController(company, this.employeeSession);

    this.selectedUser = selectedUser;
    this.selectedVaccine = selectedVaccine;

    this.lblVaccineDesignation.setText("Vaccine: " + selectedVaccine.getDesignation());
    this.lblVaccineBrand.setText("Brand: " + selectedVaccine.getBrand());

    DosageInfoDTO dosageInfoDto = this.ctrl.getDosageInfoFromVaccineBySnsUser(selectedVaccine, selectedUser);

    this.lblDoseNumber.setText("Dose Number: " + dosageInfoDto.getDoseNumber());
    this.lblDosage.setText("Dosage: " + dosageInfoDto.getDosage() + "ml");
  }

  @FXML
  void handleContinueVaccineInfo(ActionEvent event) {
    try {
      VaccineAdministrationUI ui = (VaccineAdministrationUI) super.getParentUI().mainApp.replaceSceneContent("/fxml/RegVacAdminLotNumber.fxml");
      ui.setParentUI(this.getParentUI());
      ui.initLotNumber(this.selectedUser, this.selectedVaccine);
    } catch (Exception e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    }
  }

  // Lot Number Scene

  /**
   * Initializes Lot Number scene.
   */
  void initLotNumber(ArrivalDTO selectedUser, VaccineDTO selectedVaccine) {
    this.lblCenter.setText(this.getParentUI().getVaccinationCenterName());

    Company company = App.getInstance().getCompany();
    this.employeeSession = super.getParentUI().getEmployeeSession();
    this.ctrl = new RegisterVaccineAdministrationController(company, this.employeeSession);

    this.selectedUser = selectedUser;
    this.selectedVaccine = selectedVaccine;

    this.lblVaccineDesignation.setText("Vaccine: " + selectedVaccine.getDesignation());
    this.lblVaccineBrand.setText("Brand: " + selectedVaccine.getBrand());
  }

  @FXML
  void enableContinueLotNumber(Event event) {
    if (txtLotNumber.getText().trim().isEmpty()) {
      btnContinueLotNumber.setDisable(true);
    } else {
      btnContinueLotNumber.setDisable(false);
    }
  }

  @FXML
  void handleContinueLotNumber(ActionEvent event) {
    try {
      DosageInfoDTO dosageInfoDto = this.ctrl.getDosageInfoFromVaccineBySnsUser(selectedVaccine, selectedUser);
      this.ctrl.createVaccineAdministration(this.selectedUser, this.selectedVaccine, this.txtLotNumber.getText(), dosageInfoDto.getDoseNumber());
    } catch (IllegalArgumentException e) {
      Utils.showError("Lot number invalid.", "Please insert a valid lot number.");
      return;
    }

    showDataAndAskToConfirm();

    super.toRoleScene();
  }

  void showDataAndAskToConfirm() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirm the data");
    alert.setHeaderText("Do you want to register the following Vaccine Administration?");
    alert.setContentText(this.ctrl.stringifyData());

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
      this.ctrl.save();
      Alert alertSuccess = new Alert(AlertType.CONFIRMATION);
      alertSuccess.setTitle("Success");
      alertSuccess.setHeaderText("Vaccine Administration registered successfully.");
      alertSuccess.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
      alertSuccess.showAndWait();
    }
  }

  @Override
  void handleHelp(ActionEvent event) {
    Utils.showHelp("Coordinator Help", HelpText.VACCINE_ADMINISTRATION);
  }
}
