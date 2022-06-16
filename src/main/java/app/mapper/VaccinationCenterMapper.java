package app.mapper;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Address;
import app.domain.model.CommunityMassVaccinationCenter;
import app.domain.model.HealthCareCenter;
import app.domain.model.VaccinationCenter;
import app.dto.VaccinationCenterListDTO;

/**
 * Vaccination Center mapper
 * 
 * @autor Tomás Lopes <1211289@isep.ipp.pt>
 */
public class VaccinationCenterMapper {
  // Private constructor to prevent instantiation from other classes
  private VaccinationCenterMapper() {}

  public static VaccinationCenterListDTO toDto(VaccinationCenter vaccinationCenter) {
    String type = "";

    if (vaccinationCenter instanceof HealthCareCenter) type = "Health Care Center";
    else if (vaccinationCenter instanceof CommunityMassVaccinationCenter) type = "Community Mass Vaccination Center";

    String name = vaccinationCenter.getName();
    Address address = vaccinationCenter.getAddress();
    String email = vaccinationCenter.getEmail();
    String phone = vaccinationCenter.getPhone();
    String openingHours = vaccinationCenter.getOpeningHours().toString();
    String closingHours = vaccinationCenter.getClosingHours().toString();

    return new VaccinationCenterListDTO(type, name, address, email, phone, openingHours, closingHours);
  }

  public static List<VaccinationCenterListDTO> toDto(List<VaccinationCenter> vaccinationCenters) {
    List<VaccinationCenterListDTO> centers = new ArrayList<VaccinationCenterListDTO>();

    for (VaccinationCenter vaccinationCenter : vaccinationCenters) {
      VaccinationCenterListDTO vaccinationCenterDTO = VaccinationCenterMapper.toDto(vaccinationCenter);

      centers.add(vaccinationCenterDTO);
    }

    return centers;
  }
}
