import static org.junit.Assert.*;
 
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
 
/**
 * Created by Stephen Wilkins on October 27, 2014
 * These are made to be complete tests of every node
 * reference for a number of different additions/removals
 */
 
public class AdditionalTestsStephen {
    private AVL<Integer> integerList;
    
    @Before
    public void setUp() throws Exception {
        integerList = new AVL<Integer>();
    }
 
    @Test(timeout = 250)
    public void basicAddNoRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);
        
        integerList.add(2); //Adding 2
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 2, null, null, 1, false, 0, 0);
        
        integerList.add(2);
        integerList.add(1); //Adding 1
        root = integerList.getRoot();
        testNode(root, 2, 1, null, 2, false, 1, 1);
        testNode(root.getLeft(), 1, null, null, 2, false, 0, 0);
        
        integerList.add(1);
        integerList.add(3); //Adding 3
        root = integerList.getRoot();
        testNode(root, 2, 1, 3, 3, false, 1, 0);
        testNode(root.getLeft(), 1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);
    }
    
    @Test(timeout = 250)
    public void basicAddSingleRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);
        
        integerList.add(1); //Adding 1
        integerList.add(1);
        root = integerList.getRoot();
        testNode(root, 1, null, null, 1, false, 0, 0);
        
        integerList.add(1);
        integerList.add(2); //Adding 2
        root = integerList.getRoot();
        testNode(root, 1, null, 2, 2, false, 1, -1);
        testNode(root.getRight(), 2, null, null, 2, false, 0, 0);
        
        integerList.add(1);
        integerList.add(3); //Adding 3
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 2, 1, 3, 3, false, 1, 0);
        testNode(root.getLeft(), 1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);
    }
    
    @Test(timeout = 250)
    public void basicAddDoubleRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);
        
        integerList.add(1);
        root = integerList.getRoot();
        testNode(root, 1, null, null, 1, false, 0, 0);
        
        integerList.add(3);
        root = integerList.getRoot();
        testNode(root, 1, null, 3, 2, false, 1, -1);
        testNode(root.getRight(), 3, null, null, 2, false, 0, 0);
        
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 2, 1, 3, 3, false, 1, 0);
        testNode(root.getLeft(), 1, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 3, null, null, 3, false, 0, 0);
    }
    
    @Test(timeout = 250)
    public void complexAddNoRotation() {
        Node<Integer> root = integerList.getRoot();
        testNode(root, null, null, null, 0, true, 0, 0);
        
        integerList.add(4); //Adding 4
        integerList.add(4);
        root = integerList.getRoot();
        testNode(root, 4, null, null, 1, false, 0, 0);
        
        integerList.add(2); //Adding 2
        integerList.add(4);
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 4, 2, null, 2, false, 1, 1);
        testNode(root.getLeft(), 2, null, null, 2, false, 0, 0);
        
        integerList.add(6); //Adding 6
        integerList.add(4);
        integerList.add(2);
        root = integerList.getRoot();
        testNode(root, 4, 2, 6, 3, false, 1, 0);
        testNode(root.getLeft(), 2, null, null, 3, false, 0, 0);
        testNode(root.getRight(), 6, null, null, 3, false, 0, 0);
        
        integerList.add(1); //Adding 1
        integerList.add(6);
        root = integerList.getRoot();
        testNode(root, 4, 2, 6, 4, false, 2, 1);
        testNode(root.getLeft(), 2, 1, null, 4, false, 1, 1);
        testNode(root.getLeft().getLeft(), 1, null, null, 4, false, 0, 0);
        testNode(root.getRight(), 6, null, null, 4, false, 0, 0);
        
        integerList.add(5); //Adding 5
        integerList.add(6);
        integerList.add(4);
        root = integerList.getRoot();
        testNode(root, 4, 2, 6, 5, false, 2, 0);
        testNode(root.getLeft(), 2, 1, null, 5, false, 1, 1);
        testNode(root.getLeft().getLeft(), 1, null, null, 5, false, 0, 0);
        testNode(root.getRight(), 6, 5, null, 5, false, 1, 1);
        testNode(root.getRight().getLeft(), 5, null, null, 5, false, 0, 0);
 
        integerList.add(7); //Adding 7
        integerList.add(1);
        integerList.add(7);
        root = integerList.getRoot();
        testNode(root, 4, 2, 6, 6, false, 2, 0);
        testNode(root.getLeft(), 2, 1, null, 6, false, 1, 1);
        testNode(root.getLeft().getLeft(), 1, null, null, 6, false, 0, 0);
        testNode(root.getRight(), 6, 5, 7, 6, false, 1, 0);
        testNode(root.getRight().getLeft(), 5, null, null, 6, false, 0, 0);
        testNode(root.getRight().getRight(), 7, null, null, 6, false, 0, 0);
        
        integerList.add(3); //Adding 3
        root = integerList.getRoot();
        testNode(root, 4, 2, 6, 7, false, 2, 0);
        testNode(root.getLeft(), 2, 1, 3, 7, false, 1, 0);
        testNode(root.getLeft().getLeft(), 1, null, null, 7, false, 0, 0);
        testNode(root.getLeft().getRight(), 3, null, null, 7, false, 0, 0);
        testNode(root.getRight(), 6, 5, 7, 7, false, 1, 0);
        testNode(root.getRight().getLeft(), 5, null, null, 7, false, 0, 0);
        testNode(root.getRight().getRight(), 7, null, null, 7, false, 0, 0);
    }
    
    @Test(timeout = 250)
    public void complexAddSingleRotations() {
        
    }
    
    @Test(timeout = 250)
    public void complexAddDoubleRotations() {
        
    }
    
    private void testNode(Node<Integer> node, Integer data, Integer left, Integer right, int size, boolean empty, int height, int balance) {
        if (node != null) {
            assertEquals("Incorrect data", data, node.getData());
            if (left == null) {
                assertNull("Left node was not null", node.getLeft());
            } else {
                assertEquals("Incorrect left data", left, node.getLeft().getData());
            }
            if (right == null) {
                assertNull("Right node was not null", node.getRight());
            } else {
                assertEquals("Incorrect right data", right, node.getRight().getData());
            }
            assertEquals("Incorrect height", height, node.getHeight());
            assertEquals("Incorrect balance factor", balance, node.getBalanceFactor());
        }
        assertEquals("Incorrect size", size, integerList.size());
        if (empty) {
            assertTrue("Incorrect emptiness", integerList.isEmpty());
        } else {
            assertFalse("Incorrect emptiness", integerList.isEmpty());
        }
    }
 
}