package app.domain.model;

import org.junit.Test;

public class VaccineTest {
    @Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine instance = new Vaccine(null, null, null, null, null);
	}

    @Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine instance = new Vaccine("", "", "", "", "");
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine instance = new Vaccine("Pfizer–BioNTech COVID-19", "Pfizer", "vac1", "Not existing vacTypeID");
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine.addAdminProc(null, null, null);
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine.addAdminProc(-10, -5, 3);
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine.addAdminProc("minAge", "maxAge", 0.13);
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vaccine.addAdminProc("18", "2", "3");
	}
  
	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		AdminProc.addDoseInfo(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		AdminProc.addDoseInfo("dosage", 0.10);
	}

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		AdminProc.addDoseInfo(-10, -5);
	}


    
}
