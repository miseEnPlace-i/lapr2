package app.domain.model;

import org.junit.Test;

public class VaccinationCenterTest {

    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
                "vacinacaoporto@gmail.com", 221010101, 1221231231, "www.centrovacinaoporto.com", "8:00", "19:00", 5,
                10);
        assert (center != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureIntegerParameterIsNotNull() {
        VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida",
                "vacinacaoporto@gmail.com", 221010101, 122123123, "www.centrovacinaoporto.com", "8:00", "19:00", 0, 10);
    }

}
