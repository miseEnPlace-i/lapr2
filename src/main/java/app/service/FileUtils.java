package app.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
     * @param dateIntervals String array with date intervals
     * @return generated filename according to specifications
     */
    public static String generateFileName(String[] dateIntervals) {
        String fileName = "";

        String pathName = convertDatesIntervalToPathName(dateIntervals[0], dateIntervals[dateIntervals.length - 1]);
        fileName = OUTPUT_BASE_PATH + pathName + "_data.csv";

        return fileName;
    }

    /**
     * 
     * @param beginningDateInterval first date interval
     * @param endDateInterval last date interval
     * @return String with the file's name corresponding to first and last intervals of dates' array
     */
    public static String convertDatesIntervalToPathName(String beginningDateInterval, String endDateInterval) {
        String firstDate = beginningDateInterval.substring(0, 10);
        int startIndexOfEndDate = endDateInterval.indexOf("/") + 1;
        String secondDate = endDateInterval.substring(startIndexOfEndDate);

        return firstDate + "_" + secondDate;
    }
}
