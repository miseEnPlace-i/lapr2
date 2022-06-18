package app.domain.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import app.domain.shared.Constants;
import app.domain.shared.Gender;
import app.utils.Time;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class LegacyDataObjectBuilderTest {
    private SNSUser snsUser;
    private VaccineType vacType;
    private Vaccine vaccine;
    private Employee e;
    private VaccinationCenter vc;
    private Calendar cal;
    private LegacyData ld;
    private LegacyDataObjectBuilder ob;

    @Before
    public void setUp() {
        this.snsUser = new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        this.vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        this.vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        this.e = new Employee("1", "Name2", "+351916919269", "c@user.com", new Address("street", 1, "1-11", "city"), "15542404", Constants.ROLE_COORDINATOR);
        this.cal = Calendar.getInstance();
        this.vc = new CommunityMassVaccinationCenter("Centro Vacinação de Teste", new Address("street", 1, "11-11", "city"), "test@gmail.com", "+351212345678",
                "+351212345679", "http://www.test.com", new Time("08:00"), new Time("20:00"), new Slot(1, 1), this.e, this.vacType);
        this.ld = new LegacyData(snsUser, vaccine, 1, "1AC3F-25", cal, cal, cal, cal, vc);

        this.ob = new LegacyDataObjectBuilder(this.ld);
    }

    @Test
    public void ensureCreateAdministrationWorks() {
        VaccineAdministration expected = new VaccineAdministration(this.snsUser, this.vaccine, "1AC3F-25", 1, this.vc, this.cal);
        // assertEquals(expected, ob.createAdministration());
    }

    @Test
    public void ensureCreateAppointmentWorks() {
        Appointment expected = new Appointment(this.snsUser, this.cal, this.vc, this.vacType, false);
        // assertEquals(expected, ob.createAppointment());
    }

    @Test
    public void ensureCreateArrivalWorks() {
        Arrival expected = new Arrival(ob.createAppointment(), this.cal);
        // assertEquals(expected, ob.createArrival());
    }
}
