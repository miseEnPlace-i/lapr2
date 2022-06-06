package app.ui.gui;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
  private Stage stage;
  private final double MINIMUM_WINDOW_WIDTH = 840.0;
  private final double MINIMUM_WINDOW_HEIGHT = 600.0;
  private final double SCENE_WIDTH = 1000.0;
  private final double SCENE_HEIGHT = 600.0;

  @Override
  public void start(Stage stage) throws Exception {
    this.stage = stage;
    stage.setTitle("DGS Application");
    stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
    stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
    toMainScene();
    replaceSceneContent("/fxml/BaseScreen.fxml");
    this.stage.show();
  }

  public Stage getStage() {
    return this.stage;
  }

  public void toMainScene() {
    try {
      MainUI mainUI = (MainUI) replaceSceneContent("/fxml/Main.fxml");
      mainUI.setMainApp(this);
    } catch (Exception ex) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public Initializable replaceSceneContent(String fxml) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    InputStream in = App.class.getResourceAsStream(fxml);
    loader.setBuilderFactory(new JavaFXBuilderFactory());
    loader.setLocation(App.class.getResource(fxml));
    Pane page;

    try {
      page = (Pane) loader.load(in);
    }
    finally {
      in.close();
    }

    Scene scene = new Scene(page, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add("/styles/Styles.css");
    this.stage.setScene(scene);
    this.stage.sizeToScene();

    return (Initializable) loader.getController();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
