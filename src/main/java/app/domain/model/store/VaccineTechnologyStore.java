package app.domain.model.store;

import java.util.ArrayList;
import java.util.List;

public class VaccineTechnologyStore {
    private List<String> types;


    public VaccineTechnologyStore() {
        this.types = new ArrayList<String>();
    }

    public void addVaccineTechnology(String technology) {
        types.add(technology);
    }

    public List<String> getList() {
        return types;
    }

    public boolean existsType(String technology) {
        return types.contains(technology);
    }

}
