package app.mappers;

import app.domain.model.Arrival;
import app.domain.model.WaitingRoom;
import app.domain.model.dto.WaitingRoomDTO;

public class WaitingRoomMapper implements IMapper<WaitingRoom, WaitingRoomDTO> {
  // Private constructor to prevent instantiation from other classes
  private WaitingRoomMapper() {}

  // TODO implement this method
  public WaitingRoomDTO toDto(WaitingRoom waitingRoom) {
    for (Arrival arrival : waitingRoom) {
      // arrivalDto = ArrivalMapper.toDto(arrival);
    }
    return null;
  }
}
