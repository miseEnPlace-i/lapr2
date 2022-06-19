package app.controller;

import java.util.Calendar;
import app.domain.model.CenterPerformance;
import app.domain.model.VaccinationCenter;
import app.session.EmployeeSession;

/**
 * Analyse Center Performance Controller
 * 
 * @author Tomás Lopes <1211289@isep.ipp.pt>
 */
public class AnalyseCenterPerformanceController {
  private EmployeeSession session;

  public AnalyseCenterPerformanceController(EmployeeSession session) {
    this.session = session;
  }

  /**
   * 
   * @param day the day of the month of the analysis
   * @param interval the interval, in minutes, of the analysis
   * @return the center performance in this day for this given interval
   */
  public CenterPerformance analyseCenterPerformance(Calendar day, int interval) {
    VaccinationCenter center = session.getVaccinationCenter();
    return center.getCenterPerformanceForDay(day, interval);
  }
}
