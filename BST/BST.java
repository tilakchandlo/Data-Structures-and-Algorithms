import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Binary Search Tree
 * @author Tilak Patel
 * @version 1.0
 */
public class BST<T extends Comparable<T>> implements BSTInterface<T> {
    private Node<T> root;
    private int size;
    @Override
    public void add(T data) {
        root = insert(data, root);
        size++;
    }

    /**
     * Private helper method to add the data as a leaf in the BST.
     * Traverses the tree to find the
     * appropriate location using recursion.
     * @param data the data to be added
     * @param current the starting node where the data should be added from
     * @return the node that got inserted to the tree
     * with all the necessary links
     */
    private Node<T> insert(T data, Node<T> current) {
        if (current == null) {
            Node<T> newNode = new Node<T>(data);
            return newNode;
        }
        int compareResult = data.compareTo(current.getData());
        if (compareResult < 0) {
            current.setLeft(insert(data, current.getLeft()));
        } else if (compareResult > 0) {
            current.setRight(insert(data, current.getRight()));
        } else {
            size--;
        }
        return current;
    }

    @Override
    public T remove(T data) {
        if (root == null) {
            return null;
        }
        int oldSize = size;
        root = remove(data, root);
        if (size == oldSize - 1) {
            return data;
        }
        return null;
    }

    /**
     * Private helper method to remove the data from the BST.
     * Traverses the tree to find the
     * appropriate location using recursion
     * and removes the data accordingly (uses predecessor).
     * @param data the data to be removed
     * @param current the starting node where the data
     * should be removed from
     * @return the node that got deleted from the tree
     * with the data
     */
    private Node<T> remove(T data, Node<T> current) {
        if (current == null) {
            return null;
        }
        int compareResult = data.compareTo(current.getData());
        if (compareResult < 0) {
            current.setLeft(remove(data, current.getLeft()));
        } else if (compareResult > 0) {
            current.setRight(remove(data, current.getRight()));
        } else if (current.getLeft() != null && current.getRight() != null) {
            Node<T> maxLeft = findMax(current.getLeft());
            current.setData(maxLeft.getData());
            current.setLeft(remove(current.getData(), current.getLeft()));
        } else {
            if (current.getLeft() != null) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            size--;
        }
        return current;
    }

    /**
     * Private helper method to find the max of BST.
     * Traverses the tree to find the
     * appropriate location. Helps finding the predecessor.
     * @param node the root node where the traversing should begin from
     * @return the node that holds the max data value
     */
    private Node<T> findMax(Node<T> node) {
        if (node == null) {
            return node;
        }
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public T get(T data) {
        T toReturn = null;
        Node<T> returnNode = search(data, root);
        if (returnNode != null) {
            toReturn = returnNode.getData();
        }
        return toReturn;
    }

    @Override
    public boolean contains(T data) {
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
     * Private helper method to level order the BST.
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
     * Private helper method to find height of the BST.
     * Uses recursion to find the height of
     * the left and right sides of the tree.
     * @param node the node from where the height calculation will begin
     * @return the height of the tree
     */
    private int height(Node<T> node) {
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