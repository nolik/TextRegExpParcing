package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



/**
 * Class-composite in composite pattern.
 * Includes all operation for working with composite elements.
 */
public class CompositeElement implements Element {
    /**
     * Enum of current composite type elements.
     */
   public enum CompositeType implements ElementType {
        TEXT, PARAGRAPH, SENTENCE, WORD
    }

    private ElementType type;


    /**
     * It's the list of all subelements in current Element.
     */
    private List<Element> part = new ArrayList<>();


    public CompositeElement(ElementType elementType) {
        type = elementType;
    }

    @Override
    public void addElement(Element part) {
        this.part.add(part);
    }

    @Override
    public void removeElement(Element part) {
        this.part.remove(part);
    }


    @Override
    public List<Element> getElement(ElementType findType) {

        List<Element> findingElements = new LinkedList<>();

        if (type.equals(findType)) {
            findingElements.add(this);
        } else {
            //go find to list of part
            for (Element x : part) {
                findingElements.addAll(x.getElement(findType));
            }
        }
        return findingElements;
    }


    @Override
    public void setStringInLeaf(String elementaryString) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Element> getListOfPart() {
        return part;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Element x : part) {
            result.append(x.toString());
        }
        return result.toString();
    }
}




