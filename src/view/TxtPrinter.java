package view;

import model.ProjectConstants;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for printing result to txt file.
 *
 * @author Novik Ihar
 */
public class TxtPrinter implements Printable {
    private String outPutFilePath;
    static Logger logger = Logger.getLogger(TxtPrinter.class);

    public TxtPrinter(String outPutFile) {
        outPutFilePath = outPutFile;
    }

    @Override
    public void print(String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outPutFilePath), true))) {
            bufferedWriter.write(text);
        } catch (IOException e) {
            logger.error(ProjectConstants.WRITE_ERROR);
            e.printStackTrace();
        }

    }


}


