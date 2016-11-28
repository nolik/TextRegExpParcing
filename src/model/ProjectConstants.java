package model;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class includes all project constant.
 *
 * @author Ihar Novik
 */
public class ProjectConstants {
    private static Locale locale = Locale.getDefault();
    private static ResourceBundle bundle = ResourceBundle.getBundle("text", locale);

    public static final String REGEX_PARAGRAPH = bundle.getString("REGEX_PARAGRAPH");
    public static final String REGEX_SENTENCE = bundle.getString("REGEX_SENTENCE");
    public static final String REGEX_WORD_WITH_ALL = bundle.getString("REGEX_WORD_WITH_ALL"); //"([-a-zA-Zа-яА-Я0-9_]+)|(([ \\.,!\\?:;@]{1}))";
    public static final String REGEX_WORD = bundle.getString("REGEX_WORD");
    public static final String REGEX_LETTER = bundle.getString("REGEX_LETTER");
    public static final String REGEX_PUNCTUATION = bundle.getString("REGEX_PUNCTUATION"); //+space
    public static final String REGEX_URL = bundle.getString("REGEX_URL");
    public static final String REGEX_LISTING = bundle.getString("REGEX_LISTING");
    public static final String REGEX_PARAGRAPH_AND_LISTING = bundle.getString("REGEX_PARAGRAPH_AND_LISTING"); //in last (\\s|^) - for matching without tabulation
    public final static String REGEX_CONSONANTS = bundle.getString("REGEX_CONSONANTS");
    public final static String FILE_NOT_FOUND = bundle.getString("FILE_NOT_FOUND");
    public final static String CHANGE_BY_FIRST_LETTER = bundle.getString("CHANGE_BY_FIRST_LETTER");
    public final static String SORT_BY_FIRST_CONSONANT = bundle.getString("SORT_BY_FIRST_CONSONANT");
    public final static String WRITE_ERROR = bundle.getString("WRITE_ERROR");


}
