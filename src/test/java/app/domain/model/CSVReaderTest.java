package app.domain.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.Test;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */

public class CSVReaderTest {
    String localPath;


    //Check is not possible to create CSVReader with null file path
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullPathNotAllowed() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String path = null;
        CSVReader csvReader = new CSVReader(path);
        csvReader.readSNSUserData();
    }

    //Check is not possible to create CSVReader with empty file path
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyPathNotAllowed() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String path = "";
        CSVReader csvReader = new CSVReader(path);
        csvReader.readSNSUserData();
    }

    //Check is not possible to read a file with CSVReader with wrong file path
    @Test(expected = FileNotFoundException.class)
    public void ensureWrongPathNotAllowed() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException{
        String path = "nonExistingFile";
        CSVReader csvReader = new CSVReader(path);
        csvReader.readSNSUserData();
    }

    //Check is possible to read a file with header and "," as separator (TYPE 1)
    @Test
    public void ensureReadFileWithHeaderIsWorking() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String path = "src/test/java/app/domain/model/SNSUserFilesToTests/HeaderUsersFile.csv";
        CSVReader csvReader = new CSVReader(path);
        List<String[]> list = csvReader.readSNSUserData();

        String[] expected = {"joao","M","14/12/2003","rua das flores","+351919999999","joao@gmail.com","123456789","155424041ZY0"};

        assertArrayEquals(list.get(0), expected);
    }

    //Check is possible to read a file without header and ";" as separator (TYPE 2)
    @Test
    public void ensureReadFileWithoutHeaderIsWorking() throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException {
        String path = "src/test/java/app/domain/model/SNSUserFilesToTests/MissingHeaderUsersFile.csv";
        CSVReader csvReader = new CSVReader(path);
        List<String[]> list = csvReader.readSNSUserData();

        String[] expected = {"joao","M","14/12/2003","rua das flores","+351919999999","joao@gmail.com","123456789","155424041ZY0"};

        assertArrayEquals(list.get(0), expected);
    }
   
    
}
