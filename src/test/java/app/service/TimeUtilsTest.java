package app.service;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class TimeUtilsTest {
  @Test
  public void ensureAgeIsBeingCalculatedCorrectly() {
    Date birthDay = new Date();
    birthDay = DateUtils.addYears(birthDay, -20);
    int age = TimeUtils.calculateAge(birthDay);
    assertEquals(20, age);
  }
}
