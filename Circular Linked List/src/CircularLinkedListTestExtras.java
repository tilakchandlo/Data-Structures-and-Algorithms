import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;


public class CircularLinkedListTestExtras {
    private CircularLinkedList<Integer> list;
    
    @Before
    public void setup() {
       list = new CircularLinkedList<Integer>();
    }
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex1() {
        list.addAtIndex(-1, new Integer(0));
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex2() {
        list.addAtIndex(10, new Integer(0));
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex3() {
        list.removeAtIndex(-1);
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex4() {
        list.removeAtIndex(1);
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex5() {
        list.removeAtIndex(0);
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex6() {
        list.get(1);
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex7() {
        list.get(0);
    }
    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testBadIndex8() {
        list.get(-1);
    }
    
    @Test(timeout = 200)
    public void testRemoveFromFront() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertEquals(new Integer(1), list.removeFromFront());
        assertEquals(new Integer(2), list.removeFromFront());
        assertEquals(new Integer(3), list.removeFromFront());
        assertEquals(null, list.removeFromFront());
        // Just making sure.
        assertEquals(null, list.removeFromFront());
    }
    
    @Test(timeout = 200)
    public void testRemoveFromBack() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertEquals(new Integer(3), list.removeFromBack());
        assertEquals(new Integer(2), list.removeFromBack());
        assertEquals(new Integer(1), list.removeFromBack());
        assertEquals(null, list.removeFromBack());
        // Just making sure.
        assertEquals(null, list.removeFromBack());
    }
    
    @Test(timeout = 200)
    public void testMulti1() {
        list.addToFront(1);
        list.addToBack(2);
        list.addToFront(-1);
        assertEquals(3, list.size());
        assertFalse(list.isEmpty());
        
        list.addAtIndex(3, 3);
        list.addAtIndex(0, -1);
        assertEquals(5, list.size());
        assertFalse(list.isEmpty());
        
        assertEquals(new Integer(-1), list.removeFromFront());
        assertEquals(new Integer(3), list.removeFromBack());
        list.clear();
        assertTrue(list.isEmpty());
    }
    
    @Test(timeout = 200)
    public void testMulti2() {
        list.addToBack(Integer.MAX_VALUE);
        list.addToBack(Integer.MIN_VALUE);
        assertEquals(2, list.size());
        assertEquals(new Integer(Integer.MAX_VALUE), list.removeFromFront());
        assertEquals(new Integer(Integer.MIN_VALUE), list.removeFromBack());
    }
}
