package app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * FileUtils tests
 * 
 * @author André Barros <1211299@isep.ipp.pt>
 */
public class FileUtilsTest {
    List<String> content = new ArrayList<>();

    @Before
    public void setUp() {
        content.add("content");
        FileUtils.writeToFile("out/test/test", content.get(0));
    }

    /**
     * Tests that it is possible to remove special chars from the file name
     */
    @Test
    public void ensureRemoveSpecialCharsWorks() {
        String fileName = "test@e";

        String actual = FileUtils.removeSpecialChars(fileName);

        String expected = "teste";

        assertEquals(expected, actual);
    }

    /**
     * Tests that it is possible to remove extension from the file name
     */
    @Test
    public void ensureRemoveExtensionWorks() {
        String fileName = "test.txt";

        String actual = FileUtils.removeExtension(fileName);

        String expected = "test";

        assertEquals(expected, actual);
    }

    /**
     * Tests that hasNoExtension method works properly
     */
    @Test
    public void ensureHasNoExtensionWorks() {
        String fileName = "test";

        boolean actual = FileUtils.hasNoExtension(fileName);

        boolean expected = true;

        assertEquals(expected, actual);
    }

    /**
     * Test that sanitizeFileName works properly. When the fileName contains banned chars or has a extension different from
     * ".csv", returns a fileName sanitized
     */
    @Test
    public void ensureSanitizeFileName() {
        String fileName = "tes@t.txt";

        String actual = FileUtils.sanitizeFileName(fileName);

        String expected = "out/test/test.csv";

        assertEquals(expected, actual);
    }

    /**
     * Tests that it is possible to build a directory if it is not already created
     */
    @Test
    public void ensureBuildDirStructure() {
        String dirStructure = "export";

        FileUtils.buildDirStructure(dirStructure);
    }

    /**
     * Tests that it it possible to write data to a file
     */
    @Test
    public void ensureWriteToFileWorks() {
        String fileName = "out/test/test";
        String content = "content";

        boolean actual = FileUtils.writeToFile(fileName, content);

        assertEquals(true, actual);
    }

    /**
     * Tests that it it possible to read data from a file
     * 
     * @throws FileNotFoundException
     */
    @Test
    public void ensureReadFromFileWorks() throws FileNotFoundException {
        String fileName = "out/test/test";

        List<String> actual = FileUtils.readFromFile(fileName);

        assertEquals(this.content, actual);
    }
}
