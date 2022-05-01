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

    
}
