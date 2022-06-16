package app.domain.model;

import java.util.Calendar;
import java.util.Timer;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTypeStore;
import app.utils.Time;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class Scheduler {

  private final static int SECOND_INTERVAL = 60*60*24;

  public static void scheduleExportDailyVaccinated(String path, String time, String separator, VaccinationCenterStore vacCenterStore, VaccineTypeStore vacTStore){
        

      ExportDailyVaccinatedTask task = new ExportDailyVaccinatedTask(path, separator.charAt(0), vacCenterStore, vacTStore);
      Timer timer = new Timer();
  
      Time scheduleTime = new Time(time);
  
      Calendar firstTime = Calendar.getInstance();

      firstTime.set(Calendar.SECOND, 0);
      firstTime.set(Calendar.MINUTE, scheduleTime.getMinutes());
      firstTime.set(Calendar.HOUR_OF_DAY, scheduleTime.getHours());

      if(firstTime.before(Calendar.getInstance())) firstTime.add(Calendar.SECOND, SECOND_INTERVAL);
  
      timer.scheduleAtFixedRate(task, firstTime.getTime(), SECOND_INTERVAL);

    }
    
}
