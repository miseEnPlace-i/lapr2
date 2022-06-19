package app.service.readCSV;

import java.util.List;

/**
 * The common interface for all CSV readers.
 * 
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public interface ICSVReader {
  List<String[]> read(List<String> fileData);
}
