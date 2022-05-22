package app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * File Utils.
 */
public class FileUtils {
    /**
     * Write to a file.
     * 
     * @param fileName the file name.
     * @param content the content to write.
     * @throws IOException if the file could not be written.
     * @return true if the file was written successfully, false otherwise.
     */
    public static boolean writeToFile(String filename, String content) {
        try {
            File file = new File(filename);
            file.createNewFile(); // creates a file if it does not exist
            PrintWriter writer = new PrintWriter(file);
            writer.write(content);
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Read from a file.
     */
    public static List<String> readFromFile(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) throw new FileNotFoundException("This file does not exist.");
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<String>();

            while (scanner.hasNextLine())
                lines.add(scanner.nextLine());

            scanner.close();

            return lines;
        } catch (IOException e) {
            System.err.println(e);
            e.printStackTrace();
        }
        return null;
    }
}
