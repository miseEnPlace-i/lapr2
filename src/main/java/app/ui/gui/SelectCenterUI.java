package app.ui.gui;

import app.dto.VaccinationCenterListDTO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class SelectCenterUI extends RoleUI {
  @FXML
  private ListView<VaccinationCenterListDTO> lstCenters;

  @FXML
  private Button btnConfirm;

  @Override
  void init() {
    // TODO populate lst centers
    lstCenters.getItems().add(new VaccinationCenterListDTO("type", "name", "address", "email", "phone", "openingHours", "closingHours"));
  }

  @Override
  public String getUIRoleName() {
    return "";
  }
}
