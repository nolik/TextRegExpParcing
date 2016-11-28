package model;


import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for read text from File
 *
 * @author Ihar Novik
 */
public class TextReader {
    static Logger logger = Logger.getLogger(TextReader.class);
    /**
     * Method for reading from file.
     *
     * @param filePath Path to input file.
     * @return String includes text from file.
     */
    public static String readTextFromFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while (bufferedReader.ready()) {
                text.append(bufferedReader.readLine());
                text.append("\n");
            }
        } catch (FileNotFoundException e) {
            logger.error(ProjectConstants.FILE_NOT_FOUND);
            e.printStackTrace();
        }
        return text.toString();
    }

}
