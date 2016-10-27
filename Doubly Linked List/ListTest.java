import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.Before;

/**
 * Created by mjsobrep on 9/3/2014.
 */
public class ListTest {
    private LinkedListInterface<Integer> list;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<Integer>();
    }

    //test that it starts empty and throws nulls returns null when appropriate
    @Test (timeout = 200)
    public void testStart() {
        assertTrue(list.isEmpty());
        assertEquals(null, list.removeFromBack());
        assertEquals(null, list.removeFromFront());

    }

    //test that adding out of bounds at the start will throuw an error
    @Test (expected = IndexOutOfBoundsException.class)
    public void startAddOutOfBounds() {
        list.addAtIndex(3, new Integer(5));
    }

    //test that get at start will throw error at index = 0
    @Test (expected = IndexOutOfBoundsException.class)
    public void getAtEmpty(){
        list.get(0);
    }

    //test that get at start will throw error at index != 0
    @Test (expected = IndexOutOfBoundsException.class)
    public void getAtEmpty1(){
        list.get(1);
    }

    //test remove at index for empty index = 0
    @Test (expected = IndexOutOfBoundsException.class)
    public void removeEmpty0(){
        list.removeAtIndex(0);
    }

    //test remove at index for empty index != 0
    @Test (expected = IndexOutOfBoundsException.class)
    public void removeEmpty1(){
        list.removeAtIndex(1);
    }

//test that the toArray gives an empty array
    @Test (timeout = 200)
    public void emptyToArray(){
        assertEquals(0, list.toArray().length);
    }

    //*******TEST Populating list***********

    //should look like [4 8 6 9 7 5]
    @Test (timeout = 200)
    public void checkAddStuff()
    {
        list.addAtIndex(0, new Integer(9));
        list.addAtIndex(0, new Integer(8));
        list.addAtIndex(2, new Integer(7));
        list.addAtIndex(1,new Integer(6));
        list.addToBack(new Integer(5));
        list.addToFront(new Integer(4));


        assertEquals(new Integer(4),list.removeFromFront());
        assertEquals(new Integer(5),list.removeFromBack());
        assertEquals(new Integer(8),list.removeAtIndex(0));
        assertEquals(new Integer(7),list.removeAtIndex((Integer)list.size()-1));
        assertEquals(new Integer(9),list.removeAtIndex(1));
        assertEquals(new Integer(6),list.removeFromBack());
    }

    //check one item left with remove from front
    @Test(timeout = 200)
    public void frontOffSmall(){
        list.clear();
        assertTrue(list.isEmpty());
        list.addToBack(new Integer(5));
        list.addToBack(new Integer(9));

        assertEquals(new Integer(5),list.removeFromFront());
        assertFalse(list.isEmpty());
        assertEquals(new Integer(9),list.removeFromFront());
    }

    //check out the clear function
    @Test(timeout = 200)
    public void testClear(){
        list.addAtIndex(0, new Integer(9));
        list.addAtIndex(0, new Integer(8));
        list.addAtIndex(2, new Integer(7));
        list.addAtIndex(1,new Integer(6));
        list.addToBack(new Integer(5));
        list.addToFront(new Integer(4));

        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    // test get function [4 8 6 9 7 5]
    @Test(timeout = 200)
    public void testGet(){

        list.addAtIndex(0, new Integer(9));
        list.addAtIndex(0, new Integer(8));
        list.addAtIndex(2, new Integer(7));
        list.addAtIndex(1,new Integer(6));
        list.addToBack(new Integer(5));
        list.addToFront(new Integer(4));

        assertEquals(new Integer(4),list.get(0));
        assertEquals(new Integer(6),list.get(2));
        assertEquals(new Integer(5),list.get(list.size()-1));
    }
}
