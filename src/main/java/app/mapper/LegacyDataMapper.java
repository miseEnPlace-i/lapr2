package app.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import app.dto.LegacyDataDTO;

public class LegacyDataMapper {
  private static final String FIRST = "Primeira";
  private static final String SECOND = "Segunda";
  private static final String THIRD = "Terceira";
  private static final String FOURTH = "Quarta";

  // Private constructor to prevent instantiation from other classes
  private LegacyDataMapper() throws IllegalAccessException {
    throw new IllegalAccessException();
  }

  public static LegacyDataDTO toDto(String[] fileData) throws ParseException {
    if (fileData == null || fileData.length < 8) throw new IllegalArgumentException();

    int dose = 0;

    switch (fileData[2]) {
      case FIRST:
        dose = 1;
        break;
      case SECOND:
        dose = 2;
        break;
      case THIRD:
        dose = 3;
        break;
      case FOURTH:
        dose = 4;
        break;
    }

    Calendar scheduledDate = Calendar.getInstance();
    Calendar arrivalDate = Calendar.getInstance();
    Calendar administrationDate = Calendar.getInstance();
    Calendar departureDate = Calendar.getInstance();

    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    scheduledDate.setTime(df.parse(fileData[4]));
    arrivalDate.setTime(df.parse(fileData[5]));
    administrationDate.setTime(df.parse(fileData[6]));
    departureDate.setTime(df.parse(fileData[7]));

    return new LegacyDataDTO(fileData[0], fileData[1], dose, fileData[3], scheduledDate, arrivalDate, administrationDate, departureDate);
  }
}
