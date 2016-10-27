import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSets<T> implements DisjointSetsInterface<T> {
    //Should be a map of data to its parent; root data maps to itself.
    private Map<T, Node> set;

    /**
     * @param setItems the initial items (sameSet and merge will never be called
     * with items not in this set, this set will never contain null elements).
     */
    public DisjointSets(Set<T> setItems) {
        set = new HashMap<T, Node>();
        for (T item : setItems) {
            set.put(item, new Node(item, 0));
        }
    }

    /**
     * Helper method to find the parent root of the given item.
     * Will return the root node in the end by using recursively
     * searching up by finding the parents. Does Path compression - the
     * node of the given item will point to the root node.
     *
     * @param toFind the item's (vertex's) root that needs to be found
     * @return the root of the vertex
     */
    private T find(T toFind) {
        Node node = set.get(toFind);
        if (node == null) {
            return null;
        }
        if (toFind.equals(node.parent)) {
            return toFind;
        }
        node.parent = find(node.parent);
        return node.parent;
    }

    @Override
    public boolean sameSet(T u, T v) {
        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        return (find(u).equals(find(v)));
    }

    @Override
    public void merge(T u, T v) {
        if (u == null || v == null) {
            throw new IllegalArgumentException();
        }
        T uRoot = find(u);
        T vRoot = find(v);
        if (!uRoot.equals(find(vRoot))) {
            Node uNode = set.get(uRoot);
            Node vNode = set.get(vRoot);
            if (uNode.rank < vNode.rank) {
                uNode.parent = vRoot;
            } else if (uNode.rank > vNode.rank) {
                vNode.parent = uRoot;
            } else {
                vNode.parent = uRoot;
                uNode.rank++;
            }
        }
    }

    /**
     * Private inner Node class.
     */
    private class Node {
        private int rank;
        private T parent;

        private Node(T parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }
}
