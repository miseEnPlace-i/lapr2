package app.domain.model;

import org.junit.Test;
import app.domain.shared.Constants;

/**
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class VaccinationCenterTest {

        /**
         * Check that it is not possible to create a Vaccination Center with null values.
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureNullIsNotAllowed() {
                new VaccinationCenter(null, null, null, null, null, null, null, null, 0, 0, null);

        }

        /**
         * Check that it is not possible to create a Vaccination Center with empty values.
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureEmptyIsNotAllowed() {

                Employee coordinator = new Employee("", "", "", "", "", "");

                new VaccinationCenter("", "", "", "", "", "", "", "", 0, 0, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the email address invalid
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureEmailIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoportoAgmail.com", "+351912345678", "+351-123-1234567", "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the phone number invalid
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensurePhoneNumberIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro", "Rua João Almeida", "vacinacaoporto@gmail.com", "91919191", "+351-123-1234567", "www.centrovacinaoporto.com", "8:00", "19:00", 5, 10, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the fax number invalid
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureFaxNumberIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-12345", "www.centrovacinaoporto.com", "8:00", "19:00", 0, 10, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with the website address invalid
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureWebsiteAddressIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567", "www.centrovacinaoporto.com", "8:00", "19:00", 0, 10, coordinator);
        }

        /**
         * Check that it is not possible to create a Vaccination Center with invalid opening hours
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureOpenHoursIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567", "www.centrovacinaoporto.com", "25:123", "19:00", 5, 10, coordinator);

        }

        /**
         * Check that it is not possible to create a Vaccination Center with invalid closing hours
         * 
         * @throws Exception IllegalArgumentException
         */
        @Test(expected = IllegalArgumentException.class)
        public void ensureClosHoursIsCorrect() {
                Employee coordinator = new Employee("Joana", "+351916478865", "Rua almeida", "joanamaria@gmail.com", "30365258 4 ZZ0", Constants.ROLE_COORDINATOR);

                new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567", "www.centrovacinaoporto.com", "20:00", "30:60", 5, 10, coordinator);

        }

        /**
         * Check that it is possible to create a Vaccination Center with all valid parameters.
         */
        @Test
        public void ensureItsPossibleToCreateVaccinationCenter() {


                Employee coordinator = new Employee("Joana", "+351916478865", "joanamaria@gmail.com", "Rua almeida", "123456789ZZ1", Constants.ROLE_COORDINATOR);
                VaccinationCenter center =
                                new VaccinationCenter("Centro Vacinação Porto", "Rua João Almeida", "vacinacaoporto@gmail.com", "+351912345678", "+351-123-1234567", "www.centrovacinaoporto.com", "20:00", "19:00", 5, 10, coordinator);
                assert (center != null);
        }
}
