package app.domain.model;

import org.junit.Test;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */

public class VaccinationCenterTest {

        /**
         * Check that it is not possible to create a Vaccination Center with null
         * values.
         * 
         * @throws Exception
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
                VaccinationCenter center = new VaccinationCenter(null, null, null, null, null, null, null,
                                null,
                                0,
                                0, null);

        }

        /**
         * Check that it is not possible to create a Vaccination Center with empty
         * values.
         * 
         * @throws Exception
         */

        @Test(expected = IllegalArgumentException.class)
        public void ensureEmptyIsNotAllowed() {

                Employee coordinator = new Employee("", "", "", "", "", "");

                VaccinationCenter center = new VaccinationCenter("", "", "", "", "", "", "", "", 0, 0, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the phone
         * number invalid
         * 
         * @throws Exception
         */

        @Test(expected = IllegalArgumentException.class)
        public void ensurePhoneNumberIsCorrect() {
                Employee coordinator = new Employee("name", "123123", "email", "address", "123123",
                                Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro", "Rua João Almeida",
                                "vacinacaoporto@gmail.com", "91919191", "+351123456788",
                                "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the fax
         * number invalid
         * 
         * @throws Exception
         */

        @Test(expected = IllegalArgumentException.class)
        public void ensureFaxNumberIsCorrect() {
                Employee coordinator = new Employee("name", "123123", "email", "address", "123123",
                                Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                                "Rua João Almeida", "vacinacaoporto@gmail.com", "+351221010101",
                                "+35111221231", "www.centrovacinaoporto.com", "8:00", "19:00", 0, 10,
                                coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the website
         * address invalid
         * 
         * @throws Exception
         */

        @Test(expected = IllegalArgumentException.class)
        public void ensureWebsiteAddressIsCorrect() {
                Employee coordinator = new Employee("name", "123123", "email", "address", "123123",
                                Constants.ROLE_COORDINATOR);

                VaccinationCenter center = new VaccinationCenter("Centro Vacinação Porto",
                                "Rua João Almeida", "vacinacaoporto@gmail.com", "221010101",
                                "1122123123", "ww.centrovacinaoporto.com", "8:00", "19:00", 0, 10,
                                coordinator);
        }
}