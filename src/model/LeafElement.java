package model;

import java.util.ArrayList;
import java.util.List;



/**
 * Class-leaf in composite pattern.
 * Includes all operation for working with leaf elements.
 */
public class LeafElement implements Element {

    /**
     * Enum of current leaf type.
     */
    public enum LeafType implements ElementType {
        LETTER, SIGN, LISTING
    }

    private ElementType type;
    private String str;

    public LeafElement(ElementType type, String str) {
        this.type = type;
        this.str = str;
    }

    @Override
    public void addElement(Element part) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeElement(Element part) {
        throw new UnsupportedOperationException();
    }


    @Override
    public List<Element> getElement(ElementType findType) {
        List<Element> currentList = new ArrayList<>(1);
        if (type.equals(findType)) currentList.add(this);

        return currentList;
    }


    @Override
    public void setStringInLeaf(String elementaryString) {
        str = elementaryString;
    }

    @Override
    public List<Element> getListOfPart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return str;
    }
}

