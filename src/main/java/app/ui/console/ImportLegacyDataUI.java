package app.ui.console;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import app.controller.App;
import app.controller.ImportLegacyDataController;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.dto.LegacyDataDTO;
import app.exception.NotFoundException;
import app.ui.console.utils.Utils;

public class ImportLegacyDataUI implements Runnable {
  private ImportLegacyDataController ctrl;

  public ImportLegacyDataUI(VaccinationCenter center) {
    App app = App.getInstance();
    Company company = app.getCompany();
    this.ctrl = new ImportLegacyDataController(company, center);
  }

  @Override
  public void run() {
    System.out.println("\nImport Legacy Data UI:");

    String filepath = Utils.readLineFromConsole("Legacy data file path: ");

    // get only the file name from the path
    int lastIndex = filepath.lastIndexOf("/");
    String filename = filepath.substring(lastIndex + 1);

    String answer = String.format(
        "Are you sure you want to import the legacy data file %s? (y/n)",
        filename);

    // confirm the filepath
    if (!Utils.confirm(answer)) return;

    // process it
    try {
      List<String[]> fileData = this.ctrl.read(filepath);

      // convert to dto
      List<LegacyDataDTO> legacyDtoList = this.ctrl.convert(fileData);

      this.ctrl.validate(legacyDtoList);
    } catch (FileNotFoundException e) {
      System.out.println("Error: file not found.");
      e.printStackTrace();
    } catch (ParseException e) {
      System.out.println(
          "Error: the file content could not be parsed (it probably contains errors!).");
      e.printStackTrace();
    } catch (NotFoundException e) {
      System.out.printf("Error: %s not found.\n", e.getMessage());
      e.printStackTrace();
    } catch (ClassNotFoundException | InstantiationException
        | IllegalAccessException | NoSuchMethodException
        | InvocationTargetException e) {
      System.out.println(
          "Something wrong has happened. Here's something that might help:");
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

  }
}
