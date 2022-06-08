package app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The CalendarUtils class. It provides an easy way to automatically parse a string, for example in dd/MM/yyyy format,
 * to a Calendar instance, as well as methods to calculate the age for a User.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */
public class CalendarUtils {

  // From https://regexland.com/regex-dates/
  // private static final String[][] VALID_FORMATS = {{"yyyy/MM/dd", "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$"},
  // {"dd/MM/yyyy", "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$"},
  // {"yyyy-MM-dd", "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$"}, {"dd-MM-yyyy",
  // "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$"},};
  private static final String[][] VALID_FORMATS = {{"dd/MM/yyyy", "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$"},};

  /**
   * Parse a String.
   * 
   * @param dateStr the string to be parsed.
   */
  public static Calendar parse(String dateStr) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    for (String[] format : VALID_FORMATS) {
      if (dateStr.matches(format[1])) {
        SimpleDateFormat sdf = new SimpleDateFormat(format[0]);
        calendar.setTime(sdf.parse(dateStr));
        return calendar;
      }
    }

    throw new IllegalArgumentException("Invalid date format");
  }

  /**
   * Calculates the age.
   * 
   * @return the age given the birthday.
   */
  public static int calculateAge(Calendar birthDay) {
    Calendar today = Calendar.getInstance();
    int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
    if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)) {
      age--;
    }
    return age;
  }

  /**
   * Calendar to string in dd/MM/yyyy format.
   * 
   * @param calendar
   */
  public static String calendarToString(Calendar calendar) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(calendar.getTime());
  }

  /**
   * Date + String Time to Calendar
   * 
   * @param date the Date object hat contains the date (dd/MM/yyyy)
   * @param time the string that contains the time (HH:MM)
   * 
   * @return a Calendar object with date and time
   */
  public static Calendar parseDateTime(Date date, String time) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dateStr = sdf.format(date);

    String dateAndTime = dateStr + " " + time;

    sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(sdf.parse(dateAndTime));

    return calendar;
  }
}
