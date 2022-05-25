package app.mappers;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Arrival;
import app.domain.model.WaitingRoom;
import app.domain.model.dto.ArrivalDTO;

public class WaitingRoomMapper {
  // Private constructor to prevent instantiation from other classes
  private WaitingRoomMapper() {}

  // TODO implement this method
  public static List<ArrivalDTO> toDto(WaitingRoom waitingRoom) {
    List<ArrivalDTO> waitingRoomDTO = new ArrayList<>();

    for (Arrival arrival : waitingRoom) {
      ArrivalDTO arrivalDto = ArrivalMapper.toDto(arrival);
      waitingRoomDTO.add(arrivalDto);
    }

    return waitingRoomDTO;
  }
}
