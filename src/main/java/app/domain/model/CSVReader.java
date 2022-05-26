package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import app.service.FileUtils;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class CSVReader {

    private String path;

    public CSVReader(String path){
        this.path = path;
    }

    public List<String[]> readSNSUserData(){
        List<String> fileData = FileUtils.readFromFile(this.path);

        //checks separator
        String separator = fileData.get(0).contains(";") ? ";" : ",";

        List<String[]> result = new ArrayList<String[]>();

        //if the separator is ";" then the file contains a header
        for ( int i = separator.equals(";") ? 0 : 1 ; i < fileData.size(); i++) { 
            //splits the user data around the separator and adds the data to the result list
            result.add(fileData.get(i).split(separator)); 
        }

        return result;

    }

}
