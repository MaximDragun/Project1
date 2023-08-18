import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import newlist.NewArrayList;
import newlist.NewArrayListImpl;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class NewArrayListTest {
    private NewArrayList<Integer> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new NewArrayListImpl<>();
    }

    @Test
    void testAddAndGet() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        assertEquals(3, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testAddByIndexAndGet() {
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(1, 2);

        assertEquals(3, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testAddAndSetAndGet() {
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(1);
        Integer number = arrayList.set(2, 3);

        assertEquals(1, number);
        assertEquals(3, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(3, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testRemove() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        int removed = arrayList.remove(1);

        assertEquals(2, removed);
        assertEquals(2, arrayList.size());
        assertEquals(1, arrayList.get(0));
        assertEquals(3, arrayList.get(1));
    }

    @Test
    void testAddAndGetAndSetThrows() {
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(4, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(-3, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(-3));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(3, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(-3, -2));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(3));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(-3));
    }

    @Test
    void testClear() {
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        arrayList.clear();

        assertEquals(0, arrayList.size());
    }

    @Test
    void testSortWithComparatorNaturalOrder() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        arrayList.sort(Comparator.naturalOrder());

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testSortWithComparatorReverseOrder() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        arrayList.sort(Comparator.reverseOrder());

        assertEquals(3, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(1, arrayList.get(2));
    }

    @Test
    void testSortWithoutComparator() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        arrayList.sort();

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }
    @Test
    void testSortComparatorIsNull() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        arrayList.sort(null);

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testSortComparatorIsNullAndClassIsNotComparable() {
        class LocalTest{}
        NewArrayList<LocalTest> newArrayList = new NewArrayListImpl<>();

        newArrayList.add(new LocalTest());
        newArrayList.add(new LocalTest());
        newArrayList.add(new LocalTest());

        assertThrows(ClassCastException.class, newArrayList::sort);
    }

    @Test
    void testQuickSortWithoutComparator() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        NewArrayListImpl.quickSort(arrayList);

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    void testQuickSortWithComparatorReverseOrder() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        NewArrayListImpl.quickSort(arrayList, Comparator.reverseOrder());

        assertEquals(3, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(1, arrayList.get(2));
    }

    @Test
    void testQuickSortWithComparatorIsNull() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

       assertThrows(NullPointerException.class, () -> NewArrayListImpl.quickSort(arrayList, null));
    }

    @Test
    void testToString() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(2);

        assertEquals("[3, 1, 2]", arrayList.toString());
    }

    @Test
    void testClone() {
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(128);

        NewArrayList<Integer> cloneList = arrayList.clone();

        assertNotEquals(cloneList, arrayList);
        assertEquals(3, cloneList.size());
        assertEquals(3, cloneList.get(0));
        assertEquals(1, cloneList.get(1));
        assertEquals(128, cloneList.get(2));
        assertSame(arrayList.get(0), cloneList.get(0));
        assertSame(arrayList.get(2), cloneList.get(2));
    }
}
