package model;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Class included methods for work with text(as CompositeElement).
 *
 * @author Ihar Novik
 */
public class TextManager {
    /*
        8. Слова текста, начинающиеся с гласных букв, рассортировать в алфавитном порядке
    по первой согласной букве слова.
    */

    /**
     * Method for sort list of all words in text that have first letter - vowel,
     * sorting by first consonant (alphabetically).
     *
     * @param text Text (CompositeElement with ElementType.Text) that will be sorted.
     */
    public static List<Element> sortByFirstConsonant(CompositeElement text) {
        //get all WORD elements in text
        List<Element> listOfWords = text.getElement(CompositeElement.CompositeType.WORD);

        Iterator<Element> iteratorOfWords = listOfWords.iterator();

        while (iteratorOfWords.hasNext()) {
            Element word = iteratorOfWords.next();
            String firstLetter = word.getListOfPart().get(0).toString();
            //del from listOfWords element that firstLetter vowel
            if (CompareByFirstConsonant.isConsonant(firstLetter)) {
                iteratorOfWords.remove();
            }
        }

        Collections.sort(listOfWords, CompareByFirstConsonant.getInstance());
        return listOfWords;
    }


   /*
    15. Преобразовать каждое слово в тексте, удалив из него все последующие
     (предыдущие) вхождения первой (последней) буквы этого слова.
      нужно для каждого слова проводить эту операцию
    */


    /**
     * Method change each word in text, del in this word all subsequent occurrences
     * of the first letter of the word.
     *
     * @param text Text (CompositeElement with ElementType.Text) that will be sorted.
     */
    public static void changeWordByFirstLetter(CompositeElement text) {
        //get all WORD elements in text
        List<Element> listOfWord = text.getElement(CompositeElement.CompositeType.WORD);
        Iterator<Element> iteratorOfWord = listOfWord.iterator();

     /*   Pass on the list and find + del if word has subsequent occurrences
         of the first letter of the word.*/
        while (iteratorOfWord.hasNext()) {
            Element word = iteratorOfWord.next();
            findAndDelByFirstLetter(word);
        }
    }

    /**
     * Method change each word in text, del in this word all subsequent occurrences
     * of the last letter of the word.
     *
     * @param text Text (CompositeElement with ElementType.Text) that will be sorted.
     */
    public static void changeWordByLastLetter(CompositeElement text) {
        List<Element> listOfWord = text.getElement(CompositeElement.CompositeType.WORD);
        Iterator<Element> iteratorOfWord = listOfWord.iterator();

         /*   Pass on the list and find + del if word has subsequent occurrences
         of the last letter of the word.*/
        while (iteratorOfWord.hasNext()) {
            Element word = iteratorOfWord.next();
            findAndDelByLastLetter(word);
        }
    }

    private static void findAndDelByLastLetter(Element word) {
        List<Element> listOfLetters = word.getListOfPart();
        ListIterator<Element> iteratorOfLetters = listOfLetters.listIterator(listOfLetters.size());

        List<Element> forDel = new LinkedList<>();
        String lastLetter = iteratorOfLetters.previous().toString().toUpperCase();

        // Add subsequent occurrences of the first letter to list forDel.
        while (iteratorOfLetters.hasPrevious()) {
            Element letter = iteratorOfLetters.previous();
            if (letter.toString().toUpperCase().equals(lastLetter)) {
                forDel.add(letter);
            }
        }
        // Del all marked letters(in list forDel).
        for (Element x : forDel) {
            word.removeElement(x);
        }

    }

    /**
     * Method finds and  del in  word all subsequent occurrences of the first
     * letter of the word.
     *
     * @param word
     */
    private static void findAndDelByFirstLetter(Element word) {
        List<Element> listOfLetters = word.getListOfPart();
        Iterator<Element> iteratorOfLetters = listOfLetters.iterator();

        List<Element> forDel = new LinkedList<>();

        String firstLetter = iteratorOfLetters.next().toString().toUpperCase();

        // Add subsequent occurrences of the first letter to list forDel.
        while (iteratorOfLetters.hasNext()) {
            Element letter = iteratorOfLetters.next();
            if (letter.toString().toUpperCase().equals(firstLetter)) {
                forDel.add(letter);
            }
        }
        // Del all marked letters(in list forDel).
        for (Element x : forDel) {
            word.removeElement(x);
        }
    }
}

/**
 * Comparator sorting by first consonant (alphabetically).
 */
class CompareByFirstConsonant implements Comparator<Element> {
    private static CompareByFirstConsonant instance;

    private CompareByFirstConsonant() {
    }

    /**
     * Lazy singleton initialization for comparator.
     *
     * @return CompareByFirstConsonant
     */
    public static CompareByFirstConsonant getInstance() {
        if (instance == null) {
            instance = new CompareByFirstConsonant();
        }
        return instance;
    }

    /**
     * Method for determination: whether letter consonant?
     *
     * @param str - the String of letter
     * @return true if str - is Consonant, else - false.
     */
    public static boolean isConsonant(String str) {
        return Pattern.matches(ProjectConstants.REGEX_CONSONANTS, str.toUpperCase());
    }

    /**
     * Method for finding first consonant in word.
     *
     * @param word - Element with ElementType.WORD
     * @return Character - first consonant in word,
     * if word hasn't consonant - Character.Max_VALUE.
     */
    private Character getFirstConsonant(Element word) {
        for (Element x : word.getListOfPart()) {
            String letter = x.toString();
            if (isConsonant(letter)) {
                return letter.toCharArray()[0];
            }
        }
        return Character.MAX_VALUE;
    }

    @Override
    public int compare(Element a, Element b) {
        Character consonantA = getFirstConsonant(a);
        Character consonantB = getFirstConsonant(b);
        return consonantA.compareTo(consonantB);
    }

}




