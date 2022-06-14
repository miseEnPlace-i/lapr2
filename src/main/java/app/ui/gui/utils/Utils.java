package app.ui.gui.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {
  public static void showError(String heading, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(heading);
    alert.setContentText(message);

    alert.showAndWait();
  }
}
