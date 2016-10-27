import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for various heap operations.
 * 
 * Adding is not explicitly covered because buildBigHeap() covers it.
 * 
 * Removing is not explicitly covered because tearDown() covers it.
 * 
 * PriorityQueue is not tested because every method in it should be a
 * self-explanatory one-liner.
 * 
 * Big O times are not checked.
 * 
 * @author Sam
 *
 */
public class HeapTest {
    private Heap<Integer> heap;

    @Before
    public void setUp() {
        heap = new Heap<Integer>();
    }

    @After
    public void tearDown() {
        Object[] data = heap.toArray();
        // Check that the rest of the array is null.
        assertEquals(null, data[0]);
        for (int i = heap.size() + 1; i < data.length; i++) {
            assertEquals(null, data[i]);
        }

        Integer lastValue = Integer.MIN_VALUE;
        // Check that the data is properly ordered.
        while (heap.size() > 0) {
            Integer currentValue = heap.remove();
            assertTrue(lastValue.compareTo(currentValue) <= 0);
            lastValue = currentValue;
        }

        assertEquals(0, heap.size());
    }

    private void buildBigHeap() {
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(17);
        heap.add(19);
        heap.add(36);
        heap.add(7);
        heap.add(25);
        heap.add(100);
    }

    @Test
    public void testIsEmptyEmpty() {
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testIsEmptyNotEmpty() {
        buildBigHeap();
        assertFalse(heap.isEmpty());
    }

    @Test
    public void testPeekEmpty() {
        assertEquals(null, heap.peek());
    }

    @Test
    public void testPeekNotEmpty() {
        buildBigHeap();
        assertEquals((Integer) 1, heap.peek());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, heap.size());
    }

    @Test
    public void testSizeNotEmpty() {
        buildBigHeap();
        assertEquals(9, heap.size());
    }
}
