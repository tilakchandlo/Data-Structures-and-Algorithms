import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;


public class SkipListTest {
    private SkipList<Integer> list;
    
    @Before
    public void setUp() throws Exception {
        list = new SkipList<Integer>(new CoinFlipper(new Random(10)));
    }
    
    private void addData() {
        list.put(1);
        list.put(2);
        list.put(3);
        list.put(4);
        list.put(-3);
        list.put(-7);
        list.put(11);
        list.put(22);
        list.put(18);
        list.put(7);
        list.put(16);
        list.put(1001);
        list.put(12);
        list.put(9);
        list.put(0);
    }

    @Test
    public void testFirstEmpty() {
        assertEquals(null, list.first());
    }
    
    @Test
    public void testFirstNotEmpty() {
        addData();
        assertEquals(new Integer(-7), list.first());
    }

    @Test
    public void testLastEmpty() {
        assertEquals(null, list.last());
    }
    
    @Test
    public void testLastNotEmpty() {
        addData();
        assertEquals(new Integer(1001), list.last());
    }

    @Test
    public void testContainsEmpty() {
        assertFalse(list.contains(1));
        assertFalse(list.contains(10));
        assertFalse(list.contains(0));
    }
    
    @Test
    public void testContainsNotEmpty() {
        addData();
        assertTrue(list.contains(1));
        assertFalse(list.contains(10));
        assertTrue(list.contains(0));
    }
    
    @Test
    public void testNoDuplicates() {
        list.put(1);
        list.put(1);
        assertEquals(1, list.size());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testPutNull() {
        list.put(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testRemoveNull() {
        list.remove(null);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testContainsNull() {
        list.contains(null);
    }
    
    @Test
    public void testGetEmpty() {
        assertEquals(null, list.get(1));
        assertEquals(null, list.get(10));
        assertEquals(null, list.get(0));
    }
    
    @Test
    public void testGetNotEmpty() {
        addData();
        assertEquals(new Integer(1), list.get(1));
        assertEquals(null, list.get(10));
        assertEquals(new Integer(0), list.get(0));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testGetNull() {
        assertEquals(null, list.get(null));
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, list.size());
    }

    @Test
    public void testSizeNotEmpty() {
        addData();
        assertEquals(15, list.size());
    }
    
    @Test
    public void testClearEmpty() {
        list.clear();
        assertEquals(0, list.size());
        assertEquals(null, list.first());
        assertEquals(null, list.last());
    }
    
    @Test
    public void testClearNotEmpty() {
        addData();
        list.clear();
        assertEquals(0, list.size());
        assertEquals(null, list.first());
        assertEquals(null, list.last());
    }

    @Test
    public void testDataSetEmpty() {
        assertEquals(0, list.dataSet().size());
    }
    
    @Test
    public void testDataSetNotEmpty() {
        addData();
        // I'm too lazy to check if all of the values are here.
        assertEquals(15, list.dataSet().size());
    }

    @Test
    public void testRemoveEmpty() {
        assertEquals(null, list.remove(100));
        assertEquals(null, list.remove(51));
    }
    
    @Test
    public void testRemoveNotEmpty() {
        addData();
        assertEquals(null, list.remove(100));
        assertEquals(15, list.size());
        assertEquals(new Integer(1), list.remove(1));
        assertEquals(14, list.size());
    }
}
