package app.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.CSVReader;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.domain.model.store.SNSUserStore;
import app.dto.LegacyDataDTO;
import app.exception.NotFoundException;
import app.mapper.LegacyDataMapper;

public class ImportLegacyDataController {
    private Company company;
    private VaccinationCenter center;
    private SNSUserStore snsUserStore;

    public ImportLegacyDataController(Company company, VaccinationCenter center) {
        this.company = company;
        this.center = center;
        this.snsUserStore = company.getSNSUserStore();
    }

    public List<String[]> read(String filepath) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException,
            NoSuchMethodException, InvocationTargetException {
        CSVReader csvReader = new CSVReader(filepath);
        return csvReader.read();
    }

    public List<LegacyDataDTO> convert(List<String[]> fileData) throws ParseException {
        List<LegacyDataDTO> legacyDtoList = new ArrayList<LegacyDataDTO>();

        for (String[] d : fileData) {
            LegacyDataDTO dto = LegacyDataMapper.toDto(d);
            legacyDtoList.add(dto);
        }

        return legacyDtoList;
    }

    public void validate(List<LegacyDataDTO> legacyDtoList) throws NotFoundException {
        for (LegacyDataDTO d : legacyDtoList) {
            String snsNumber = d.getSnsNumber();
            if (this.snsUserStore.findSNSUserByNumber(snsNumber) == null) throw new NotFoundException("SNS User w/ number " + snsNumber);
        }
    }

    public void sort() {}

    public void save() {}
}
