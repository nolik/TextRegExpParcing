package view;

/**
 * This interface provides methods for print all information in project to all output ways(console,xml,file...).
 *
 * @author Ihar Novik
 */
public interface Printable {
    /**
     * Method for printing text to file.
     * @param text
     */
    void print(String text);
}
