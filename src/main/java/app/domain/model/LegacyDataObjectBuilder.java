package app.domain.model;

import app.domain.model.list.AdministrationList;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.list.CenterEventList;
import app.domain.shared.CenterEventType;
import app.dto.VaccineTypeDTO;
import app.mapper.VaccineTypeMapper;

/**
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class LegacyDataObjectBuilder {
    private LegacyData legacyData;
    private Appointment appointment;

    public LegacyDataObjectBuilder(LegacyData legacyData) {
        this.legacyData = legacyData;
        this.appointment = null;
    }

    public Appointment createAppointment() {
        AppointmentScheduleList aptSchList = this.legacyData.getCenter().getAppointmentList();
        VaccineTypeDTO vacTypeDto = VaccineTypeMapper.toDto(legacyData.getVaccine().getVacType());
        this.appointment = aptSchList.createAppointment(legacyData.getSNSUser(), legacyData.getScheduledDate(), vacTypeDto, false);
        return this.appointment;
    }

    public Arrival createArrival() {
        if (this.appointment == null) throw new IllegalStateException("Appointment must be created before arrival");
        WaitingRoom waitingRoom = this.legacyData.getCenter().getWaitingRoom();
        return waitingRoom.createArrival(this.appointment, this.legacyData.getArrivalDate());
    }

    public Administration createAdministration() {
        AdministrationList administList = this.legacyData.getSNSUser().getAdministrationList();
        return administList.create(legacyData.getVaccine(), legacyData.getDoseNumber(), legacyData.getLotNumber(), legacyData.getCenter(),
                legacyData.getAdministrationDate());
    }

    public CenterEvent createArrivalEvent() {
        CenterEventList centerEvents = this.legacyData.getCenter().getEvents();
        return centerEvents.create(legacyData.getArrivalDate(), CenterEventType.ARRIVAL, legacyData.getSNSUser());
    }

    public CenterEvent createVaccinatedEvent() {
        CenterEventList centerEvents = this.legacyData.getCenter().getEvents();
        return centerEvents.create(legacyData.getAdministrationDate(), CenterEventType.VACCINATED, legacyData.getSNSUser());
    }

    public CenterEvent createDeparturedEvent() {
        CenterEventList centerEvents = this.legacyData.getCenter().getEvents();
        return centerEvents.create(legacyData.getDeparturedDate(), CenterEventType.DEPARTURE, legacyData.getSNSUser());
    }
}
