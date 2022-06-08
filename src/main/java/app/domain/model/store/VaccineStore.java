package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.dto.VaccineDTO;
import app.mapper.VaccineMapper;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class VaccineStore {
  private List<Vaccine> vaccines;


  public VaccineStore() {
    this.vaccines = new ArrayList<Vaccine>();
  }

  public Vaccine createVaccine(String designation, String id, String brand, VaccineType vacType) {
    Vaccine vac = new Vaccine(designation, id, brand, vacType);

    return vac;
  }

  public void saveVaccine(Vaccine vac) {
    vaccines.add(vac);
  }

  public boolean exists(String designation) {
    for (Vaccine vac : vaccines)
      if (vac.getDesignation() == designation) return true;
    return false;
  }

  public int size() {
    return vaccines.size();
  }

  public List<Vaccine> getVaccinesByType(VaccineType vt) {
    List<Vaccine> vaccinesList = new ArrayList<Vaccine>();

    for (Vaccine vaccine : vaccines) {
      if (vaccine.getVacType().equals(vt)) {
        vaccinesList.add(vaccine);
      }
    }

    return vaccinesList;
  }

  public Vaccine findVaccineById(String id) {
    for (Vaccine vaccine : vaccines) {
      if (vaccine.getId().equals(id)) {
        return vaccine;
      }
    }

    return null;
  }

  // public boolean areVaccinesWithValidAdminProcessWithVaccineType(int age, VaccineType vaccineType) {
  // List<Vaccine> vaccinesList = getVaccinesByType(vaccineType);

  // for (Vaccine vaccine : vaccinesList) {
  // if (vaccine.hasAdministrationProcessForGivenAge(age)) {
  // return true;
  // }
  // }

  // return false;
  // }

  public List<VaccineDTO> getVaccinesByVaccineTypeWithAdminProcessForAge(VaccineType vaccineType, int age) {
    List<VaccineDTO> vaccinesList = new ArrayList<VaccineDTO>();

    for (Vaccine vaccine : vaccines) {
      if (vaccine.getVacType().equals(vaccineType)) {
        if (vaccine.hasAdministrationProcessForGivenAge(age)) {
          vaccinesList.add(VaccineMapper.toDto(vaccine));
        }
      }
    }

    return vaccinesList;
  }
}
