package app.mapper;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.Arrival;
import app.domain.model.WaitingRoom;
import app.dto.ArrivalDTO;

public class WaitingRoomMapper {
  // Private constructor to prevent instantiation from other classes
  private WaitingRoomMapper() {}

  public static List<ArrivalDTO> toDto(WaitingRoom waitingRoom) {
    List<ArrivalDTO> waitingRoomDto = new ArrayList<>();

    for (Arrival arrival : waitingRoom) {
      ArrivalDTO arrivalDto = ArrivalMapper.toDto(arrival);
      waitingRoomDto.add(arrivalDto);
    }

    return waitingRoomDto;
  }
}
