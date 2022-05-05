package app.domain.model;

/**
* @author Carlos Lopes <1211277@isep.ipp.pt>
*/
public class VaccineType {

    private String code;
    private String description;
    private String technology;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnology() {
        return this.technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }


    
}
