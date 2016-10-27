import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
/**
 * My AVL implementation.
 *
 * @author Tilak Patel
 */
public class AVL<T extends Comparable<T>> implements AVLInterface<T>,
       Gradable<T> {

    // Do not add additional instance variables
    private Node<T> root;
    private int size;

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = add(data, root);
    }

    /**
     * Private helper method to add the data as a leaf in the AVL.
     * Traverses the tree to find the
     * appropriate location using recursion.
     * @param data the data to be added
     * @param current the starting node where the data should be added from
     * @return the node that got inserted to the tree
     * with all the necessary links
     */
    public Node<T> add(T data, Node<T> node) {
        if (node == null) {
            Node<T> newNode = new Node<T>(data);
            size++;
            return newNode;
        } else {
            int compareResult = data.compareTo(node.getData());
            if (compareResult < 0) {
                node.setLeft(add(data, node.getLeft()));
                updateHeightAndBF(node);
                node = rotate(node);
                updateHeightAndBF(node);
            } else if (compareResult > 0) {
                node.setRight(add(data, node.getRight()));
                updateHeightAndBF(node);
                node = rotate(node);
                updateHeightAndBF(node);
            }
            return node;
        }
    }

    /**
     * Private helper method to update the current
     * node's height and balance factor
     * @param node the starting node where the data should be added from
     */
    private void updateHeightAndBF(Node<T> node) {
        if (node != null) {
            int leftHeight = -1;
            int rightHeight = -1;
            if (node.getLeft() != null) {
                leftHeight = node.getLeft().getHeight();
            }
            if (node.getRight() != null) {
                rightHeight = node.getRight().getHeight();
            }
            node.setHeight(Math.max(leftHeight, rightHeight) + 1);
            node.setBalanceFactor(leftHeight - rightHeight);
        }
    }

    /**
     * Private helper method to rotate the nodes in
     * the AVL.
     * @param node the starting node where the data should be
     * rotated from
     * @return the node that got rotated
     * with all the necessary links
     */
    private Node<T> rotate(Node<T> node) {
        if (node != null) {
            if (node.getBalanceFactor() > 1) {
                if (node.getLeft().getBalanceFactor() < 0) {
                    node = rotateLeftRight(node);
                } else {
                    node = rotateRight(node);
                }
            } else if (node.getBalanceFactor() < -1) {
                if (node.getRight().getBalanceFactor() > 0) {
                    node = rotateRightLeft(node);
                } else {
                    node = rotateLeft(node);
                }
            }
            return node;
        }
        return null;
    }

    /**
     * Private helper method to right rotate the node in
     * the AVL (balance factor < -1)
     * @param node the starting node where the data should be
     * rotated from
     * @return the node that got rotated right
     * with all the necessary links
     */
    private Node<T> rotateRight(Node<T> node) {
        Node<T> temp = node.getLeft();
        node.setLeft(temp.getRight());
        temp.setRight(node);
        updateHeightAndBF(node);
        updateHeightAndBF(temp);
        return temp;
    }

    /**
     * Private helper method to left rotate the node in
     * the AVL (balance factor < -1)
     * @param node the starting node where the data should be
     * rotated from
     * @return the node that got rotated right
     * with all the necessary links
     */
    private Node<T> rotateLeft(Node<T> node) {
        Node<T> temp = node.getRight();
        node.setRight(temp.getLeft());
        temp.setLeft(node);
        updateHeightAndBF(node);
        updateHeightAndBF(temp);
        return temp;
    }

    /**
     * Private helper method to left rotate and then rotate
     * right the node in the AVL (balance factor > 1)
     * @param node the starting node where the data should be
     * rotated from
     * @return the node that got rotated right
     * with all the necessary links
     */
    private Node<T> rotateLeftRight(Node<T> node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }

    /**
     * Private helper method to right rotate and then rotate
     * left the node in the AVL (balance factor < -1)
     * @param node the starting node where the data should be
     * rotated from
     * @return the node that got rotated right
     * with all the necessary links
     */
    private Node<T> rotateRightLeft(Node<T> node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> toReturn = new Node<>(null);
        root = remove(root, data, toReturn);
        return toReturn.getData();
    }

    /**
     * Private helper method to remove the data from the AVL.
     * Traverses the tree to find the
     * appropriate location using recursion
     * and removes the data accordingly (uses successor).
     * @param data the data to be removed
     * @param curr the starting node where the data
     * should be removed from
     * @param toReturn the node that gets returned
     * @return the node that got deleted from the tree
     * with the data
     */
    private Node<T> remove(Node<T> curr, T data, Node<T> toReturn) {
        if (curr == null) {
            return null;
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, toReturn));
            updateHeightAndBF(curr);
            curr = rotate(curr);
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, toReturn));
            updateHeightAndBF(curr);
            curr = rotate(curr);
        } else {
            size--;
            toReturn.setData(curr.getData());

            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() == null) {
                curr = curr.getRight();
            } else if (curr.getRight() == null) {
                curr = curr.getLeft();
            } else {
                curr.setData(twoChildren(curr));
                updateHeightAndBF(curr);
                curr = rotate(curr);
            }
        }
        return curr;
    }
    /**
     * Private helper method to find the successor.
     * @param curr the starting node where the data should be added
     * from
     * @return the date for the successor node
     */
    private T twoChildren(Node<T> curr) {
        Node<T> suc = curr.getRight();
        Node<T> sucParent = null;
        while (suc.getLeft() != null) {
            sucParent = suc;
            suc = suc.getLeft();
        }
        T ret = suc.getData();
        if (sucParent == null) {
            curr.setRight(suc.getRight());
        } else {
            sucParent.setLeft(suc.getRight());
        }
        return ret;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        T toReturn = null;
        Node<T> returnNode = search(data, root);
        if (returnNode != null) {
            toReturn = returnNode.getData();
        }
        return toReturn;
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        return (search(data, root) != null);
    }

    /**
     * Private helper method to search for a specific node with the
     * contained data.
     * @param data the data that needs to be located or searched for
     * @return the node that holds the data value that needs to be found
     */
    private Node<T> search(T data, Node<T> node) {
        if (node != null) {
            int compareResult = data.compareTo(node.getData());
            if (compareResult < 0) {
                return search(data, node.getLeft());
            } else if (compareResult > 0) {
                return search(data, node.getRight());
            } else {
                return node;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        return preOrder(new LinkedList<T>(), root);
    }
    /**
     * Private helper method to complete the pre order traversal.
     * @param list the list that will be returned with the tree
     * pre ordered
     * @param node the starting node where the traversal will begin from
     * @return the list that is pre ordered
     */
    private List<T> preOrder(LinkedList<T> list, Node<T> node) {
        if (node != null) {
            list.add(node.getData());
            preOrder(list, node.getLeft());
            preOrder(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> postorder() {
        return postOrder(new LinkedList<T>(), root);
    }
    /**
     * Private helper method to complete the post order traversal.
     * @param list the list that will be returned with the tree
     * post ordered
     * @param node the starting node where the traversal will begin from
     * @return the list that is post ordered
     */
    private List<T> postOrder(LinkedList<T> list, Node<T> node) {
        if (node != null) {
            postOrder(list, node.getLeft());
            postOrder(list, node.getRight());
            list.add(node.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {
        return inOrder(new LinkedList<T>(), root);
    }
    /**
     * Private helper method to complete the in order traversal.
     * @param list the list that will be returned with the tree
     * in ordered
     * @param node the starting node where the traversal will begin from
     * @return the list that is in order
     */
    private List<T> inOrder(LinkedList<T> list, Node<T> node) {
        if (node != null) {
            inOrder(list, node.getLeft());
            list.add(node.getData());
            inOrder(list, node.getRight());
        }
        return list;
    }

    @Override
    public List<T> levelorder() {
        return levelOrder(new LinkedList<T>(), root);
    }

    /**
     * Private helper method to level order the AVL.
     * Uses a queue to help level order.
     * @param list the linked list that will be level ordered
     * @param node the node from where the adding of
     * the data to the list will begin
     * @return the list that will be level ordered
     */
    private List<T> levelOrder(LinkedList<T> list, Node<T> node) {
        if (node != null) {
            Queue<Node<T>> level = new LinkedList<Node<T>>();
            level.add(node);
            while (!level.isEmpty()) {
                node = level.remove();
                list.add(node.getData());
                if (node.getLeft() != null) {
                    level.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    level.add(node.getRight());
                }
            }
        }
        return list;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        return height(root);
    }

    /**
     * Private helper method to find height of the AVL.
     * Uses recursion to find the height of
     * the left and right sides of the tree.
     * @param node the node from where the height calculation will begin
     * @return the height of the tree
     */
    public int height(Node<T> node) {
        if (node == null) {
            return -1;
        } else {
            return Math.max(height(node.getLeft()),
                    height(node.getRight())) + 1;
        }
    }

    @Override
    public Node<T> getRoot() {
        return root;
    }
}
