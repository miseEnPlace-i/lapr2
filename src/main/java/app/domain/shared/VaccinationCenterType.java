package app.domain.shared;


public enum VaccinationCenterType {
    MASS_COMMUNITY_VACCINATION_CENTER("Mass Community Vaccination Center"), HEALTH_CARE_CENTER(
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
