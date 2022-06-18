package app.domain.model;

import static org.junit.Assert.assertNotNull;
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
public class LegacyDataTest {
    private LegacyData ld;

    @Before
    public void setUp() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Employee e2 =
                new Employee("1", "Name2", "+351916919269", "c@user.com", new Address("street", 1, "1-11", "city"), "15542404", Constants.ROLE_COORDINATOR);
        Calendar now = Calendar.getInstance();
        VaccinationCenter vc = new CommunityMassVaccinationCenter("Centro Vacinação de Teste", new Address("street", 1, "11-11", "city"), "test@gmail.com",
                "+351212345678", "+351212345679", "http://www.test.com", new Time("08:00"), new Time("20:00"), new Slot(1, 1), e2, vacType);
        this.ld = new LegacyData(snsUser, vaccine, 1, "lotNum", now, now, now, now, vc);
    }

    // Check that it is not possible to create an instance with null arguments
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullArgumentsNotValid() {
        new LegacyData(null, null, 0, null, null, null, null, null, null);
    }

    // Check that the constructor is working correctly
    @Test
    public void ensureIsPossibleToCreateInstance() {
        assertNotNull(this.ld);
    }

    // Check that the getters are working correctly
    @Test
    public void ensureGettersAreWorkingCorrectly() {
        assertNotNull(this.ld.getSNSUser());
        assertNotNull(this.ld.getVaccine());
        assertNotNull(this.ld.getDoseNumber());
        assertNotNull(this.ld.getLotNumber());
        assertNotNull(this.ld.getArrivalDate());
        assertNotNull(this.ld.getScheduledDate());
        assertNotNull(this.ld.getAdministrationDate());
        assertNotNull(this.ld.getDeparturedDate());
        assertNotNull(this.ld.getCenter());
    }

    // check that validate is working properly
    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly1() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        new LegacyData(snsUser, null, 0, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly2() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        new LegacyData(snsUser, vaccine, -1, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly3() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        new LegacyData(snsUser, vaccine, 0, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly4() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        new LegacyData(snsUser, vaccine, -1, "1", null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly5() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Calendar now = Calendar.getInstance();
        new LegacyData(snsUser, vaccine, -1, "1", now, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly6() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Calendar now = Calendar.getInstance();
        new LegacyData(snsUser, vaccine, -1, "1", now, now, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly7() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Calendar now = Calendar.getInstance();
        new LegacyData(snsUser, vaccine, -1, "1", now, now, now, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly8() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Calendar now = Calendar.getInstance();
        new LegacyData(snsUser, vaccine, -1, "1", now, now, now, now, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureValidateIsWorkingProperly9() {
        SNSUser snsUser =
                new SNSUser("12345678", "123123123", "a", new Date(), Gender.N_A, "987654321", "fa@gds.c", new Address("street", 1, "1345-124", "city"));
        VaccineType vacType = new VaccineType("00000", "COVID-19", "M_RNA_TECHNOLOGY");
        Vaccine vaccine = new Vaccine("Monkeypox vaccine", "00002", "PTVaccines", vacType);
        Employee e2 =
                new Employee("1", "Name2", "+351916919269", "c@user.com", new Address("street", 1, "1-11", "city"), "15542404", Constants.ROLE_COORDINATOR);
        VaccinationCenter vc = new CommunityMassVaccinationCenter("Centro Vacinação de Teste", new Address("street", 1, "11-11", "city"), "test@gmail.com",
                "+351212345678", "+351212345679", "http://www.test.com", new Time("08:00"), new Time("20:00"), new Slot(1, 1), e2, vacType);
        Calendar now = Calendar.getInstance();
        new LegacyData(snsUser, vaccine, -1, "1", now, now, now, now, vc);
    }
}
