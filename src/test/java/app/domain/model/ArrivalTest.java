package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import app.domain.model.store.EmployeeStore;
import app.domain.model.store.SNSUserStore;
import app.domain.model.store.VaccinationCenterStore;
import app.domain.model.store.VaccineTechnologyStore;
import app.domain.model.store.VaccineTypeStore;
import app.domain.shared.Constants;

public class ArrivalTest {
    Company company;
    SNSUserStore snsUserStore;
    EmployeeStore empStore;
    VaccinationCenterStore vcStore;
    VaccineTechnologyStore vtechStore;
    VaccineTypeStore vtStore;
    VaccineType vacType;
    VaccinationCenter center;

    @Before
    public void setUp() {
        this.company = new Company("designation", "12345");
        this.snsUserStore = this.company.getSNSUserStore();
        this.vcStore = company.getVaccinationCenterStore();
        this.snsUserStore = company.getSNSUserStore();
        this.empStore = company.getEmployeeStore();
        this.vtStore = company.getVaccineTypeStore();
        this.vtechStore = company.getVaccineTechnologyStore();

        SNSUser snsUser = snsUserStore.createSNSUser("000000000ZZ4", "123456789", "name", Calendar.getInstance().getTime(), 'M', "+351212345678", "s@user.com", "address");
        this.snsUserStore.saveSNSUser(snsUser);

        Employee e2 = empStore.createEmployee("Name2", "+351916919269", "c@user.com", "address", "155424041ZY0", Constants.ROLE_COORDINATOR);
        this.empStore.saveEmployee(e2);

        this.vtechStore.addVaccineTechnology("M_RNA_TECHNOLOGY");

        this.vacType = vtStore.addVaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        this.vtStore.saveVaccineType(vacType);

        this.center = vcStore.createCommunityMassCenter("Centro Vacinação de Teste", "Rua de Teste", "test@gmail.com", "+351212345678", "+351212345679",
                "http://www.test.com", "20:00", "21:00", 7, 5, e2, vacType);
        this.vcStore.saveVaccinationCenter(this.center);
    }
    
    @Test
    public void ensureToStringWorksAsExpected() {
        SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 30);

        Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
        Arrival arrival = new Arrival(appointment);

        Calendar now = Calendar.getInstance();

        String result = arrival.toString();
        String expected = String.format("Arrival: 123456789 @ %s", now.getTime());
        
        assertEquals(expected, result);
    }

    @Test
    public void ensureHasVaccineTypeWorks() {
        SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 30);

        Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
        
        assertEquals(true, appointment.hasVaccineType(this.vacType));
        assertEquals(false, appointment.hasVaccineType(null));
    }

    @Test
    public void ensureGetAppointmentWorksAsExpected() {
        SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 30);

        Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
        Arrival arrival = new Arrival(appointment);

        assertEquals(arrival.getAppointment(), appointment);
    }
    
    @Test
    public void ensureGetArrivalTimeWorksAsExpected() {
        SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 30);

        Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
        Arrival arrival = new Arrival(appointment);

        Calendar now = Calendar.getInstance();
        
        arrival.setDepartureTime(now);

        Calendar departureTime = arrival.getDepartureTime();

        assertEquals(departureTime, now);
    }

    @Test
    public void ensureGetArrivalTimeWorksWithoutErrors() {
        SNSUser snsUser = this.snsUserStore.findSNSUserByNumber("123456789");
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 20);
        date.set(Calendar.MINUTE, 30);

        Appointment appointment = new Appointment(snsUser, date, center, this.vacType, true);
        Arrival arrival = new Arrival(appointment);

        arrival.getArrivalTime();
    }
    
}
