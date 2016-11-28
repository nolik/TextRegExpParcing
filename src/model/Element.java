package model;

import java.util.List;

/**
 * Interface - Component in Composite pattern.
 * Includes main operation with Composite and Leaf.
 *
 * @author Ihar Novik
 */
public interface Element {
    /**
     * Method add element to list of part in Composite element.
     * For LeafElement throw UnsupportedOperationException.
     *
     * @param part - Element when we add to list of part.
     */
    public void addElement(Element part);

    /**
     * Method  remove element from list of part in Composite element.
     * For LeafElement throw UnsupportedOperationException.
     *
     * @param part
     */
    public void removeElement(Element part);

    /**
     * Method return list of Elemtent's that find in parameter findType.
     *
     * @param findType  finding type.
     * @return list of finding Element's.
     */
    public List<Element> getElement(ElementType findType);

    //еще одна пробная функция взять строку от элемента

    /**
     * Method change elementary string in LeafElement.
     * For CompositeElement method throw  UnsupportedOperationException.
     *
     * @param elementaryString
     */
    public void setStringInLeaf(String elementaryString);

      /**
     * Method returned list of part's of this CompositeElement.
     * For LeafElement method throw  UnsupportedOperationException.
     * @return list of part Element's includes in this CompositeElement
     */
    public List<Element> getListOfPart();
}
