package app.service.readCSV;

import java.util.ArrayList;
import java.util.List;

public class MissingHeaderCSVReader implements ICSVReader {

    @Override
    public List<String[]> read(List<String> fileData) {
        List<String[]> result = new ArrayList<String[]>();
        for ( int i = 0 ; i < fileData.size(); i++) { 
            //splits the user data around the separator and adds the data to the result list
            result.add(fileData.get(i).split(",")); 
        }
        return result;
    }
    
}
