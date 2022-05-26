package app.controller;

import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;
import app.exception.AppointmentNotFoundException;

/**
 * Register SNS User Arrival Controller.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController implements IRegisterController {
  private Company company;
  private VaccinationCenter center;
  private SNSUser snsUser;
  private Appointment appointment;
  private Arrival arrival;
  private WaitingRoom waitingRoom;

  /**
   * Constructor for RegisterSNSUserController.
   */
  public RegisterSNSUserArrivalController(Company company, VaccinationCenter center) {
    this.company = company;
    this.center = center;
    this.snsUser = null;
  }

  public void create() {
    this.waitingRoom = center.getWaitingRoom();
    this.arrival = waitingRoom.createArrival(this.snsUser);
  }

  @Override
  public void save() {
    this.waitingRoom.saveArrival(this.arrival);

    // DEBUG: print the waiting room
    System.out.println(this.waitingRoom);
  }

  @Override
  public String stringifyData() {
    return this.appointment.toString();
  }

  @Override
  public String getResourceName() {
    return "SNS User Arrival";
  }

  public void findSNSUser(String snsNumber) {
    SNSUserStore snsUserStore = company.getSNSUserStore();
    SNSUser snsUser = snsUserStore.findSNSUserByNumber(snsNumber);

    if (snsUser == null) {
      throw new IllegalArgumentException("There is no SNS User with this SNS number.");
    }

    this.snsUser = snsUser;
  }

  public void findSNSUserAppointment() throws AppointmentNotFoundException {
    AppointmentScheduleList appointments = center.getAppointmentList();
    this.appointment = appointments.hasAppointmentToday(this.snsUser.getSnsNumber());
  }

}
