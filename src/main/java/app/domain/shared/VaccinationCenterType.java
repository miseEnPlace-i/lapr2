package app.domain.shared;


public enum VaccinationCenterType {
    COMMUNITY_MASS_VACCINATION_CENTER("Community Mass Vaccination Center"), HEALTH_CARE_CENTER(
            "Health Care Center");

    private String value;

    private VaccinationCenterType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
