import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class CircularLinkedListTest2 {

    private CircularLinkedList<Integer> list;

    @Before
    public void setup() {
       list = new CircularLinkedList<Integer>();
    }

    @Test(timeout = 1000)
    public void testCircularity() {
        addManyElementsToBack();
        Node<Integer> head = list.getHead();
        Node<Integer> curNode = head.getNext();
        while (curNode != head) {
            if (curNode == head) { //testing for reference equality!
                assertTrue(curNode == head);
            }
            curNode = curNode.getNext();
        }
    }

    @Test(timeout = 1000)
    public void testTailPointsToHead() {
        addManyElementsToBack();
        Node<Integer> head = list.getHead();
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));
        list.addToBack(new Integer(3));

        assertTrue(null != list.removeAtIndex(4));

        list.removeAtIndex(20);
        list.removeFromBack();

        assertTrue(list.getTail().getNext() == head);
    }

    @Test(timeout = 1000)
    public void testRandomAddAndGet() {
        addManyElementsToBack();

        Random rand = new Random();

        for (int i = 0; i <= 200; i++) {
            int index = rand.nextInt(list.size());
            Integer randNum = new Integer(rand.nextInt());
            list.addAtIndex(index, randNum);
            assertEquals(randNum, list.get(index));
        }
    }

    private Integer[] addManyElementsToBack() {
        Integer[] nums = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            list.addToBack(new Integer(i));
            nums[i] = new Integer(i);
        }
        return nums;
    }
}
