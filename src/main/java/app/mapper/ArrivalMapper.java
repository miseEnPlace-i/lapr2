package app.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.domain.shared.Gender;
import app.dto.ArrivalDTO;

public class ArrivalMapper {
  // Private constructor to prevent instantiation from other classes
  private ArrivalMapper() {}

  public static ArrivalDTO toDto(Arrival arrival) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat sdfWithHour = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    SNSUser snsUser = arrival.getSNSUser();
    String name = snsUser.getName();
    Gender gender = snsUser.getGender();
    Date birthDate = snsUser.getBirthDay();
    String snsUserNumber = snsUser.getSnsNumber();
    String phoneNumber = snsUser.getPhoneNumber();
    Date time = arrival.getArrivalTime().getTime();
    Appointment appointment = arrival.getAppointment();

    return new ArrivalDTO(name, gender, sdf.format(birthDate), snsUserNumber, phoneNumber, sdfWithHour.format(time), appointment);
  }
}
