package app.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

/**
 * The CalendarUtils class. It provides an easy way to automatically parse a string, for example in dd/MM/yyyy format,
 * to a Calendar instance, as well as methods to calculate the age for a User.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class TimeUtils {
  /**
   * Calculates the age.
   * 
   * @return the age given the birthday.
   */
  public static int calculateAge(Date birthDay) {
    Calendar today = Calendar.getInstance();
    Calendar birthDate = DateUtils.toCalendar(birthDay);

    int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

    if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
      age--;
    }

    return age;
  }

  public static String dateToString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(date);
  }
}
