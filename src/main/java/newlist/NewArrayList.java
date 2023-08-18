package newlist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where
 * in the list each element is inserted. The user can access elements by their integer index (position in the list).
 * Unlike sets, lists typically allow duplicate elements.
 *
 * @param <E> the type of elements in this list
 * @author Max Dragun
 * @version 1.0
 * @see NewArrayListImpl
 * @see List
 * @see ArrayList
 */
public interface NewArrayList<E> {
    /**
     * Adds an element to the end of the list
     *
     * @param e element to be appended to this list
     * @see #add(int, E)
     */
    void add(E e);

    /**
     * Adds an element to the list at the specified index
     *
     * @param index points to the index of the list where the new element should be added
     * @param e     element to be appended to this list
     * @throws IndexOutOfBoundsException if index is not out of range list
     * @see #add(E)
     */
    void add(int index, E e);

    /**
     * Get an element from a list by its index
     *
     * @param index index of the element to return
     * @return list item
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    E get(int index);

    /**
     * Replace element in list with new one by index
     *
     * @param index index of the element to return
     * @param e     element to be replaced to this list
     * @return element that has been replaced
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    E set(int index, E e);

    /**
     * Removes an element from the list by its index
     *
     * @param index index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    E remove(int index);

    /**
     * Clears the list of all elements
     */
    void clear();

    /**
     * Sorts the elements of a list according to a comparator
     *
     * @param comparator Comparator of class E, if null is entered then Comparator.naturalOrder() is used
     * @throws ClassCastException if null is entered and class E does not implement the Comparable interface
     * @see #sort()
     */
    void sort(Comparator<E> comparator);

    /**
     * Sorts the elements according to the compareTo method
     *
     * @throws ClassCastException if class E does not implement the Comparable interface
     * @see #sort(Comparator)
     */
    void sort();

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * Implementation of the {@link Object} class method
     * Creates a new list using shallow copying
     *
     * @return new list that is a shallow copy of this list
     * @see Object
     */
     NewArrayListImpl<E> clone();
}
