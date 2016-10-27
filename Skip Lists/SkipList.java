import java.util.HashSet;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>> implements
        SkipListInterface<T> {
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;

    /**
     * constructs a SkipList object that stores data in ascending order when an
     * item is inserted, the flipper is called until it returns a tails if for
     * an item the flipper returns n heads, the corresponding node has n + 1
     * levels
     *
     * @param coinFlipper
     *            the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public T first() {
        Node<T> current = head;
        if (size == 0) {
            return null;
        }
        while (current.getDown() != null) {
            current = current.getDown();
        }
        return current.getNext().getData();
    }

    @Override
    public T last() {
        if (size == 0) {
            return null;
        }
        Node<T> current = head;
        while (current != null) {
            if (current.getDown() == null && current.getNext() == null) {
                return current.getData();
            } else if (current.getNext() == null) {
                current = current.getDown();
            } else {
                current = current.getNext();
            }
        }
        return null;
    }

    @Override
    public boolean contains(T data) {
        return get(data) != null;
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        int levelsToMove = 1;
        while (coinFlipper.flipCoin() == CoinFlipper.Coin.HEADS) {
            levelsToMove++;
        }

        Node<T> current = head;
        int currentLevel = head.getLevel();
        if (currentLevel < levelsToMove) {
            while (currentLevel < levelsToMove) {
                Node<T> temp = new Node<>(null, currentLevel + 1);
                head.setUp(temp);
                temp.setDown(head);
                head = temp;
                currentLevel++;
            }
            current = head;
        } else if (levelsToMove < currentLevel) {
            while (levelsToMove < current.getLevel()) {
                current = current.getDown();
            }
        },

        Node<T> newNode = new Node<>(data, levelsToMove);
        Node<T> oldNode = null;
        while (current != null) {
            if (current.getNext() == null
                || data.compareTo(current.getNext().getData()) < 0) {
                newNode.setNext(current.getNext());
                current.setNext(newNode);
                if (oldNode != null) {
                    oldNode.setDown(newNode);
                }
                newNode.setUp(oldNode);
                oldNode = newNode;
                newNode = new Node<>(data, levelsToMove - 1);
                current = current.getDown();
            } else if (data.compareTo(current.getNext().getData()) > 0) {
                current = current.getNext();
            }
        }
        size++;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size != 0) {
            Node<T> current = head;
            while (current != null) {
                if (current.getNext() == null) {
                    current = current.getDown();
                } else if (data.compareTo(current.getNext().getData()) < 0) {
                    current = current.getDown();
                } else if (data.compareTo(current.getNext().getData()) > 0) {
                    current = current.getNext();
                } else {
                    return current.getNext().getData();
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<T>(null, 1);
    }

    @Override
    public Set<T> dataSet() {
        Set<T> result = new HashSet<T>();
        if (size == 0) {
            return result;
        }
        Node<T> current = head;
        while (current.getDown() != null) {
            current = current.getDown();
        }
        while (current.getNext() != null) {
            result.add(current.getNext().getData());
            current = current.getNext();
        }
        return result;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        Node<T> current = head;
        Node<T> removed = null;
        while (current != null) {
            if (current.getNext() == null) {
                current = current.getDown();
            } else if (data.compareTo(current.getNext().getData()) < 0) {
                current = current.getDown();
            } else if (data.compareTo(current.getNext().getData()) > 0) {
                current = current.getNext();
            } else {
                removed = current.getNext();
                current.setNext(current.getNext().getNext());
                current = current.getDown();
            }
        }
        if (removed != null) {
            size--;
            return removed.getData();
        }
        return null;
    }
}
