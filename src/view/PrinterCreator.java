package view;

/**
 * Class for creating printer.
 *
 * @author Novik Ihar
 */
public class PrinterCreator {
    public static TxtPrinter getTxtPrinter(String filePath) {
        return new TxtPrinter(filePath);
    }
}

