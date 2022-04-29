package app.domain.model;

import org.junit.Assert;
import org.junit.Test;
import app.domain.shared.Constants;

public class VaccinationCenterTest {

        @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
                Employee coordinator = new Employee("name", 123123, "email", "address", 123123,
                                Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("cen1tro", "Rua João Almeida",
                                "vacinacaoporto@gmail.com", 221010101, 1221231231,
                                "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

        @Test(expected = IllegalArgumentException.class)
        public void ensureIntegerParameterIsNotNull() {
                Employee coordinator = new Employee("name", 123123, "email", "address", 123123,
                                Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                                "Rua João Almeida", "vacinacaoporto@gmail.com", 221010101,
                                122123123, "www.centrovacinaoporto.com", "8:00", "19:00", 0, 10,
                                coordinator);
        }
}
