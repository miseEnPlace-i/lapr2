package app.domain.shared;

public enum MenuFXMLPath {
  RECEPTIONIST("/fxml/ReceptionistMenu.fxml"), COORDINATOR("/fxml/CoordinatorMenu.fxml");

  private String path;


  private MenuFXMLPath(String path) {
    this.path = path;
  }

  @Override
  public String toString() {
    return path;
  }
}
