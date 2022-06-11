package app.domain.model;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import app.domain.shared.CenterEventType;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.utils.Time;

public class CenterPerformanceTest {
  VaccinationCenter center;

  @Before
  public void setup() throws ParseException {
    Employee coordinator = new Employee("123456789", "name", "+351212345678", "email@email.com", "address", "000000000ZZ4", Constants.ROLE_COORDINATOR);

    center = new HealthCareCenter("name", "address", "email@email.com", "+351212345678", "+351212345678", "https://ccc.com", new Time("10:00"),
        new Time("11:00"), new Slot(5, 5), coordinator, "ages", "ars");

    new VaccineType("12345", "description", "technology");
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    SimpleDateFormat sdfBday = new SimpleDateFormat("dd-MM-yyyy");
    SNSUser user = new SNSUser("000000000ZZ4", "123456789", "name", sdfBday.parse("01-02-2022"), Gender.MALE, "+351212345678", "email@email.com", "address");

    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:00")), CenterEventType.ARRIVAL, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:00")), CenterEventType.ARRIVAL, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:00")), CenterEventType.ARRIVAL, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:00")), CenterEventType.ARRIVAL, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:10")), CenterEventType.ARRIVAL, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:10")), CenterEventType.DEPARTURE, user);
    this.center.getEvents().create(DateUtils.toCalendar(sdf.parse("05-10-2022 10:20")), CenterEventType.DEPARTURE, user);
  }


  @Test
  public void ensureMaxSumIsCorrect() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    CenterPerformance performance = center.getCenterPerformanceForDay(DateUtils.toCalendar(sdf.parse("05-10-2022")), 5);

    assertEquals(performance.getMaxSum(), 4);
  }

  @Test
  public void ensureDifferencesListSizeIsCorrect() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    CenterPerformance performance = center.getCenterPerformanceForDay(DateUtils.toCalendar(sdf.parse("05-10-2022")), 60);

    assertEquals(1, performance.getDifferencesList().size());
  }

  @Test
  public void ensureDifferencesListIsCorrect() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    CenterPerformance performance = center.getCenterPerformanceForDay(DateUtils.toCalendar(sdf.parse("05-10-2022")), 60);

    Integer[] expected = new Integer[] {3};
    assertEquals(Arrays.asList(expected), performance.getDifferencesList());
  }

  @Test
  public void ensureDifferencesListWithTinyIntervalIsCorrect() throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    CenterPerformance performance = center.getCenterPerformanceForDay(DateUtils.toCalendar(sdf.parse("05-10-2022")), 10);

    Integer[] expected = new Integer[] {4, 0, -1, 0, 0, 0};
    assertEquals(Arrays.asList(expected), performance.getDifferencesList());
  }
}
