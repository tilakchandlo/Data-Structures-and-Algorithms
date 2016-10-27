import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by fmontei1 on 9/24/2014.
 */
public class AdditionalTests {
    private Heap<Integer> heap;

    @Before
    public void setUp() {
        heap = new Heap<Integer>();
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testAddNull() {
        heap.add(null);
    }

    @Test(timeout = 200)
    public void testResize() {
        addMany(100);
        assertEquals(entrySize(), heap.size());
    }

    @Test(timeout = 200)
    public void testMultipleRemoves() {
        int size = 100;
        addMany(size);
        while (!heap.isEmpty()) {
            heap.remove();
            assertEquals(--size, heap.size());
        }
        assertEquals(0, heap.size());
        assertEquals(null, heap.peek());
        assertEquals(null, heap.remove());
        //assertEquals(0, heap.size());
        int i = 0;
        Comparable arr[] = heap.toArray();
        while (arr[i] != null) {
            assertNull(arr[i++]);
        }
    }

    @Test(timeout = 200)
    public void testAddWithRedundantEntries() {
        heap.add(7);
        heap.add(7);
        heap.add(6);
        heap.add(6);
        heap.add(6);
        heap.add(5);
        heap.add(5);
        heap.add(1);
        checkBackingArray(1, 5, 5, 6, 6, 7, 6, 7);
        assertEquals(entrySize(), heap.size());
    }

    /**
     * This test tests the following:
     * a) heap is resizing
     * b) heap is properly sorted after adding multiple entries, including small
     *    entries at the end, which forces a lot of swapping to take place.
     * c) heap is properly sorted after removal of a few entries.
     */
    @Test(timeout = 200)
    public void testAdvancedRemove() {
        basicAdd(5, 2, 8, 12, 31, 4, 6, 11, 15, 1, 9, 17, 13, 11, 21, 16, 17, 3, 14, 7, 0);
        Integer[] addOutput = {0, 1, 4, 3, 2, 8, 6, 12, 11, 5, 9, 17, 13, 11, 21, 16, 17, 15, 14, 31, 7};
        checkBackingArray(addOutput);

        assertEquals((Integer) 0, heap.remove());
        Integer[] removeOutput = new Integer[]{1, 2, 4, 3, 5, 8, 6, 12, 11, 7, 9, 17, 13, 11, 21, 16, 17, 15, 14, 31};
        checkBackingArray(removeOutput);

        assertEquals((Integer) 1, heap.remove());
        removeOutput = new Integer[]{2, 3, 4, 11, 5, 8, 6, 12, 14, 7, 9, 17, 13, 11, 21, 16, 17, 15, 31};
        checkBackingArray(removeOutput);

        assertEquals((Integer) 2, heap.remove());
        removeOutput = new Integer[]{3, 5, 4, 11, 7, 8, 6, 12, 14, 31, 9, 17, 13, 11, 21, 16, 17, 15};
        checkBackingArray(removeOutput);
    }

    /**
     * In addition to the previous test, this test checks that redundant entries
     * are handled properly.
     */
    @Test(timeout = 200)
    public void testAdvancedRemoveWithRedundantEntries() {
        basicAdd(5, 2, 8, 12, 31, 4, 6, 11, 15, 1, 9, 17, 13, 11, 21, 16, 17, 5, 4, 7);
        Integer[] addOutput = new Integer[]{1, 2, 4, 4, 5, 8, 6, 12, 5, 7, 9, 17, 13, 11, 21, 16, 17, 15, 11, 31};
        checkBackingArray(addOutput);

        assertEquals((Integer) 1, heap.remove());
        Integer[] removeOutput = new Integer[]{2, 4, 4, 5, 5, 8, 6, 12, 11, 7, 9, 17, 13, 11, 21, 16, 17, 15, 31};
        checkBackingArray(removeOutput);

        assertEquals((Integer) 2, heap.remove());
        removeOutput = new Integer[]{4, 5, 4, 11, 5, 8, 6, 12, 15, 7, 9, 17, 13, 11, 21, 16, 17, 31};
        checkBackingArray(removeOutput);

        assertEquals((Integer) 4, heap.remove());
        removeOutput = new Integer[]{4, 5, 6, 11, 5, 8, 11, 12, 15, 7, 9, 17, 13, 31, 21, 16, 17};
        checkBackingArray(removeOutput);
    }

    private void basicAdd(Integer... numbers) {
        for (Integer i : numbers) {
            if (i != null) {
                heap.add(i);
            }
        }
    }

    private void addMany(int ceiling) {
        for (int i = 0; i < ceiling; i++) {
            heap.add(i);
        }
    }

    /**
     * Counts the number of actual entries in the heap by ignoring all null
     * entries.
     */
    private int entrySize() {
        int count = 0;
        Comparable arr[] = heap.toArray();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            }
            count++;
        }
        return count;
    }

    private void checkBackingArray(Integer... numbers) {
        @SuppressWarnings("rawtypes")
        Comparable[] backingArray = heap.toArray();
        assertNull(backingArray[0]);

        for (int i = 1; i < backingArray.length; i++) {
            if (i > numbers.length) {
                assertNull(backingArray[i]);
            } else {
                assertEquals(numbers[i - 1], backingArray[i]);
            }
        }
    }
}
