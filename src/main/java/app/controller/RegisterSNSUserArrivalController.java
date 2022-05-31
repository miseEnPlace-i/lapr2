package app.controller;

import app.domain.model.Appointment;
import app.domain.model.Arrival;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.store.SNSUserStore;
import app.domain.shared.CenterEventType;
import app.dto.AppointmentListDTO;
import app.dto.ArrivalDTO;
import app.exception.AppointmentNotFoundException;
import app.mapper.AppointmentListMapper;
import app.mapper.ArrivalMapper;

/**
 * Register SNS User Arrival Controller.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class RegisterSNSUserArrivalController implements IRegisterController<ArrivalDTO> {
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
  }

  public void create() {
    this.waitingRoom = center.getWaitingRoom();
    this.arrival = waitingRoom.createArrival(this.appointment);
  }

  @Override
  public void save() {
    this.waitingRoom.saveArrival(this.arrival);
    this.center.getEvents().create(this.arrival.getArrivalTime(), CenterEventType.ARRIVAL, this.snsUser);
    
    // DEBUG: print the waiting room
    // System.out.println(this.waitingRoom);
  }

  @Override
  public String stringifyData() {
    // convert to dto and return a string of it
    AppointmentListDTO dto = AppointmentListMapper.toDto(this.appointment);

    return dto.toString();
  }

  @Override
  public String getResourceName() {
    return "SNS User Arrival";
  }

  public void findSNSUser(String snsNumber) {
    SNSUserStore snsUserStore = company.getSNSUserStore();
    SNSUser snsUser = snsUserStore.findSNSUserByNumber(snsNumber);

    if (snsUser == null) throw new IllegalArgumentException("There is no SNS User with this SNS number.");

    this.snsUser = snsUser;
  }

  public void findSNSUserAppointment() throws AppointmentNotFoundException {
    // acceptance criteria: validate if there is already an arrival for the user
    WaitingRoom waitingRoomList = center.getWaitingRoom();
    boolean hasArrived = waitingRoomList.hasSNSUserArrivedToday(this.snsUser);

    if (hasArrived) throw new AppointmentNotFoundException("The SNS User has already arrived today.");

    // search for appointment
    AppointmentScheduleList appointments = center.getAppointmentList();
    this.appointment = appointments.hasAppointmentToday(this.snsUser.getSnsNumber());
  }

  @Override
  public ArrivalDTO getRegisteredObject() {
    return ArrivalMapper.toDto(arrival);
  }
}
