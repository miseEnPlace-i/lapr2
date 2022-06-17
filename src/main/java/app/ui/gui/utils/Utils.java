package app.ui.gui.utils;

import java.util.Optional;
import app.controller.App;
import app.domain.shared.HelpText;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Utils {
  // Private constructor to prevent instantiation
  private Utils() {}

  public static void showError(String heading, String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(heading);
    alert.setContentText(message);

    alert.showAndWait();
  }

  public static void showInformation(String heading, String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText(heading);
    alert.setContentText(message);

    alert.showAndWait();
  }

  public static boolean showConfirmation(String heading, String message) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(heading);
    alert.setContentText(message);

    return alert.showAndWait().get() == ButtonType.OK;
  }

  public static void showExitConfirmation() {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Exit?");
    alert.setHeaderText("Do you want to save your changes?");
    alert.setContentText("All unsaved changes will be lost.");

    ButtonType saveButton = new ButtonType("Save");
    ButtonType noButton = new ButtonType("Don't Save");
    ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

    alert.getButtonTypes().setAll(saveButton, noButton, cancelButton);

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == saveButton) {
      App.getInstance().doLogout();
      App.getInstance().saveCurrentCompany();
      Platform.exit();
      System.exit(0);
    } else if (result.get() == noButton) {
      App.getInstance().doLogout();
      Platform.exit();
      System.exit(0);
    }
  }

  public static void showHelp(String title, HelpText text) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(text.toString());

    alert.showAndWait();
  }
}
