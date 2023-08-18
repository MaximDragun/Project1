package newlist;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * Is an implementation of the {@link NewArrayList}, {@link RandomAccess}, {@link Cloneable}, {@link Serializable} interface
 * Simplified version of the {@link ArrayList} with simplified functionality, it has methods such as:
 * Adding an element, adding an element by index, getting an element, replacing an element,
 * removing an element, sorting a list with and without a comparator, clearing a list, getting the length of a list
 * and static methods to implement quick sorting of a list.
 * The list is implemented like {@link ArrayList} using an array Objects
 * The ability to iterate over the list, as well as deep cloning as well as many other features will of course be added in future updates!!!
 *
 * @param <E> the type of elements in this list
 *
 * @author  Max Dragun
 * @version 1.0
 */
public class NewArrayListImpl<E>
        implements NewArrayList<E>, RandomAccess, Cloneable, Serializable {
    /**
     * Serialized data version
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Default initial capacity is 10
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * The array buffer into which the elements of the NewArrayListImpl are stored.
     */
    private transient Object[] data;
    /**
     * List length
     */
    private int size;

    /**
     * Empty list constructor with capacity 10
     */
    public NewArrayListImpl() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Empty list constructor with predefined initial capacity
     *
     * @param initialCapacity - length of internal data array
     * @throws IllegalArgumentException - if the entered value of the initialCapacity parameter is less than 0
     */
    public NewArrayListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
    /**
     * Adds an element to the end of the list
     * When adding an element, the list length is checked, if it is equal to the length of the internal array, then a new array is created 1.5 times larger than the previous one and all elements of the old array are copied to the new one, and the data variable stores a link to the new array.
     * Write the element to the end of the array and increase the value of the length of the list
     *
     * @param e element to be appended to this list
     * @see #add(int, E)
     */
    public void add(E e) {
        ensureCapacity();
        data[size++] = e;
    }

    /**
     * Adds an element to the list at the specified index
     * When adding an element, the list length is checked, if it is equal to the length of the internal array, then a new array is created 1.5 times larger than the previous one and all elements of the old array are copied to the new one, and the data variable stores a link to the new array.
     * We shift all elements one to the right and insert a new element at index
     *
     * @param index points to the index of the list where the new element should be added
     * @param e     element to be appended to this list
     * @throws IndexOutOfBoundsException if index is not out of range list
     * @see #add(E)
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }

    /**
     * Get an element from a list by its index
     *
     * @param index index of the element to return
     * @return list item
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) data[index];
    }

    /**
     * Replace element in list with new one by index
     *
     * @param index index of the element to return
     * @param e     element to be replaced to this list
     * @return element that has been replaced
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = (E)data[index];
        data[index] = e;
        return oldElement;
    }

    /**
     * Removes an element from the list by its index
     * When deleting, all elements are shifted to the left by 1 position before the index position, and null is written to the last element
     *
     * @param index index of the element to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException if index is not out of range list
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E removedElement = (E) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
        return removedElement;
    }

    /**
     * Clears the list of all elements
     * Resets the length of the list to zero
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts the elements of a list according to a comparator. Sorting method - TimSort
     *
     * @param comparator Comparator of class E, if null is entered then method compareTo is used
     * @throws ClassCastException if null is entered and class E does not implement the Comparable interface
     * @see #sort()
     */
    public void sort(Comparator<E> comparator) {
        Arrays.sort((E[]) data, 0, size, comparator);
    }
    /**
     * Sorts the elements according to the compareTo method. Sorting method - TimSort
     *
     * @throws ClassCastException if class E does not implement the Comparable interface
     * @see #sort(Comparator)
     */
    public void sort() {
        sort(null);
    }

    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, (int) (data.length * 1.5));
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Implementation of the {@link Object} class method
     * Creates a new list using shallow copying, references to lists will be different,
     * references to internal arrays are also different, but the reference to an array element is the same
     *
     * @return new list that is a shallow copy of this list
     * @see Object
     */
    @Override
    public NewArrayListImpl<E> clone() {
        try {
            NewArrayListImpl<E> clone = (NewArrayListImpl<E>) super.clone();
            clone.data = Arrays.copyOf(data, data.length);
            return clone;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    /**
     * Implementation of the {@link Object} class method
     * Representing a list as a comma-separated enumeration of its elements
     *
     * @return string representation of the object.
     * @see Object
     */
    @Override
    public String toString() {
        int iMax = size - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(data[i]);
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }

    /**
     * Implementation of the quicksort algorithm for sorting a list {@link NewArrayList<E>}
     * The E class must implement the {@link Comparable<E>} interface when comparing elements, use the {@link Comparable#compareTo(Object)} method
     *
     * @param list reference to the {@link NewArrayList<E>} list to be sorted
     */
    public static <E extends Comparable<E>> void quickSort(NewArrayList<E> list) {
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * Implementation of the quicksort algorithm for sorting a list {@link NewArrayList<E>} using comparator for class E
     *
     * @param list reference to the {@link NewArrayList<E>} list to be sorted
     * @param comparator class E comparator to compare elements
     * @throws NullPointerException if comparator is null
     */
    public static <E> void quickSort(NewArrayList<E> list, Comparator<E> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        quickSort(list, 0, list.size() - 1, comparator);
    }

    private static <E extends Comparable<E>> void quickSort(NewArrayList<E> list, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(list, left, right);
            quickSort(list, left, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, right);
        }
    }

    private static <E> void quickSort(NewArrayList<E> list, int left, int right, Comparator<E> comparator) {
        if (left < right) {
            int lastIndex = partition(list, left, right, comparator);
            quickSort(list, left, lastIndex - 1, comparator);
            quickSort(list, lastIndex + 1, right, comparator);
        }
    }

    private static <E extends Comparable<E>> int partition(NewArrayList<E> list, int left, int right) {
        E last = list.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (list.get(j).compareTo(last) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, right);
        return i + 1;
    }

    private static <E> int partition(NewArrayList<E> list, int left, int right, Comparator<E> comparator) {
        E last = list.get(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(list.get(j), last) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, right);
        return i + 1;
    }

    private static <E> void swap(NewArrayList<E> list, int i, int j) {
        E element = list.get(i);
        list.set(i, list.get(j));
        list.set(j, element);
    }

}
