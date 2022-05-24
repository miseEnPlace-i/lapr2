package app.controller;

import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;

/**
 * Register SNS User Arrival Controller.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController implements IRegisterController {
  private Company company;
  private SNSUserStore snsUserStore;
  private VaccinationCenter center;
  private SNSUser snsUser;

  /**
   * Constructor for RegisterSNSUserController.
   */
  public RegisterSNSUserArrivalController(Company company) {
    this.company = company;
    this.snsUserStore = company.getSNSUserStore();
    this.center = null;
    this.snsUser = null;
  }

  public void create() {
    // TODO
    // this.center.waitingRoomList.add()
  }

  @Override
  public void save() {
    // TODO
  }

  @Override
  public String stringifyData() {
    return null;
  }

  @Override
  public String getResourceName() {
    return "SNS User Arrival";
  }

  public void findSNSUser(String snsNumber) {
    SNSUser snsUser = this.snsUserStore.findSNSUserByNumber(snsNumber);

    if (snsUser == null) {
      throw new IllegalArgumentException("There is no SNS User with this SNS number.");
    }

    this.snsUser = snsUser;
  }

  public void findSNSUserAppointment(String snsNumber) {

    // Appointment apt = .findAppointment(snsNumber);

    // if (apt != null) {
    // throw new IllegalArgumentException("This SNS User does not have any appointment registered.");
    // }
  }


}
