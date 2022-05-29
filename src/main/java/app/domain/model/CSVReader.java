package app.domain.model;

import java.io.FileNotFoundException;
import java.util.List;
import app.service.FileUtils;
import app.service.readCSV.ICSVReader;
/**
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public class CSVReader {

    private String path;

    public CSVReader(String path){
        if(path == null || path == ""){
            throw new IllegalArgumentException("File path cannot be null or empty!");
        }
        this.path = path;
    }

    public List<String[]> readSNSUserData() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException{
        List<String> fileData = FileUtils.readFromFile(this.path);

        
        //checks separator
        String className = fileData.get(0).contains(",") ? "app.service.readCSV.MissingHeaderCSVReader" : "app.service.readCSV.HeaderCSVReader";

        Class<?> oClass = Class.forName(className);

        ICSVReader reader = (ICSVReader) oClass.newInstance();

        List<String[]> userDataList = reader.read(fileData);


        return userDataList;

    }

}
