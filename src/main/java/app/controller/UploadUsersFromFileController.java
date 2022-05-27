package app.controller;

import java.text.ParseException;
import java.util.List;
import app.domain.model.CSVReader;
import app.domain.model.Company;
import app.domain.model.dto.SNSUserRegisterInfoDTO;
import app.domain.model.store.SNSUserStore;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class UploadUsersFromFileController {

    private Company company;
    private CSVReader csvReader;

    public UploadUsersFromFileController(Company company) {
        this.company = company;
        this.csvReader = null;
    }

    public CSVReader createCsvReader(String filePathName){
        return new CSVReader(filePathName);
    }

    public List<SNSUserRegisterInfoDTO> readAndUpload() throws ParseException{

        SNSUserStore store = this.company.getSNSUserStore();

        List<String[]> userDataList = csvReader.readSNSUserData();

        List<SNSUserRegisterInfoDTO> userRegisterInfoList = store.registerListOfUsers(userDataList);

        return userRegisterInfoList;
    }
    
}
