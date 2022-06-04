package app.controller;

import java.util.Calendar;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.session.EmployeeSession;

public class AnalyseCenterPerformanceController {
  private Company company;
  private EmployeeSession session;

  public AnalyseCenterPerformanceController(Company company, EmployeeSession session) {
    this.company = company;
    this.session = session;
  }

  public void analyseCenterPerformance(Calendar day, int interval) {
    VaccinationCenter center = session.getVaccinationCenter();
    CenterPerformance centerPerformance = center.getCenterPerformanceForDay(day, interval);
  }
}
