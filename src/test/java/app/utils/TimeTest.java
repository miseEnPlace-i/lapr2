package app.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import org.junit.Test;

/**
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class TimeTest {
  @Test(expected = IllegalArgumentException.class)
  public void ensureEmptyIsNotAllowed() {
    new Time("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNullCalendarIsNotAllowed() {
    new Time((Calendar) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNullStringIsNotAllowed() {
    new Time((String) null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureMalformedHoursStringIsNotAllowed() {
    new Time(":00");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureMalformedMinutesStringIsNotAllowed() {
    new Time("10:");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureStringWithSpacesInMinutesIsNotAllowed() {
    new Time("10: ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureStringWithSpacesInHoursIsNotAllowed() {
    new Time(" :10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureMalformedStringIsNotAllowed() {
    new Time("1000");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureMalformedStringInHoursAndMinutesIsNotAllowed() {
    new Time(":");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidTimeFormatIsNotAllowed() {
    new Time("10:10:10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidHourIsNotAllowed() {
    new Time("24:00");
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureInvalidMinuteIsNotAllowed() {
    new Time("22:60");
  }

  @Test
  public void ensureMidnightIsValid() {
    Time time = new Time("00:00");
    assertNotNull(time);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeHoursAreNotAllowed() {
    new Time(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeMinutesAreNotAllowed() {
    new Time(0, -1);
  }

  @Test
  public void ensureIsPossibleToInstantiateWithIntegers() {
    Time time = new Time(1, 0);
    assertNotNull(time);
  }

  @Test
  public void ensureLastMinuteOfDayIsValid() {
    Time time = new Time(23, 59);
    assertNotNull(time);
  }

  @Test
  public void ensureToStringIsWorking() {
    Time time = new Time(23, 59);
    assertEquals(time.toString(), "23:59");
  }

  @Test
  public void ensureIsAfterIsWorking() {
    Time time1 = new Time(23, 59);
    Time time2 = new Time(0, 0);
    assertTrue(time1.isAfter(time2));
  }

  @Test
  public void ensureIsAfterIsWorkingWhenHoursAreEqual() {
    Time time1 = new Time(10, 35);
    Time time2 = new Time(10, 30);
    assertTrue(time1.isAfter(time2));
  }

  @Test
  public void ensureIsBeforeIsWorking() {
    Time time1 = new Time(23, 59);
    Time time2 = new Time(0, 0);
    assertTrue(time2.isBefore(time1));
  }

  @Test
  public void ensureIsBeforeIsWorkingWhenHoursAreEqual() {
    Time time1 = new Time(10, 10);
    Time time2 = new Time(10, 15);
    assertTrue(time1.isBefore(time2));
  }

  @Test
  public void ensureIsBeforeIsFalseWhenDatesAreEqual() {
    Time time = new Time(23, 59);
    assertFalse(time.isBefore(time));
  }

  @Test
  public void ensureIsBeforeIsFalseWhenDateIsAfter() {
    Time time1 = new Time(10, 59);
    Time time2 = new Time(23, 59);
    assertFalse(time2.isBefore(time1));
  }

  @Test
  public void ensureIsAfterIsFalseWhenDatesAreEqual() {
    Time time = new Time(23, 59);
    assertFalse(time.isAfter(time));
  }

  @Test
  public void ensureIsEqualIsWorking() {
    Time time1 = new Time(0, 0);
    Time time2 = new Time(0, 0);
    assertTrue(time2.equals(time1));
  }

  @Test
  public void ensureEqualIsWorkingWithDifferentClasses() {
    Time time = new Time(0, 0);
    assertFalse(time.equals("00:00"));
  }

  @Test
  public void ensureIsEqualFalseIsWorking() {
    Time time1 = new Time(0, 0);
    Time time2 = new Time(10, 0);
    assertFalse(time2.equals(time1));
  }

  @Test
  public void ensureGetNowIsWorking() {
    Time time = Time.getNow();
    Time actualTime = new Time(Calendar.getInstance());
    Time backupTime = new Time(Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE));

    assertNotNull(time);
    assertEquals(time, actualTime);
    assertEquals(time, backupTime);
  }

  @Test
  public void ensureIsBetweenIsWorking() {
    Time time1 = new Time(0, 0);
    Time time2 = new Time(0, 30);
    Time time3 = new Time(0, 59);

    assertTrue(time2.isBetween(time1, time3));
  }

  @Test
  public void ensureIsBetweenIsWorkingWhenDateIsAfter() {
    Time time1 = new Time(0, 0);
    Time time2 = new Time(0, 30);
    Time time3 = new Time(0, 59);

    assertFalse(time3.isBetween(time1, time2));
  }

  @Test
  public void ensureIsBetweenIsWorkingWhenDateIsBefore() {
    Time time1 = new Time(0, 0);
    Time time2 = new Time(0, 30);
    Time time3 = new Time(0, 59);

    assertFalse(time1.isBetween(time2, time3));
  }

  @Test
  public void ensureConvertToMinutesIsWorking() {
    Time time = new Time(23, 59);
    assertEquals(time.convertToMinutes(), 23 * 60 + 59);
  }

  @Test
  public void ensureConvertToMinutesIsWorkingWithZero() {
    Time time = new Time(0, 0);
    assertEquals(time.convertToMinutes(), 0);
  }

  @Test
  public void ensureGetHoursIsWorking() {
    Time time = new Time(10, 30);
    assertEquals(time.getHours(), 10);
  }

  @Test
  public void ensureGetMinutesIsWorking() {
    Time time = new Time(10, 30);
    assertEquals(time.getMinutes(), 30);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureNegativeMinutesCannotCreateTime() {
    new Time(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ensureMoreThan24HoursCannotCreateTime() {
    new Time(24 * 60 + 1);
  }

  @Test
  public void ensureIsPossibleCreateTimeWithMinutes() {
    Time time = new Time(30);
    assertEquals(time, new Time(0, 30));
  }

  @Test
  public void ensureIsPossibleCreateTimeWithHours() {
    Time time = new Time(100);
    assertEquals(time, new Time(1, 40));
  }

  @Test
  public void ensureIsPossibleCreateTimeWithHoursWithZero() {
    Time time = new Time(0);
    assertEquals(time, new Time(0, 0));
  }

  @Test
  public void ensureIsPossibleCreateTimeWithHoursWithZeroMinutes() {
    Time time = new Time(120);
    assertEquals(time, new Time(2, 0));
  }
}
