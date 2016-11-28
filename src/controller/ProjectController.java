package controller;

import model.*;
import org.apache.log4j.Logger;
import view.Printable;
import view.PrinterCreator;

import java.io.IOException;

/**
 * Class of the project where executed interaction MVC model and view of the project.
 * Include main method of the project.
 *
 * @author Novik Ihar
 */
public class ProjectController {
    static Logger logger = null;

    public static void main(String[] args) throws IOException {
        logger = Logger.getLogger(ProjectController.class);
        //we have one log4j.properties that configure automatically

        String path = "/home/nolik/IdeaProjects/forEpam/Task2/Text";
        TextParser textParser = new TextParser();
        CompositeElement allText = textParser.parse(TextReader.readTextFromFile(path));
        Printable printer = PrinterCreator.getTxtPrinter("/home/nolik/IdeaProjects/forEpam/Task2/outputTask2");

        printer.print(allText.toString());
        logger.info(allText);

        TextManager.changeWordByFirstLetter(allText);
        //       TextManager.changeWordByLastLetter(allText);
        logger.info(ProjectConstants.CHANGE_BY_FIRST_LETTER + allText);
        printer.print("\n" + allText.toString());

        String sortedWords = TextManager.sortByFirstConsonant(allText).toString();
        printer.print(sortedWords);
        logger.info("\n"+ProjectConstants.SORT_BY_FIRST_CONSONANT + sortedWords);

    }


}
