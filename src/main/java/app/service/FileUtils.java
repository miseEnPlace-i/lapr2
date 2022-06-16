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
    private static String[] BANNED_CHARS = {",", "\"", "@", ":", ";", "!", "&", "%", "[", "]", "/"};
    public static String OUTPUT_BASE_PATH = "export/";

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

            //create file path directory
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

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
     * 
     * @throws FileNotFoundException
     */
    public static List<String> readFromFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        if (!file.exists()) throw new FileNotFoundException("This file does not exist.");
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<String>();

        while (scanner.hasNextLine())
            lines.add(scanner.nextLine());

        scanner.close();

        return lines;

    }

    /**
     * 
     * @param fileName filename to sanitize
     * @return sanitized filename
     */
    public static String sanitizeFileName(String fileName) {
        String newFileName = fileName;
        newFileName = removeExtension(fileName) + ".csv";

        newFileName = removeSpecialChars(newFileName);
        buildDirStructure(OUTPUT_BASE_PATH);
        newFileName = OUTPUT_BASE_PATH + newFileName;

        return newFileName;
    }

    /**
     * 
     * @param fileName filename to check if has extension
     * @return true or false depending on whether the filename has extension or not
     */
    public static boolean hasNoExtension(String fileName) {
        return fileName.indexOf(".") == -1;
    }

    /**
     * 
     * @param fileName
     * @return
     */
    public static String removeExtension(String fileName) {
        if (hasNoExtension(fileName)) return fileName;

        return fileName.substring(0, fileName.indexOf("."));
    }

    /**
     * 
     * @param fileName filename to check if has special characters
     * @return filename without special characters
     */

    public static String removeSpecialChars(String fileName) {
        String newFileName = fileName;

        for (int i = 0; i < BANNED_CHARS.length; i++)
            newFileName = newFileName.replace(BANNED_CHARS[i], "");

        return newFileName;
    }

    /**
     * 
     * @param dirStructure directory structure
     */
    public static void buildDirStructure(String dirStructure) {

        File dirPath = new File(dirStructure);

        if (!dirPath.exists() || !dirPath.isDirectory()) {
            dirPath.mkdirs();
        }

    }
}
