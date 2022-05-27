package app.mapper;

import java.text.SimpleDateFormat;
import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.dto.ArrivalDTO;

public class ArrivalMapper {
  // Private constructor to prevent instantiation from other classes
  private ArrivalMapper() {}

  // TODO implement this method
  public static ArrivalDTO toDto(Arrival arrival) {
    SNSUser user = arrival.getSNSUser();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    String formattedDate = sdf.format(arrival.getArrivalTime().getTime());

    return new ArrivalDTO(user.getName(), user.getGender(), user.getBirthDay(), user.getSnsNumber(), user.getPhoneNumber(), formattedDate);
  }
}
