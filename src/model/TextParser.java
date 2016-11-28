package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class included methods for parsing text to subelements.
 *
 * @author Ihar Novik
 */

public class TextParser {

    // парсим сначало текст по параграфам и листингам, а потом точечно листинг, иначе параграф
    //нашел один, но нужно проверить: (\s*(Start listing)([^\t]+)(End listing))|(\s.+)


    //RegExp для поиска ссылок:
    // url pattern : /^(https?:\/\/)?([\w\.]+)\.([a-z]{2,6}\.?)(\/[\w\.]*)*\/?$/

    /**
     * Method for parsing text to subelements(such as paragraph and listing).
     *
     * @param allText all text
     * @return CompositeElement with CompositeType.TEXT (all text in one composite element)
     */
    public CompositeElement parse(String allText) {
        CompositeElement textElement = new CompositeElement(CompositeElement.CompositeType.TEXT);
        parseToParagraphAndListing(textElement, allText);
        return textElement;
    }

    /**
     * Method for parsing text to CompositeElement with type of paragraph nad listing.
     *
     * @param textElement  CompositeElement with type of text
     * @param allText String of all text.
     */
    private void parseToParagraphAndListing(CompositeElement textElement, String allText) {
        Pattern patternParagraphAndListing = Pattern.compile(ProjectConstants.REGEX_PARAGRAPH_AND_LISTING);
        Matcher matcherParagraphAndListing = patternParagraphAndListing.matcher(allText);

        while (matcherParagraphAndListing.find()) {
            String paragraphOrListing = matcherParagraphAndListing.group();

            if (Pattern.matches(ProjectConstants.REGEX_LISTING, paragraphOrListing)) {
                LeafElement listing = new LeafElement(LeafElement.LeafType.LISTING, paragraphOrListing);
                textElement.addElement(listing);
            } else {
                CompositeElement paragraph = new CompositeElement(CompositeElement.CompositeType.PARAGRAPH);
                textElement.addElement(paragraph);
                parseToSentence(paragraph, paragraphOrListing);
            }
        }
    }

    /**
     * Method for parsing from paragraph to sentence.
     *
     * @param paragraph  CompositeElement with type of paragraph.
     * @param strOfParagraph String of paragraph.
     */
    private void parseToSentence(CompositeElement paragraph, String strOfParagraph) {


        Pattern patternSentence = Pattern.compile(ProjectConstants.REGEX_SENTENCE);
        Matcher matcher = patternSentence.matcher(strOfParagraph);

        while (matcher.find()) {
            String strOfSentence = matcher.group();
            CompositeElement sentence = new CompositeElement(CompositeElement.CompositeType.SENTENCE);
            paragraph.addElement(sentence);
            parseToWordAndPunctuation(sentence, strOfSentence);
        }
    }

    /**
     * Method for parsing from sentence to word and punctuation(with space).
     *
     * @param sentence CompositeElement with type of sentence.
     * @param strOfSentence String of sentence.
     */
    private void parseToWordAndPunctuation(CompositeElement sentence, String strOfSentence) {
        Pattern patternWordAndAll = Pattern.compile(ProjectConstants.REGEX_WORD_WITH_ALL);
        Matcher matcherWordAndAll = patternWordAndAll.matcher(strOfSentence);

        while (matcherWordAndAll.find()) {
            String str = matcherWordAndAll.group();

            if (Pattern.matches(ProjectConstants.REGEX_WORD, str)) {

                CompositeElement word = new CompositeElement(CompositeElement.CompositeType.WORD);
                sentence.addElement(word);
                parseToLetter(word, str);
            } else {

                LeafElement sign = new LeafElement(LeafElement.LeafType.SIGN, str);
                sentence.addElement(sign);
            }
        }

    }

    /**
     * Method for parsing from word to letters.
     *
     * @param word CompositeElement with type of word.
     * @param strOfWord String of word.
     */
    private void parseToLetter(CompositeElement word, String strOfWord) {
        //Last method to parse words to letters
        Pattern patternLetter = Pattern.compile(ProjectConstants.REGEX_LETTER);
        Matcher matcherLetter = patternLetter.matcher(strOfWord);

        while (matcherLetter.find()) {
            String strOfLetter = matcherLetter.group();
            LeafElement letter = new LeafElement(LeafElement.LeafType.LETTER, strOfLetter);
            word.addElement(letter);
        }
    }


}



/*
 //parsing URL from paragraph
 {
        Pattern patternURL = Pattern.compile(REGEX_URL);
        Matcher matcherURL = patternURL.matcher(strOfParagraph);

        while (matcherURL.find()) {

            String strOfURL = matcherURL.group();
            System.out.println();
            System.out.println("Нашли ссылку!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("URL:" + strOfURL);
            strOfParagraph = matcherURL.replaceFirst(strOfURL);
            System.out.println("!!!! " + strOfParagraph);

            LeafElement URL = new LeafElement(ElementType.URL, strOfURL);
        }


        //*********************
 */