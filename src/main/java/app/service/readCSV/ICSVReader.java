package app.service.readCSV;

import java.util.List;

/**
 * Vaccination Center mapper
 * 
 * @autor Carlos Lopes <1211277@isep.ipp.pt>
 */
public interface ICSVReader {
    
    public List<String[]> read(List<String> fileData);

}
